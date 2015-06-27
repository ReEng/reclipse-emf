package org.reclipse.tracer.ui.launching;


import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.model.WorkbenchContentProvider;


public class SelectJavaProjectWizardPage extends WizardPage
{
   IJavaProject project = null;

   TreeViewer treeViewer = null;


   protected SelectJavaProjectWizardPage(String pageName)
   {
      super(pageName);
   }


   public void createControl(Composite parent)
   {
      this.treeViewer = new TreeViewer(parent, SWT.BORDER);

      this.treeViewer.setLabelProvider(new WorkbenchLabelProvider());
      this.treeViewer.setContentProvider(new WorkbenchContentProvider());
      this.treeViewer.setInput(ResourcesPlugin.getWorkspace().getRoot());
      this.treeViewer.addFilter(new ViewerFilter()
      {
         /**
          * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer,
          *      java.lang.Object, java.lang.Object)
          */
         @Override
         public boolean select(Viewer viewer, Object parentElement, Object element)
         {
            if (element instanceof IProject)
            {
               IProject project = (IProject) element;
               if (project.isOpen())
               {
                  try
                  {
                     return (project.hasNature(JavaCore.NATURE_ID));
                  }
                  catch (CoreException e)
                  {
                     e.printStackTrace();
                     return false;
                  }
               }
            }
            return false;
         }
      });
      this.treeViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {

         public void selectionChanged(SelectionChangedEvent event)
         {
            project = JavaCore.create((IProject) ((TreeSelection) event.getSelection()).getFirstElement());
         }

      });

      this.setControl(parent);
   }


   public IJavaProject getSelectedProject()
   {
      return this.project;
   }
}
