package org.reclipse.tracer.ui.views;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Display;
import org.reclipse.tracer.ui.TracerUIPlugin;
import org.reclipse.tracer.ui.models.CollectionListener;
import org.reclipse.tracer.ui.models.ExecutionMonitorModel;
import org.reclipse.tracer.ui.models.MonitoredClass;
import org.reclipse.tracer.ui.models.MonitoredMethod;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3780 $ $Date: 2007-09-07 21:10:18 +0200 (Fr, 07 Sep 2007) $
 */
/* package */class ExecutionMonitorViewContentProvider implements
      ITreeContentProvider, CollectionListener, PropertyChangeListener
{

   TreeViewer treeViewer;


   public ExecutionMonitorViewContentProvider()
   {
      this.classUpdater = new ClassUpdater();
      this.methodUpdater = new MethodUpdater();

      setModel((ExecutionMonitorModel) TracerUIPlugin.getDefault()
            .getExecutionMonitorModel());
      getModel().addCollectionListener(this);
   }


   /**
    * <pre>
    *                                           model    1 
    * ExecutionMonitorViewContentProvider -----------------> ExecutionMonitorModel
    *                                                model 
    * </pre>
    */
   private ExecutionMonitorModel model;


   public ExecutionMonitorModel getModel()
   {
      return this.model;
   }


   public void setModel(final ExecutionMonitorModel value)
   {
      if (this.model != value)
      {
         this.model = value;
      }
   }


   /**
    * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
    *      java.lang.Object, java.lang.Object)
    */
   public void inputChanged(final Viewer viewer, final Object oldInput,
         final Object newInput)
   {
      this.treeViewer = (TreeViewer) viewer;
   }


   /**
    * @see org.eclipse.jface.viewers.IContentProvider#dispose()
    */
   public void dispose()
   {
   }


   /**
    * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
    */
   public Object[] getChildren(final Object parentElement)
   {
      if (parentElement instanceof MonitoredClass)
      {
         final MonitoredClass monitoredClass = (MonitoredClass) parentElement;
         return monitoredClass.valuesOfMethods().toArray();
      }

      return new Object[] {};
   }


   /**
    * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
    */
   public Object getParent(final Object element)
   {
      if (element instanceof MonitoredMethod)
      {
         final MonitoredMethod monitoredMethod = (MonitoredMethod) element;

         final Iterator iter = getModel().iteratorOfClasses();
         while (iter.hasNext())
         {
            final MonitoredClass monitoredClass = (MonitoredClass) iter.next();
            if (monitoredClass.hasInMethods(monitoredMethod))
            {
               return monitoredClass;
            }
         }
      }

      return null;
   }


   /**
    * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
    */
   public boolean hasChildren(final Object element)
   {
      if (element instanceof MonitoredMethod)
      {
         return false;
      }
      else if (element instanceof MonitoredClass)
      {
         final MonitoredClass monitoredClass = (MonitoredClass) element;
         return monitoredClass.sizeOfMethods() != 0;
      }

      return false;
   }


   /**
    * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
    */
   public Object[] getElements(final Object inputElement)
   {
      return getModel().valuesOfClasses().toArray();
   }


   /**
    * @see org.reclipse.tracer.ui.models.CollectionListener#elementAdded(java.lang.String,
    *      java.lang.Object)
    */
   public void elementAdded(final String collectionName, final Object value)
   {
      if (collectionName.equals(ExecutionMonitorModel.PROPERTY_CLASSES))
      {
         final MonitoredClass monitoredClass = (MonitoredClass) value;
         monitoredClass.addCollectionListener(this);

         this.classUpdater.setMonitoredClass(monitoredClass);
         this.classUpdater.setMonitoredMethod(null);

         Display.getDefault().syncExec(this.classUpdater);
      }
      if (collectionName.equals(MonitoredClass.PROPERTY_METHODS))
      {
         final MonitoredMethod monitoredMethod = (MonitoredMethod) value;
         monitoredMethod.addPropertyChangeListener(this);

         this.classUpdater.setMonitoredClass(monitoredMethod.getParent());
         this.classUpdater.setMonitoredMethod(null);

         Display.getDefault().syncExec(this.classUpdater);
      }
   }


   /**
    * @see org.reclipse.tracer.ui.models.CollectionListener#allElementsRemoved(java.lang.String)
    */
   public void allElementsRemoved(final String collectionName)
   {
      Display.getDefault().syncExec(new Runnable()
      {
         public void run()
         {
            ExecutionMonitorViewContentProvider.this.treeViewer.refresh();
         }
      });
   }


   /**
    * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
    */
   public void propertyChange(final PropertyChangeEvent event)
   {
      if (event.getPropertyName().equals(MonitoredMethod.PROPERTY_EXECUTIONS))
      {
         final MonitoredMethod monitoredMethod = (MonitoredMethod) event
               .getSource();
         this.methodUpdater.setMonitoredMethod(monitoredMethod);

         Display.getDefault().syncExec(this.methodUpdater);
      }
   }


   private ClassUpdater classUpdater;


   class ClassUpdater implements Runnable
   {

      private MonitoredClass monitoredClass;


      public void setMonitoredClass(final MonitoredClass monitoredClass)
      {
         this.monitoredClass = monitoredClass;
      }


      private MonitoredMethod monitoredMethod;


      public void setMonitoredMethod(final MonitoredMethod monitoredMethod)
      {
         this.monitoredMethod = monitoredMethod;
      }


      /**
       * @see java.lang.Runnable#run()
       */
      public void run()
      {
         Object[] array;
         if (this.monitoredMethod != null)
         {
            array = new Object[] { this.monitoredMethod };
         }
         else
         {
            array = new Object[] {};
         }

         ExecutionMonitorViewContentProvider.this.treeViewer.add(
               this.monitoredClass, array);
         ExecutionMonitorViewContentProvider.this.treeViewer.refresh();
      }

   }


   private MethodUpdater methodUpdater;

   class MethodUpdater implements Runnable
   {

      private MonitoredMethod monitoredMethod;


      public void setMonitoredMethod(final MonitoredMethod monitoredMethod)
      {
         this.monitoredMethod = monitoredMethod;
      }


      /**
       * @see java.lang.Runnable#run()
       */
      public void run()
      {
         ExecutionMonitorViewContentProvider.this.treeViewer.update(
               this.monitoredMethod, null);
      }

   }

}
