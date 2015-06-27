package org.reclipse.structure.inference.ui.views.annotations.filtering;


import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;


public class StatusEditingSupport extends EditingSupport
{

   public StatusEditingSupport(TableViewer viewer)
   {
      super(viewer);
   }


   @Override
   protected CellEditor getCellEditor(Object element)
   {
      return new CheckboxCellEditor(((TableViewer) getViewer()).getTable(),
            SWT.CHECK | SWT.READ_ONLY);
   }


   @Override
   protected boolean canEdit(Object element)
   {
      return true;
   }


   @Override
   protected Object getValue(Object element)
   {
      return ((AnnotationFilterItem) element).isHidden();
   }


   @Override
   protected void setValue(Object element, Object value)
   {
      ((AnnotationFilterItem) element).setHidden((Boolean) value);
      getViewer().update(element, null);
   }
}