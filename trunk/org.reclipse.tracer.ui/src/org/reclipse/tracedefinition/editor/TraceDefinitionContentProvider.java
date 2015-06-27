package org.reclipse.tracedefinition.editor;


import java.util.ArrayList;
import java.util.TreeSet;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.reclipse.tracer.model.definition.AbstractTrace;
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
 * @version $Revision: 3782 $ $Date: 2007-09-08 20:25:29 +0200 (Sa, 08 Sep 2007) $
 */
public class TraceDefinitionContentProvider implements ITreeContentProvider
{

   private TraceDefinition traceDefinition;


   /**
    * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
    */
   public Object[] getChildren(final Object parentElement)
   {
      if (parentElement instanceof TraceDefinition)
      {
         final TraceDefinition traceDefinition = (TraceDefinition) parentElement;
         final ArrayList list = new ArrayList(2);
         if (traceDefinition.getCriticalTrace() != null)
         {
            list.add(traceDefinition.getCriticalTrace());
         }
         if (traceDefinition.getConsiderTrace() != null)
         {
            list.add(traceDefinition.getConsiderTrace());
         }
         return list.toArray();
      }
      else if (parentElement instanceof AbstractTrace)
      {
         final AbstractTrace abstractTrace = (AbstractTrace) parentElement;
         final TreeSet treeSet = new TreeSet(abstractTrace.valuesOfClasses());
         return treeSet.toArray();
      }
      else if (parentElement instanceof CriticalClass)
      {
         final CriticalClass criticalClass = (CriticalClass) parentElement;
         final TreeSet treeSet = new TreeSet(criticalClass.valuesOfCallers());
         return treeSet.toArray();
      }
      else if (parentElement instanceof ConsiderClass)
      {
         final ConsiderClass considerClass = (ConsiderClass) parentElement;
         final TreeSet treeSet = new TreeSet(considerClass.valuesOfMethods());
         return treeSet.toArray();
      }
      else if (parentElement instanceof CallerClass)
      {
         return new Object[] {};
      }
      else if (parentElement instanceof ConsiderMethod)
      {
         final ConsiderMethod considerMethod = (ConsiderMethod) parentElement;
         final ArrayList arrayList = new ArrayList();
         arrayList.addAll(considerMethod.valuesOfParameters());
         arrayList.addAll(considerMethod.valuesOfCallers());
         return arrayList.toArray();
      }
      else if (parentElement instanceof Parameter)
      {
         return new Object[] {};
      }

      return null;
   }


   /**
    * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
    */
   public Object getParent(final Object element)
   {
      if (element instanceof TraceDefinition)
      {
         return null;
      }
      else if (element instanceof CriticalTrace)
      {
         final CriticalTrace criticalTrace = (CriticalTrace) element;
         return criticalTrace.getTraceDefinition();
      }
      else if (element instanceof ConsiderTrace)
      {
         final ConsiderTrace considerTrace = (ConsiderTrace) element;
         return considerTrace.getTraceDefinition();
      }
      else if (element instanceof CriticalClass)
      {
         final CriticalClass criticalClass = (CriticalClass) element;
         return criticalClass.getTrace();
      }
      else if (element instanceof ConsiderClass)
      {
         final ConsiderClass considerClass = (ConsiderClass) element;
         return considerClass.getTrace();
      }
      else if (element instanceof CallerClass)
      {
         final CallerClass callerClass = (CallerClass) element;
         if (callerClass.getCriticalClass() != null)
         {
            return callerClass.getCriticalClass();
         }
         else
         {
            return callerClass.getConsiderMethod();
         }
      }
      else if (element instanceof ConsiderMethod)
      {
         final ConsiderMethod considerMethod = (ConsiderMethod) element;
         return considerMethod.getParent();
      }
      else if (element instanceof Parameter)
      {
         final Parameter parameter = (Parameter) element;
         return parameter.getMethod();
      }

      return null;
   }


   /**
    * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
    */
   public boolean hasChildren(final Object element)
   {
      if (element instanceof TraceDefinition)
      {
         final TraceDefinition traceDefinition = (TraceDefinition) element;
         return traceDefinition.getCriticalTrace() != null
               || traceDefinition.getConsiderTrace() != null;
      }
      else if (element instanceof AbstractTrace)
      {
         final AbstractTrace abstractTrace = (AbstractTrace) element;
         return abstractTrace.sizeOfClasses() != 0;
      }
      else if (element instanceof CriticalClass)
      {
         final CriticalClass criticalClass = (CriticalClass) element;
         return criticalClass.sizeOfCallers() != 0;
      }
      else if (element instanceof ConsiderClass)
      {
         final ConsiderClass considerClass = (ConsiderClass) element;
         return considerClass.sizeOfMethods() != 0;
      }
      else if (element instanceof CallerClass)
      {
         return false;
      }
      else if (element instanceof ConsiderMethod)
      {
         final ConsiderMethod considerMethod = (ConsiderMethod) element;
         return considerMethod.sizeOfParameters() != 0
               || considerMethod.sizeOfCallers() != 0;
      }
      else if (element instanceof Parameter)
      {
         return false;
      }

      return false;
   }


   /**
    * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
    */
   public Object[] getElements(final Object inputElement)
   {
      return getChildren(inputElement);
   }


   /**
    * @see org.eclipse.jface.viewers.IContentProvider#dispose()
    */
   public void dispose()
   {
      if (this.traceDefinition != null)
      {
         this.traceDefinition.removeYou();
      }
   }


   /**
    * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
    *      java.lang.Object, java.lang.Object)
    */
   public void inputChanged(final Viewer viewer, final Object oldInput,
         final Object newInput)
   {
      this.traceDefinition = (TraceDefinition) newInput;
      if (oldInput != null)
      {
         ((TraceDefinition) oldInput).removeYou();
      }
   }

}
