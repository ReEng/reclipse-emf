package org.reclipse.structure.inference.ui.matching.edit.parts.object;


import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.graphics.Color;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.ui.edit.parts.PSLinkEditPart;
import org.reclipse.structure.specification.ui.utils.PSConstants;
import org.reclipse.structure.specification.util.ModelHelper;


public class MatchingPSLinkEditPart extends PSLinkEditPart
{

   @Override
   public void refreshVisuals()
   {
      super.refreshVisuals();

      PSLink link = getRealModel();
      if (link.getSource().getModifier() != ModifierType.SET)
      {
         StringBuilder text = new StringBuilder();

         if (ModelHelper.isCreate(link))
         {
            // append qualifier
            if (link.getQualifier() != null && !link.getQualifier().isEmpty())
            {
               text.append(link.getQualifier());
            }
         }
         else
         {
            if (link.getInstanceOf() != null
                  && link.getInstanceOf().getName().equals("annotatedElements"))
            {
               text.append(link.getQualifier());
            }
            else
            {
               // append reference name
               if (link.getInstanceOf() != null)
               {
                  text.append(link.getInstanceOf().getName());
               }
               else
               {
                  text.append(PSConstants.LABEL_NO_RANGE);
               }
            }
         }

         placeLabel(text.toString());

         Color def = ColorConstants.black;
         placeReadingDirectionArrow(true, def, def);
         connection.setForegroundColor(def);
      }else {
         placeLabel(" ");
      }
   }
}
