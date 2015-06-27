package org.reclipse.structure.inference.ui.wizards;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.reclipse.structure.InferenceUIPlugin;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.annotations.AnnotationsPackage;
import org.reclipse.structure.inference.annotations.SetInstanceAnnotation;
import org.reclipse.structure.inference.annotations.TemporaryAnnotation;
import org.reclipse.structure.inference.ui.InferenceConstants;
import org.reclipse.structure.inference.ui.views.annotations.AnnotationView;
import org.reclipse.structure.specification.PSPatternSpecification;


/**
 * This wizard is used to export the annotations of an inference to a file.
 * 
 * @author harka
 */
public class SaveAnnotationsWizard extends Wizard
{
   private SaveAnnotationsWizardPage page;

   private AnnotationView view;


   public SaveAnnotationsWizard(AnnotationView view)
   {
      super();

      // save some
      this.view = view;

      // configure
      setWindowTitle("Save Inference Results");
      setDefaultPageImageDescriptor(InferenceUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui",
            "$nl$/icons/full/wizban/export_wiz.png"));
      setForcePreviousAndNextButtons(false);
      setHelpAvailable(false);
      setNeedsProgressMonitor(true);
   }


   @Override
   public void addPages()
   {
      page = new SaveAnnotationsWizardPage(view);
      addPage(page);
   }


   @Override
   public boolean performFinish()
   {
      WorkspaceModifyOperation op = new WorkspaceModifyOperation()
      {
         @Override
         protected void execute(IProgressMonitor monitor)
               throws CoreException,
                  InvocationTargetException,
                  InterruptedException
         {
            monitor.beginTask("Saving annotations", 1 + page.annotations.size());

            // create resource
            IPath path = page.container.getFullPath().addTrailingSeparator();
            if (page.filename.endsWith(InferenceConstants.EXTENSION_ANNOTATIONS_EXPORT))
            {
               path = path.append(page.filename);
            }
            else
            {
               path = path.append(page.filename).addFileExtension(InferenceConstants.EXTENSION_ANNOTATIONS_EXPORT);
            }
            URI uri = URI.createPlatformResourceURI(path.toString(), true);
            ResourceSet ress = new ResourceSetImpl();
            Resource res = ress.createResource(uri);

            // fill resource
            for (ASGAnnotation anno : page.annotations)
            {
               // rename annotation type (due to usage of specific created annotation classes)
               // TODO: this is evil!
               String name = AnnotationsPackage.eINSTANCE.getASGAnnotation().getName();
               if (anno instanceof SetInstanceAnnotation)
               {
                  name = AnnotationsPackage.eINSTANCE.getSetInstanceAnnotation().getName();
               }
               else if (anno instanceof TemporaryAnnotation)
               {
                  name = AnnotationsPackage.eINSTANCE.getTemporaryAnnotation().getName();
               }

               anno.eClass().setName(name);
               anno.eClass().getEPackage().setName(AnnotationsPackage.eNAME);
               anno.eClass().getEPackage().setNsPrefix(AnnotationsPackage.eNS_PREFIX);
               anno.eClass().getEPackage().setNsURI(AnnotationsPackage.eNS_URI);

               res.getContents().add(anno);
               monitor.worked(1);
            }

            // save resource
            try
            {
               res.save(Collections.emptyMap());
               monitor.worked(1);
            }
            catch (IOException e)
            {
               e.printStackTrace();
            }
            finally
            {
               monitor.done();
            }
         }
      };

      // run it
      try
      {
         getContainer().run(true, true, op);
      }
      catch (Exception e)
      {
         e.printStackTrace();
         return false;
      }

      return true;
   }

   private class SaveAnnotationsWizardPage extends WizardPage
   {
      protected IContainer container;

      protected String filename;

      protected List<ASGAnnotation> annotations;


      /**
       * Creates the page with the given selection applying it to the location browsing.
       * 
       * @param view The annotation view.
       */
      protected SaveAnnotationsWizardPage(AnnotationView view)
      {
         super("Save Annotations");

         // configure page
         setTitle("Save Annotations");
         setDescription("Export the current viewed annotations to a workspace resource.");

         // get annotations
         if (view != null)
         {
            annotations = new ArrayList<ASGAnnotation>();
            if (view.getAnnotations().values() != null)
            {
               for (PSPatternSpecification key : view.getAnnotations().keySet())
               {
                  for (ASGAnnotation anno : view.getAnnotations().get(key))
                  {
                     annotations.add(anno);
                  }
               }
            }

            // sort them (by pattern)
            Collections.sort(annotations, new Comparator<ASGAnnotation>()
            {

               @Override
               public int compare(ASGAnnotation one, ASGAnnotation two)
               {
                  return one.getPattern().getName().compareTo(two.getPattern().getName());
               }
            });
         }
      }


      @Override
      public void createControl(Composite parent)
      {
         Composite main = new Composite(parent, SWT.NONE);
         main.setLayout(new GridLayout());

         // location
         Label label = new Label(main, SWT.LEAD);
         label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
         label.setText("Select the location to save the annotations:");

         // create workspace view
         TreeViewer workspace = new TreeViewer(main, SWT.BORDER | SWT.SINGLE);
         workspace.getTree().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
         workspace.setContentProvider(new WorkspaceContainerContentProvider());
         workspace.setLabelProvider(new WorkbenchLabelProvider());
         workspace.setInput(ResourcesPlugin.getWorkspace().getRoot());
         workspace.addSelectionChangedListener(new ISelectionChangedListener()
         {
            @Override
            public void selectionChanged(SelectionChangedEvent event)
            {
               if (!event.getSelection().isEmpty() && event.getSelection() instanceof IStructuredSelection)
               {
                  IStructuredSelection selection = (IStructuredSelection) event.getSelection();

                  if (selection.getFirstElement() instanceof IContainer)
                  {
                     container = (IContainer) selection.getFirstElement();
                     setPageComplete(isValid());
                  }
                  else if (selection.getFirstElement() instanceof IFile)
                  {
                     container = ((IFile) selection.getFirstElement()).getParent();
                     setPageComplete(isValid());
                  }
               }
            }
         });

         // file name
         Composite nameContainer = new Composite(main, SWT.NONE);
         nameContainer.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
         nameContainer.setLayout(new GridLayout(2, false));

         label = new Label(nameContainer, SWT.LEAD);
         label.setText("File name:");

         final Text text = new Text(nameContainer, SWT.LEAD | SWT.BORDER);
         text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
         text.addModifyListener(new ModifyListener()
         {
            @Override
            public void modifyText(ModifyEvent e)
            {
               filename = text.getText().trim();
               setPageComplete(isValid());
            }
         });

         // select parent container
         if (getInitialSelection() != null)
         {
            workspace.setSelection(new StructuredSelection(getInitialSelection().getParent()), true);
         }

         // provide default name
         text.setText(getInitialFileName());

         setControl(text);
      }


      private String getInitialFileName()
      {
         String date = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());

         StringBuilder name = new StringBuilder();

         name.append(getInitialSelection().getName());
         name.append("_"); //$NON-NLS-1$
         name.append(date);
         name.append("."); //$NON-NLS-1$
         name.append(InferenceConstants.EXTENSION_ANNOTATIONS_EXPORT);

         return name.toString();
      }


      private IFile getInitialSelection()
      {
         // find resource of the AST
         URI uri = null;
         for (ASGAnnotation anno : annotations)
         {
            // go through annotated element keys
            for (String key : anno.getAnnotatedElements().keySet())
            {
               // go through annotated elements
               for (EObject element : anno.getAnnotatedElements().get(key))
               {
                  uri = element.eResource().getURI();
                  break;
               }
            }
         }

         // when an uri has been found, get WS container
         if (uri != null)
         {
            // find resource and return it
            IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(uri.toPlatformString(true));
            if (resource != null && resource instanceof IFile)
            {
               return (IFile) resource;
            }
         }

         return null;
      }


      protected boolean isValid()
      {
         // check if there are annotations
         if (annotations == null || annotations.isEmpty())
         {
            setErrorMessage("There are no annotations to save.");
            return false;
         }

         // check container
         if (container == null)
         {
            setErrorMessage("Select a container for the resource.");
            return false;
         }

         // check file name
         if (filename == null || filename.isEmpty())
         {
            setErrorMessage("Provide a valid file name.");
            return false;
         }

         // check if file already exists
         IPath path2check = container.getFullPath().addTrailingSeparator();
         if (filename.endsWith(InferenceConstants.EXTENSION_ANNOTATIONS_EXPORT))
         {
            path2check = path2check.append(filename);
         }
         else
         {
            path2check = path2check.append(filename).addFileExtension(InferenceConstants.EXTENSION_ANNOTATIONS_EXPORT);
         }
         if (ResourcesPlugin.getWorkspace().getRoot().exists(path2check))
         {
            setErrorMessage("The file already exists.");
            return false;
         }

         // everything seems to be valid
         setErrorMessage(null);
         return true;
      }
   }

   private class WorkspaceContainerContentProvider extends BaseWorkbenchContentProvider
   {
      public WorkspaceContainerContentProvider()
      {
         super();
      }


      @Override
      public Object[] getChildren(Object element)
      {
         // prepare list
         List<Object> children = new ArrayList<Object>();

         // check children
         for (Object child : super.getChildren(element))
         {
            if (child instanceof IContainer && ((IContainer) child).isAccessible()
                  && !((IContainer) child).getName().startsWith("."))
            {
               children.add(child);
            }
         }

         return children.toArray(new Object[children.size()]);
      }
   }
}
