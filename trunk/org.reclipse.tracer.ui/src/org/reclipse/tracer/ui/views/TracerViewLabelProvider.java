package org.reclipse.tracer.ui.views;


import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.reclipse.tracer.ui.TracerUIImages;
import org.reclipse.tracer.ui.models.MonitoredClass;
import org.reclipse.tracer.ui.models.MonitoredMethod;
import org.reclipse.tracer.ui.models.VMEvent;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3786 $ $Date: 2007-09-09 23:44:20 +0200 (So, 09 Sep 2007) $
 */
/* package */class TracerViewLabelProvider extends LabelProvider implements
      ITableLabelProvider
{

   /**
    * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
    */
   public String getColumnText(final Object obj, final int index)
   {
      return getText(obj);
   }


   /**
    * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
    */
   public Image getColumnImage(final Object obj, final int index)
   {
      return getImage(obj);
   }


   /**
    * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
    */
   @Override
   public Image getImage(final Object obj)
   {
      Image image = null;
      VMEvent event = null;

      try
      {
         event = (VMEvent) obj;
      }
      catch (final ClassCastException e)
      {
         throw new IllegalArgumentException("Object of type VMEvent expected!");
      }

      if (event.getType() == VMEvent.METHOD_REQUEST_CREATED)
      {
         final MonitoredMethod monitoredMethod = (MonitoredMethod) event;
         image = getMethodImage(monitoredMethod);
      }
      else if (event.getType() == VMEvent.CLASS_LOADED)
      {
         final MonitoredClass monitoredClass = (MonitoredClass) event;
         image = getClassImage(monitoredClass);
      }
      else if (event.getType() == VMEvent.THREAD_DEATH)
      {
         image = TracerUIImages.getImage(TracerUIImages.IMG_THREAD_STOPPED);
      }
      else if (event.getType() == VMEvent.VM_START)
      {
         image = TracerUIImages.getImage(TracerUIImages.IMG_VM_STARTED);
      }
      else if (event.getType() == VMEvent.VM_DISCONNECT)
      {
         image = TracerUIImages.getImage(TracerUIImages.IMG_VM_STOPPED);
      }
      else if (event.getType() == VMEvent.VM_DEATH)
      {
         image = TracerUIImages.getImage(TracerUIImages.IMG_VM_STOPPED);
      }
      else if (event.getType() == VMEvent.EXCEPTION_THROWN)
      {
         image = TracerUIImages.getImage(TracerUIImages.IMG_EXCEPTION_THROWN);
      }
      else if (event.getType() == VMEvent.TRACER_EXCEPTION)
      {
         image = TracerUIImages.getImage(TracerUIImages.IMG_TRACER_EXECPTION);
      }

      return image;
   }


   public static Image getMethodImage(final MonitoredMethod monitoredMethod)
   {
      if (monitoredMethod.getVisibility() == MonitoredMethod.PRIVATE)
      {
         return TracerUIImages.getImage(TracerUIImages.IMG_PRIVATE_METHOD);
      }
      else if (monitoredMethod.getVisibility() == MonitoredMethod.PACKAGE)
      {
         return TracerUIImages.getImage(TracerUIImages.IMG_PACKAGE_METHOD);
      }
      else if (monitoredMethod.getVisibility() == MonitoredMethod.PROTECTED)
      {
         return TracerUIImages.getImage(TracerUIImages.IMG_PROTECTED_METHOD);
      }
      else
      {
         return TracerUIImages.getImage(TracerUIImages.IMG_PUBLIC_METHOD);
      }
   }


   public static Image getClassImage(final MonitoredClass monitoredClass)
   {
      if (monitoredClass.getModifier() == MonitoredClass.ABSTRACT)
      {
         return TracerUIImages.getImage(TracerUIImages.IMG_ABSTRACT_CLASS);
      }
      else if (monitoredClass.getModifier() == MonitoredClass.FINAL)
      {
         return TracerUIImages.getImage(TracerUIImages.IMG_FINAL_CLASS);
      }
      else
      {
         return TracerUIImages.getImage(TracerUIImages.IMG_CLASS);
      }
   }

}
