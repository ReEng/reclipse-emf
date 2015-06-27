package org.reclipse.structure.specification.ui.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.fujaba.commons.Commons4EclipseFonts;
import org.fujaba.commons.figures.LabelFigure;
import org.fujaba.commons.figures.LineBorderedRectangleFigure;



/**
 * @author    mm
 * @version   $Revision: 3383 $ $Date: 2007-02-16 16:35:31 +0100 (Fri, 16 Feb 2007) $
 */
public class PSCatalogNodeFigure extends LineBorderedRectangleFigure
{
   /**
    * No comment provided by developer, please add a comment to improve documentation.
    */
   private LabelFigure stereotype = new LabelFigure();
   /**
    * No comment provided by developer, please add a comment to improve documentation.
    */
   private LabelFigure label = new LabelFigure();


   /**
    *Constructor for class PSCatalogNodeFigure
    */
   public PSCatalogNodeFigure()
   {
      ToolbarLayout layout = new ToolbarLayout();
      layout.setMinorAlignment (ToolbarLayout.ALIGN_CENTER);
      layout.setVertical (true);

      setLayoutManager (layout);
      setOpaque (true);
      setBackgroundColor (ColorConstants.white);
      setForegroundColor (ColorConstants.black);

      add (stereotype);
      label.setFont (Commons4EclipseFonts.getFont (Commons4EclipseFonts.FONT_DEFAULT_BOLD));
      add (label);

      Rectangle rec = getBounds();
      translateToAbsolute (rec);
   }


   /**
    * Sets the label attribute of the PSCatalogNodeFigure object
    *
    * @param value  The new label value
    */
   public void setLabel (String value)
   {
      label.setText (value);
   }


   /**
    * Sets the abstract attribute of the PSCatalogNodeFigure object
    *
    * @param value  The new abstract value
    */
   public void setAbstract (boolean value)
   {
      if (value)
      {
         label.setFont (Commons4EclipseFonts.getFont (Commons4EclipseFonts.FONT_DEFAULT_BOLD_ITALIC));
      }
      else
      {
         label.setFont (Commons4EclipseFonts.getFont (Commons4EclipseFonts.FONT_DEFAULT_BOLD));
      }
   }


   /**
    * Sets the stereotype attribute of the PSCatalogNodeFigure object
    *
    * @param value  The new stereotype value
    */
   public void setStereotype (String value)
   {
      stereotype.setText ("\u00ab" + value + "\u00bb");
   }


   /**
    * Get the preferredSize attribute of the PSCatalogNodeFigure object
    *
    * @param wHint  No description provided
    * @param hHint  No description provided
    * @return       The preferredSize value
    */
   @Override
   public Dimension getPreferredSize (int wHint, int hHint)
   {
      Dimension size = super.getPreferredSize (wHint, hHint);
      return new Dimension (size.width + 10, size.height);
   }
}

/*
 * $Log$
 * Revision 1.6  2006/02/15 18:38:24  mm
 * adapted to Commons4Eclipse refactoring
 *
 */
