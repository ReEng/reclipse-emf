package org.reclipse.structure.inference.ui.views.annotations.filtering;


import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;


public class ThresholdEditingSupport extends EditingSupport
{

   public ThresholdEditingSupport(TableViewer viewer)
   {
      super(viewer);
   }


   @Override
   protected CellEditor getCellEditor(Object element)
   {
      return new TextCellEditor(((TableViewer) getViewer()).getTable());
   }


   @Override
   protected boolean canEdit(Object element)
   {
      return true;
   }


   @Override
   protected Object getValue(Object element)
   {
      return String.valueOf(((AnnotationFilterItem) element).getThreshold());
   }


   @Override
   protected void setValue(Object element, Object text)
   {
      try
      {
         double val = Double.valueOf((String) text);
         if (val <= 100 && val >= 0)
         {
            ((AnnotationFilterItem) element).setThreshold(val);
            getViewer().update(element, null);
         }
      }
      catch (NumberFormatException e)
      {
         // wrong input
      }
   }
}