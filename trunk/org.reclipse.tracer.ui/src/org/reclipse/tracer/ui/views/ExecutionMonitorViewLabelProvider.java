package org.reclipse.tracer.ui.views;


import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.reclipse.tracer.ui.TracerUIImages;
import org.reclipse.tracer.ui.models.MonitoredClass;
import org.reclipse.tracer.ui.models.MonitoredMethod;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3786 $
 */
/* package */class ExecutionMonitorViewLabelProvider extends LabelProvider
{

   /**
    * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
    */
   @Override
   public Image getImage(final Object element)
   {
      Image image = null;

      if (element instanceof MonitoredClass)
      {
         final MonitoredClass monitoredClass = (MonitoredClass) element;
         image = TracerViewLabelProvider.getClassImage(monitoredClass);
      }
      else if (element instanceof MonitoredMethod)
      {
         final MonitoredMethod monitoredMethod = (MonitoredMethod) element;
         if (monitoredMethod.getExecutions() == 0)
         {
            image = TracerUIImages
                  .getImage(TracerUIImages.IMG_METHOD_NOT_EXECUTED);
         }
         else
         {
            image = TracerUIImages.getImage(TracerUIImages.IMG_METHOD_EXECUTED);
         }
      }

      return image;
   }


   /**
    * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
    */
   @Override
   public String getText(final Object element)
   {
      if (element instanceof MonitoredClass)
      {
         final MonitoredClass monitoredClass = (MonitoredClass) element;
         return monitoredClass.getName();
      }
      else if (element instanceof MonitoredMethod)
      {
         final MonitoredMethod monitoredMethod = (MonitoredMethod) element;
         final StringBuffer buffer = new StringBuffer(monitoredMethod
               .getSignature());
         switch (monitoredMethod.getExecutions())
         {
            case 0:
               buffer.append(": never executed.");
               return buffer.toString();

            case 1:
               buffer.append(": 1 execution.");
               return buffer.toString();

            default:
               buffer.append(": ");
               buffer.append(Integer.toString(monitoredMethod.getExecutions()));
               buffer.append(" executions.");
               return buffer.toString();
         }
      }

      return "should not happen";
   }
}
