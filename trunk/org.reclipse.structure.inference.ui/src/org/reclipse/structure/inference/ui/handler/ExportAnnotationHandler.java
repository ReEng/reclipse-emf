package org.reclipse.structure.inference.ui.handler;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.AbstractMethodInvocation;
import org.eclipse.gmt.modisco.java.BodyDeclaration;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.InterfaceDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.emf.util.JavaSwitch;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.handlers.HandlerUtil;
import org.reclipse.structure.InferenceUIPlugin;
import org.reclipse.structure.inference.annotations.ASGAnnotation;

/**
 * Example export String: org.spotter.demo.app.DummyApp.testOLB*
 *
 * @author Sebastian Lehrig
 */
public class ExportAnnotationHandler extends AbstractHandler {

    private static final String SCOPE_EXTENSION = "*.scope";
    private static final String[] EXTENSIONS_FILTER = new String[] { SCOPE_EXTENSION };

    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final ISelection selection = HandlerUtil.getCurrentSelectionChecked(event);

        if (selection instanceof TreeSelection) {
            final List<String> scopeSpecifications = getScopeSpecifications((TreeSelection) selection);

            if (scopeSpecifications.size() == 0) {
                ErrorDialog.openError(HandlerUtil.getActiveShellChecked(event),
                        "Export Error: Nothing to be exported found!",
                        "To extract annotations, at least one exportable annotation has to be selected, e.g., a SynchronizedMethod.",
                        new Status(IStatus.ERROR, InferenceUIPlugin.ID, 0, "Nothing to be exported found!", null));
                return null;
            }

            final String filePath = requestFileWithDialog(event);
            if (filePath != null) {
                try {
                    export(filePath, scopeSpecifications);
                } catch (final FileNotFoundException e) {
                    ErrorDialog.openError(HandlerUtil.getActiveShellChecked(event), "Export Error: Cannot write file!",
                            "To extract annotations, you need to select a file that can be written. For the file \""
                                    + filePath + "\" writing did not work.",
                            new Status(IStatus.ERROR, InferenceUIPlugin.ID, 0, "Cannot write file!", null));
                    return null;
                }

                MessageDialog.openInformation(HandlerUtil.getActiveShellChecked(event), "Export Success",
                        "Successfully exported dynamic spotter scope specifications.");
            }
        }

