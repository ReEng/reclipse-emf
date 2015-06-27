package org.reclipse.structure.specification.navigator;


import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;


/**
 * This label provider handles {@link PSNavigatorItem}s to show the adequate texts and images.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSNavigatorLabelProvider extends LabelProvider
{

   @Override
   public Image getImage(Object element)
   {
      // return the image when adequate type
      if (element instanceof PSNavigatorItem)
      {
         return ((PSNavigatorItem) element).getImage();
      }

      return super.getImage(element);
   }


   @Override
   public String getText(Object element)
   {
      // return the text when adequate type
      if (element instanceof PSNavigatorItem)
      {
         return ((PSNavigatorItem) element).getText();
      }

      return super.getText(element);
   }
}
