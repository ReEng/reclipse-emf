package org.reclipse.tracedefinition.editor;


import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.reclipse.tracer.model.definition.CallerClass;
import org.reclipse.tracer.model.definition.ConsiderClass;
import org.reclipse.tracer.model.definition.ConsiderMethod;
import org.reclipse.tracer.model.definition.ConsiderTrace;
import org.reclipse.tracer.model.definition.CriticalClass;
import org.reclipse.tracer.model.definition.CriticalTrace;
import org.reclipse.tracer.model.definition.Parameter;
import org.reclipse.tracer.model.definition.TraceDefinition;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3625 $ $Date: 2007-05-11 13:51:03 +0200 (Fr, 11 Mai 2007) $
 */
public class TraceDefinitionLabelProvider extends LabelProvider
{
   /**
    * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
    */
   @Override
   public String getText(Object element)
   {
      if (element instanceof TraceDefinition)
      {
         return "Trace Definition";
      }
      else if (element instanceof CriticalTrace)
      {
         return "Critical Trace";
      }
      else if (element instanceof ConsiderTrace)
      {
         return "Consider Trace";
      }
      else if (element instanceof CriticalClass)
      {
         CriticalClass criticalClass = (CriticalClass) element;
         return criticalClass.getName();
      }
      else if (element instanceof ConsiderClass)
      {
         ConsiderClass considerClass = (ConsiderClass) element;
         return considerClass.getName();
      }
      else if (element instanceof CallerClass)
      {
         CallerClass callerClass = (CallerClass) element;
         return callerClass.getName();
      }
      else if (element instanceof ConsiderMethod)
      {
         ConsiderMethod considerMethod = (ConsiderMethod) element;
         return considerMethod.getSignature();
      }
      else if (element instanceof Parameter)
      {
         Parameter parameter = (Parameter) element;
         return parameter.getType();
      }

      return (element != null) ? element.toString() : "element is null";
   }


   /**
    * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
    */
   @Override
   public Image getImage(Object element)
   {
      Image image = null;

      if (element instanceof TraceDefinition)
      {
         image = TraceDefinitionEditorImages
               .getImage(TraceDefinitionEditorImages.IMG_TRACE_DEFINTION);
      }
      else if (element instanceof CriticalTrace)
      {
         image = TraceDefinitionEditorImages
               .getImage(TraceDefinitionEditorImages.IMG_CRITICAL_TRACE);
      }
      else if (element instanceof ConsiderTrace)
      {
         image = TraceDefinitionEditorImages
               .getImage(TraceDefinitionEditorImages.IMG_CONSIDER_TRACE);
      }
      else if (element instanceof CriticalClass)
      {
         image = TraceDefinitionEditorImages
               .getImage(TraceDefinitionEditorImages.IMG_CLASS);
      }
      else if (element instanceof ConsiderClass)
      {
         image = TraceDefinitionEditorImages
               .getImage(TraceDefinitionEditorImages.IMG_CLASS);
      }
      else if (element instanceof CallerClass)
      {
         image = TraceDefinitionEditorImages
               .getImage(TraceDefinitionEditorImages.IMG_CLASS);
      }
      else if (element instanceof ConsiderMethod)
      {
         image = TraceDefinitionEditorImages
               .getImage(TraceDefinitionEditorImages.IMG_PUBLIC_METHOD);
      }
      else if (element instanceof Parameter)
      {
         image = TraceDefinitionEditorImages
               .getImage(TraceDefinitionEditorImages.IMG_PARAMETER);
      }

      return image;
   }

}
