package org.reclipse.structure.specification.ui.figures;


import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.fujaba.commons.figures.LabelFigure;
import org.fujaba.commons.figures.LineBorderedEllipseContainer;
import org.fujaba.commons.figures.LineBorderedEllipseFigure;
import org.fujaba.commons.figures.ShadowDecoratorFigure;



/**
 * @author Dietrich Travkin (travkin)
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSAnnotationFigure extends LineBorderedEllipseContainer
{

   private final PSAnnotationRectangleFigure annoContentsFigure;

   private boolean negative;

   private ShadowDecoratorFigure shadowDecorator;

   private boolean shadowed = false;


   public PSAnnotationFigure()
   {
      this(new PSAnnotationRectangleFigure());
   }


   public PSAnnotationFigure(IFigure contents)
   {
      super(contents);

      this.annoContentsFigure = (PSAnnotationRectangleFigure) this
            .getContents();

      setBackgroundColor(ColorConstants.white);
      setForegroundColor(ColorConstants.black);
   }


   public void addToSetConstraints(LabelFigure setSizeExprLabel)
   {
      this.annoContentsFigure.addToSetConstraints(setSizeExprLabel);
   }


   public String getName()
   {
      return this.annoContentsFigure.getName();
   }


   public boolean isDashedLine()
   {
      return (getBorderLineStyle() == SWT.LINE_DASH);
   }


   public boolean isNegative()
   {
      return this.negative;
   }


   @Override
   public void paint(Graphics graphics)
   {
      super.paint(graphics);

      if (this.isNegative())
      {
         Rectangle bounds = getBounds();
         graphics.drawLine(bounds.x, bounds.y + bounds.height, bounds.x
               + bounds.width, bounds.y);
         graphics.drawLine(bounds.x, bounds.y, bounds.x + bounds.width,
               bounds.y + bounds.height);
      }
   }


   public void removeFromSetConstraints(LabelFigure setSizeExprLabel)
   {
      this.annoContentsFigure.removeFromSetSizeExpressions(setSizeExprLabel);
   }


   public void setAbstract(boolean isAbstract)
   {
      this.annoContentsFigure.setAbstract(isAbstract);
   }


   @Override
   public void setBackgroundColor(Color bg)
   {
      this.annoContentsFigure.setBorderColor(bg);
      super.setBackgroundColor(bg);
   }


   public void setCreate(boolean create)
   {
      this.annoContentsFigure.setCreate(create);

      if (create)
      {
         setForegroundColor(ColorConstants.darkGreen);
         setBorderColor(ColorConstants.darkGreen);
      }
      else
      {
         setForegroundColor(ColorConstants.black);
         setBorderColor(ColorConstants.black);
      }
   }


   public void setDashedLine(boolean dashedLine)
   {
      if (dashedLine)
      {
         setBorderLineStyle(SWT.LINE_DASH);
      }
      else
      {
         setBorderLineStyle(SWT.LINE_SOLID);
      }
   }


   public void setName(String name)
   {
      this.annoContentsFigure.setName(name);
   }


   public void setNegative(boolean negative)
   {
      if (this.negative != negative)
      {
         this.negative = negative;

         repaint();
      }
   }


   public void setShadowed(boolean shadowed)
   {
      if (this.shadowed != shadowed)
      {
         this.shadowed = shadowed;
         if (this.shadowed)
         {
            LineBorderedEllipseFigure shadow = new LineBorderedEllipseFigure();
            shadow.setBackgroundColor(ColorConstants.white);
            shadow.setBorderLineStyle(SWT.LINE_DASH);
            shadow.setOpaque(true);
            this.shadowDecorator = new ShadowDecoratorFigure(this, shadow,
                  ShadowDecoratorFigure.DEFAULT_OFFSET,
                  -ShadowDecoratorFigure.DEFAULT_OFFSET);
            this.shadowDecorator.setOpaque(true);
         }
         else
         {
            if (this.shadowDecorator != null)
            {
               this.shadowDecorator.removeDecorator();
               this.shadowDecorator = null;
            }
         }
      }
   }


   public void setTrigger(boolean trigger)
   {
      if (trigger)
      {
         setBorderWidth(2);
      }
      else
      {
         setBorderWidth(1);
      }
   }


   public void setWeightText(String text)
   {
      this.annoContentsFigure.setWeightText(text);
   }
}