        return null;
    }

    private void export(final String filePath, final List<String> scopeSpecifications) throws FileNotFoundException {
        if (filePath != null) {
            try (PrintStream printStream = new PrintStream(filePath)) {
                for (final String scopeSpecification : scopeSpecifications) {
                    printStream.println(scopeSpecification);
                }
            }
        }
    }

    @SuppressWarnings("restriction")
    private static List<String> getScopeSpecifications(final TreeSelection treeSelection) {
        final List<String> scopeSpecifications = new ArrayList<>(treeSelection.size());

        for (final Iterator<?> iter = treeSelection.iterator(); iter.hasNext();) {
            final Object node = iter.next();

            if (node instanceof ASGAnnotation) {
                final ASGAnnotation annotation = (ASGAnnotation) node;

                switch (annotation.getPattern().getName()) {
                case "SynchronizedMethod":
                    final MethodDeclaration synchronizedMethod = (MethodDeclaration) annotation.getBoundObjects()
                            .get("method").get(0);
                    final Collection<AbstractMethodInvocation> usages = findAllPotentialUsages(synchronizedMethod);

                    for (final AbstractMethodInvocation methodInvocation : usages) {
                        // recursively go up eContainers until method declaration is found.
                        new JavaSwitch<Object>() {

                            @Override
                            public Object caseAbstractMethodDeclaration(
                                    final AbstractMethodDeclaration abstractMethodDeclaration) {
                                scopeSpecifications.add(computeExportString(abstractMethodDeclaration));
                                return null;
                            };

                            @Override
                            public Object defaultCase(final EObject object) {
                                if (object != null && object.eContainer() != null) {
                                    return doSwitch(object.eContainer());
                                }
                                return null;
                            };

                        }.doSwitch(methodInvocation);
                    }
                    break;
                case "AcquireReleasePair":
                    final ASGAnnotation seffAnnotation = (ASGAnnotation) annotation.getBoundObjects().get("seff")
                            .get(0);
                    final MethodDeclaration containingMethod = (MethodDeclaration) seffAnnotation.getBoundObjects()
                            .get("containingMethod").get(0);
                    scopeSpecifications.add(computeExportString(containingMethod));
                    break;
                default:
                    break;
                }
            }
        }

        return scopeSpecifications;
    }

    /**
     * Try to find all usages of the declared method including all potential polymorphic usages
     * 
     * @param synchronizedMethod
     *            method to check for
     * @return all usages including polymorphic ones
     */
    private static Collection<AbstractMethodInvocation> findAllPotentialUsages(
            final MethodDeclaration synchronizedMethod) {
        final Collection<AbstractMethodInvocation> usages = new LinkedList<AbstractMethodInvocation>();
        usages.addAll(synchronizedMethod.getUsages());
        MethodDeclaration m = synchronizedMethod;
        while (m.getRedefinedMethodDeclaration() != null) {
            usages.addAll(m.getUsages());
            m = m.getRedefinedMethodDeclaration();
        }
        for (final TypeAccess interfaze : synchronizedMethod.getAbstractTypeDeclaration().getSuperInterfaces()) {
            final AbstractMethodDeclaration interfaceMethod = getMatchingMethod(interfaze, synchronizedMethod);
            if (interfaceMethod != null) {
                usages.addAll(interfaceMethod.getUsages());
            }
        }
        return usages;
    }

    private static AbstractMethodDeclaration getMatchingMethod(final TypeAccess intf,
            final MethodDeclaration synchronizedMethod) {
        final InterfaceDeclaration interfaceDeclaration = (InterfaceDeclaration) intf.getType();
        for (final BodyDeclaration bodyDeclaration : interfaceDeclaration.getBodyDeclarations()) {
            if (bodyDeclaration instanceof MethodDeclaration) {
                final MethodDeclaration candidate = (MethodDeclaration) bodyDeclaration;
                if (methodsAreEqual(synchronizedMethod, candidate)) {
                    return candidate;
                }
            }
        }
        // TODO: Implement superinterfaces!
        return null;
    }

    private static boolean methodsAreEqual(final MethodDeclaration synchronizedMethod,
            final MethodDeclaration candidate) {
        if (!candidate.getName().equals(synchronizedMethod.getName())
                || candidate.getParameters().size() != synchronizedMethod.getParameters().size()) {
            return false;
        }

        for (int i = 0; i < candidate.getParameters().size(); i++) {
            final TypeAccess typeAccess = candidate.getParameters().get(i).getType();
            if (typeAccess.getType() != synchronizedMethod.getParameters().get(i).getType().getType()) {
                return false;
            }
        }

        return true;
    }

    private static String computeExportString(final AbstractMethodDeclaration methodDeclaration) {
        final ClassDeclaration classDeclaration = (ClassDeclaration) methodDeclaration.getAbstractTypeDeclaration();
        final StringBuilder sb = new StringBuilder();
        sb.append(getFullQualifiedName(classDeclaration.getPackage()));
        sb.append(classDeclaration.getName());
        sb.append(".");
        sb.append(methodDeclaration.getName());
        sb.append("*");
        return sb.toString();
    }

    private static String getFullQualifiedName(Package pkg) {
        final StringBuilder sb = new StringBuilder();

        while (pkg != null) {
            sb.insert(0, ".");
            sb.insert(0, pkg.getName());
            pkg = pkg.getPackage();
        }

        return sb.toString();
    }

    private static String requestFileWithDialog(final ExecutionEvent event) throws ExecutionException {
        final FileDialog dialog = new FileDialog(HandlerUtil.getActiveShellChecked(event), SWT.SAVE);
        dialog.setFilterExtensions(EXTENSIONS_FILTER);
        return dialog.open();
    }

}
