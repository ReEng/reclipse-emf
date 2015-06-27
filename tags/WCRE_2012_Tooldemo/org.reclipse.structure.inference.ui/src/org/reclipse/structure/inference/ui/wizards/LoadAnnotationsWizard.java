package org.reclipse.structure.inference.ui.wizards;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.reclipse.structure.InferenceUIPlugin;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.ui.InferenceConstants;
import org.reclipse.structure.inference.ui.views.annotations.AnnotationView;


/**
 * This wizard is used to export the annotations of an inference to a file.
 * 
 * @author harka
 */
public class LoadAnnotationsWizard extends Wizard
{
   private LoadAnnotationsWizardPage page;

   private AnnotationView view;


   public LoadAnnotationsWizard(AnnotationView view)
   {
      super();

      // save some
      this.view = view;

      // configure
      setWindowTitle("Load Inference Results");

      setDefaultPageImageDescriptor(InferenceUIPlugin
            .imageDescriptorFromPlugin("org.eclipse.ui",
                  "$nl$/icons/full/wizban/import_wiz.png"));
      setForcePreviousAndNextButtons(false);
      setHelpAvailable(false);
      setNeedsProgressMonitor(true);
   }


   @Override
   public void addPages()
   {
      page = new LoadAnnotationsWizardPage();
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
            monitor.beginTask("Load annotations", 2);

            // create resource
            URI uri = URI.createPlatformResourceURI(page.resource.getFullPath()
                  .toString(), true);
            ResourceSet ress = new ResourceSetImpl();
            Resource res = ress.getResource(uri, true);

            // load resource
            try
            {
               res.load(getLoadOptions((XMIResourceImpl) res));
               monitor.worked(1);
            }
            catch (IOException e)
            {
               e.printStackTrace();
               monitor.done();
            }

            // get annotations
            Set<ASGAnnotation> annos = new HashSet<ASGAnnotation>();
            for (EObject element : res.getContents())
            {
               if (element instanceof ASGAnnotation)
               {
                  annos.add((ASGAnnotation) element);
               }
            }

            // load them into the view
            view.loadAnnotations(annos);

            monitor.worked(1);
            monitor.done();
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


   protected static Map<Object, Object> getLoadOptions(XMIResourceImpl resource)
   {
      Map<Object, Object> options = resource.getDefaultLoadOptions();

      options.put(XMLResource.OPTION_DEFER_ATTACHMENT, Boolean.TRUE);
      options.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
      options.put(XMLResource.OPTION_USE_DEPRECATED_METHODS, Boolean.TRUE);
      options.put(XMLResource.OPTION_USE_PARSER_POOL, new XMLParserPoolImpl());
      options.put(XMLResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP,
            new HashMap<Object, Object>());

      resource.setIntrinsicIDToEObjectMap(new HashMap<String, EObject>());

      return options;
   }

   private class LoadAnnotationsWizardPage extends WizardPage
   {
      protected IResource resource;


      /**
       * Creates the page with the given selection applying it to the location browsing.
       * 
       * @param view The annotation view.
       */
      protected LoadAnnotationsWizardPage()
      {
         super("Load Annotations");

         // configure page
         setTitle("Load Annotations");
         setDescription("Import previous annotations from a workspace resource.");
      }


      @Override
      public void createControl(Composite parent)
      {
         Composite main = new Composite(parent, SWT.NONE);
         main.setLayout(new GridLayout());

         // location
         Label label = new Label(main, SWT.LEAD);
         label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
         label.setText("Select the location to load the annotations from:");

         // create workspace view
         TreeViewer workspace = new TreeViewer(main, SWT.BORDER | SWT.SINGLE);
         workspace.getTree().setLayoutData(
               new GridData(SWT.FILL, SWT.FILL, true, true));
         workspace.setAutoExpandLevel(2);
         workspace.setContentProvider(new WorkspaceContainerContentProvider());
         workspace.setLabelProvider(new WorkbenchLabelProvider());
         workspace.setInput(ResourcesPlugin.getWorkspace().getRoot());
         workspace.addSelectionChangedListener(new ISelectionChangedListener()
         {
            @Override
            public void selectionChanged(SelectionChangedEvent event)
            {
               if (!event.getSelection().isEmpty()
                     && event.getSelection() instanceof IStructuredSelection)
               {
                  IStructuredSelection selection = (IStructuredSelection) event
                        .getSelection();

                  if (selection.getFirstElement() instanceof IResource)
                  {
                     resource = (IResource) selection.getFirstElement();
                     setPageComplete(isValid());
                  }
               }
            }
         });

         setControl(workspace.getControl());
      }


      protected boolean isValid()
      {
         // check container
         if (resource == null
               || !(resource instanceof IFile)
               || !((IFile) resource).getFileExtension().equalsIgnoreCase(
                     InferenceConstants.EXTENSION_ANNOTATIONS_EXPORT))
         {
            setErrorMessage("Select a valid resource.");
            return false;
         }

         // everything seems to be valid
         setErrorMessage(null);
         return true;
      }
   }

   private class WorkspaceContainerContentProvider extends
         BaseWorkbenchContentProvider
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
            if (child instanceof IContainer
                  && ((IContainer) child).isAccessible()
                  && !((IContainer) child).getName().startsWith(".")
                  && hasAdequateChildren((IContainer) child))
            {
               children.add(child);
            }
            else if (child instanceof IFile
                  && ((IFile) child).getFileExtension().equals(
                        InferenceConstants.EXTENSION_ANNOTATIONS_EXPORT))
            {
               children.add(child);
            }
         }

         return children.toArray(new Object[children.size()]);
      }


      private boolean hasAdequateChildren(IContainer container)
      {
         try
         {
            for (IResource child : container.members())
            {
               if (child instanceof IFile
                     && child.getFileExtension() != null
                     && child.getFileExtension().equalsIgnoreCase(
                           InferenceConstants.EXTENSION_ANNOTATIONS_EXPORT))
               {
                  return true;
               }
               else if (child instanceof IContainer
                     && hasAdequateChildren((IContainer) child))
               {
                  return true;
               }
            }
         }
         catch (CoreException e)
         {
            e.printStackTrace();
         }

         return false;
      }
   }
}
