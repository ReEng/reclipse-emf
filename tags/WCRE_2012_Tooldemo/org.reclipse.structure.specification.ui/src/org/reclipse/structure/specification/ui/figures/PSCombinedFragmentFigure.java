package org.reclipse.structure.specification.ui.figures;


import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Point;
import org.fujaba.commons.figures.LabelFigure;
import org.fujaba.commons.figures.LineFigure;
import org.fujaba.commons.figures.UMLFragmentFigure;



/**
 * This figure is an enhanced {@link UMLFragmentFigure} -- it supports additionally
 * <strong>one</strong> label figure at the bottom to show a fragment constraint (on set fragments).
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSCombinedFragmentFigure extends UMLFragmentFigure
{

   /**
    * Whether the fragment has a constraint or not.
    */
   private boolean hasConstraint;

   /**
    * The separator between the content and the constraint.
    */
   private LineFigure separator;

   /**
    * The label figure for the constraint.
    */
   private LabelFigure constraint;


   /**
    * The default constructor.
    */
   public PSCombinedFragmentFigure()
   {
      super();
      setOpaque(true);
      setLayoutManager(new XYLayout());

      separator = new LineFigure();
      hasConstraint = false;
   }


   /**
    * Adds the specified constraint label to the figure.
    * 
    * @param label The label to add.
    */
   public void addConstraint(LabelFigure label)
   {
      constraint = label;

      add(separator);
      add(constraint);

      hasConstraint = true;
   }


   /**
    * Removes the label from the figure.
    * 
    * @param label The label to remove.
    */
   public void removeConstraint(LabelFigure label)
   {
      remove(constraint);
      remove(separator);

      hasConstraint = false;
   }


   @Override
   protected void paintChildren(Graphics graphics)
   {
      if (hasConstraint)
      {
         int x = getClientArea().x;
         int y = getClientArea().y;
         int w = getClientArea().width;
         int h = getClientArea().height;

         int lh = constraint.getPreferredSize().height;

         separator.setSize(w, separator.getPreferredSize().height);
         separator.setLocation(new Point(x, y + h - 1 - 2 * lh));

         constraint.setSize(w, lh);
         constraint.setLocation(new Point(x, y + h - 2 * lh));
      }

      super.paintChildren(graphics);
   }
}
