package org.reclipse.structure.specification.ui.edit.commands;


import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.fujaba.commons.Commons4EclipseFonts;
import org.fujaba.commons.edit.parts.AbstractDiagramEditPart;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.SpecificationFactory;
import org.reclipse.structure.specification.ui.utils.PSConstants;
import org.reclipse.structure.specification.util.ModelHelper;



/**
 * This command is used to create a new {@link PSAnnotation}.
 * 
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class CreatePSAnnotationCommand extends
      AbstractCreatePSCombinedFragmentItemCommand
{

   /**
    * The default constructor.
    * 
    * @param host The host edit part.
    * @param request The create request.
    */
   public CreatePSAnnotationCommand(AbstractDiagramEditPart host,
         Rectangle bound)
   {
      super(host, bound, "create pattern annotation", true);
   }


   @Override
   protected PSAnnotation getModel()
   {
      return (PSAnnotation) modelElement;
   }


   @Override
   public void redoModel()
   {
      // create element
      if (modelElement == null)
      {
         PSAnnotation anno = SpecificationFactory.eINSTANCE
               .createPSAnnotation();

         anno.setName(PSConstants.DEFAULT__MODEL_NAME_PREFIX__ANNOTATION
               + (getNumberOfNodes(false) + 1));
         anno.setWeight(PSConstants.DEFAULT_WEIGHT);

         modelElement = anno;
      }

      // add to pattern
      pattern.getNodes().add(getModel());
      getModel().setType(ModelHelper.getFirstPatternSpecification(getModel()));

      // resize
      setMinimumSize();

      // add to direct parent fragment
      if (fragment != null)
      {
         fragment.getChildren().add(getModel());

         // add all fragment parents, too
         if (fragment.getParents().size() > 0)
         {
            for (PSCombinedFragment parent : fragment.getParents())
            {
               parent.getChildren().add(getModel());
            }
         }
      }
   }


   @Override
   public void undoModel()
   {
      // remove from direct parent fragment
      if (fragment != null)
      {
         // remove from all parent fragment, too
         if (fragment.getParents().size() > 0)
         {
            for (PSCombinedFragment parent : fragment.getParents())
            {
               parent.getChildren().remove(getModel());
            }
         }

         fragment.getChildren().remove(getModel());
      }

      // remove from pattern specification
      pattern.getNodes().remove(getModel());
   }


   /**
    * This method sets the minimum size for the created {@link PSAnnotation} by calculating the
    * estimated text size.
    */
   protected void setMinimumSize()
   {
      StringBuffer text = new StringBuffer();
      if (!ModelHelper.isCreate(getModel()))
      {
         text.append(getModel().getName());
      }
      if (getModel().getType() != null)
      {
         text.append(":" + getModel().getType().getName());//$NON-NLS-1$
      }
      else
      {
         text.append("null");//$NON-NLS-1$
      }
      Dimension min = TextUtilities.INSTANCE.getStringExtents(text.toString(),
            Commons4EclipseFonts
                  .getFont(Commons4EclipseFonts.FONT_DEFAULT_BOLD));
      setMinHeight(min.height + 13);
      setMinWidth((int) ((double) min.width * ((double) (min.height) / 10d)));
   }
}
