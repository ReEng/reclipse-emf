package org.reclipse.structure.inference.ui.views.annotations.filtering;


import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;


public class AnnotationFilterComparator extends ViewerComparator
{
   private static final int DESCENDING = 1;

   private int index;

   private int direction;


   public AnnotationFilterComparator()
   {
      index = 0;
      direction = DESCENDING;
   }


   public void setColumn(int column)
   {
      if (column == index)
      {
         // same column as last sort, toggle the direction
         direction = 1 - direction;
      }
      else
      {
         // new column, do an ascending sort
         this.index = column;
         direction = DESCENDING;
      }
   }


   @Override
   public int compare(Viewer viewer, Object e1, Object e2)
   {
      AnnotationFilterItem p1 = (AnnotationFilterItem) e1;
      AnnotationFilterItem p2 = (AnnotationFilterItem) e2;

      int rc = 0;
      switch (index)
      {
         case 0:
            if (p1.isHidden() == p2.isHidden())
            {
               rc = 0;
            }
            else
            {
               rc = (p1.isHidden() ? 1 : -1);
            }
            break;
         case 1:
            rc = p2.getName().compareToIgnoreCase(p1.getName());
            break;
         case 2:
            rc = Double.compare(p1.getThreshold(), p2.getThreshold());
            break;
         case 3:
            if (p1.isAvailable() == p2.isAvailable())
            {
               rc = 0;
            }
            else
            {
               rc = (p1.isAvailable() ? 1 : -1);
            }
            break;
         default:
            rc = 0;
      }

      // if descending order, flip the direction
      if (direction == DESCENDING)
      {
         rc = -rc;
      }

      return rc;
   }
}
