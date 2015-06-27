package org.reclipse.tracer.symbolicexecution.ui.launching;

import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class SelectMethodDialogContentProvider implements IStructuredContentProvider
{
   public Object[] getElements(Object inputElement)
   {
      if (inputElement instanceof IType)
      {
         try
         {
            return ((IType) inputElement).getMethods();
         }
         catch (JavaModelException e)
         {
            e.printStackTrace();
         }
      }
      return null;
   }


   public void inputChanged(Viewer viewer, Object oldInput,
         Object newInput)
   {
   }


   public void dispose()
   {
   }
}
