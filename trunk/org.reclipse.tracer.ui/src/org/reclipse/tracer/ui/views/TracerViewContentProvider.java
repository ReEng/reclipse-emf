package org.reclipse.tracer.ui.views;


import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Display;
import org.reclipse.tracer.ui.TracerUIPlugin;
import org.reclipse.tracer.ui.models.CollectionListener;
import org.reclipse.tracer.ui.models.EventsModel;
import org.reclipse.tracer.ui.models.VMEvent;


/**
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4624 $ $Date: 2011-01-18 10:58:31 +0100 (Di, 18 Jan 2011) $
 */
/* package */class TracerViewContentProvider implements
      IStructuredContentProvider, CollectionListener
{

   TableViewer tableViewer;


   public TracerViewContentProvider()
   {
      setModel((EventsModel) TracerUIPlugin.getDefault().getEventsModel());
      getModel().addCollectionListener(this);
   }


   /**
    * <pre>
    *                                 model    1 
    * TracerViewContentProvider -----------------> EventsModel
    *                                      model 
    * </pre>
    */
   private EventsModel model;


   public EventsModel getModel()
   {
      return this.model;
   }


   public void setModel(EventsModel value)
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
   public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
   {
      this.tableViewer = (TableViewer) viewer;
   }


   /**
    * @see org.eclipse.jface.viewers.IContentProvider#dispose()
    */
   public void dispose()
   {
   }


   /**
    * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
    */
   public Object[] getElements(Object parent)
   {
      return getModel().valuesOfEvents().toArray();
   }


   /**
    * @see org.reclipse.tracer.ui.models.CollectionListener#elementAdded(java.lang.String,
    *      java.lang.Object)
    */
   public void elementAdded(String collectionName, Object value)
   {
      if (collectionName.equals(EventsModel.PROPERTY_EVENTS))
      {
         final VMEvent event = (VMEvent) value;
         Display.getDefault().syncExec(new Runnable()
         {
            public void run()
            {
               tableViewer.add(event);
            }
         });
      }
   }


   /**
    * @see org.reclipse.tracer.ui.models.CollectionListener#allElementsRemoved(java.lang.String)
    */
   public void allElementsRemoved(String collectionName)
   {
      if (collectionName.equals(EventsModel.PROPERTY_EVENTS))
      {
         Display.getDefault().syncExec(new Runnable()
         {
            public void run()
            {
               tableViewer.refresh();
            }
         });
      }
   }

}
