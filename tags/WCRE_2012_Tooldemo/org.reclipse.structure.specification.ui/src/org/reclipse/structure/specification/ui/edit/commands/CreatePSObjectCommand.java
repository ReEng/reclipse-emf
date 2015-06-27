package org.reclipse.structure.specification.ui.edit.commands;


import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.fujaba.commons.Commons4EclipseFonts;
import org.fujaba.commons.edit.parts.AbstractDiagramEditPart;
import org.reclipse.metamodel.MetaModel;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.SpecificationFactory;
import org.reclipse.structure.specification.ui.utils.PSConstants;
import org.reclipse.structure.specification.util.ModelHelper;



/**
 * This command is used to create a new {@link PSObject}.
 * 
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class CreatePSObjectCommand extends
      AbstractCreatePSCombinedFragmentItemCommand
{

   private MetaModel mm;


   /**
    * The default constructor.
    * 
    * @param host The host edit part.
    * @param request The create request.
    */
   public CreatePSObjectCommand(AbstractDiagramEditPart host, Rectangle bound)
   {
      super(host, bound, "create pattern object", true);

      // save meta model for type resolving
      if (host.getRealModel() instanceof PSPatternSpecification)
      {
         mm = ModelHelper.getMetaModel((PSPatternSpecification) host
               .getRealModel());
      }
      else if (host.getRealModel() instanceof PSCombinedFragment)
      {
         mm = ModelHelper.getMetaModel(((PSCombinedFragment) host
               .getRealModel()).getPatternSpecification());
      }
   }


   @Override
   protected PSObject getModel()
   {
      return (PSObject) modelElement;
   }


   @Override
   public void redoModel()
   {
      // create model element
      if (modelElement == null)
      {
         PSObject obj = SpecificationFactory.eINSTANCE.createPSObject();

         obj.setName(PSConstants.DEFAULT__MODEL_NAME_PREFIX__OBJECT
               + (getNumberOfNodes(true) + 1));
         obj.setWeight(PSConstants.DEFAULT_WEIGHT);

         // set the element as instance of
         obj.setInstanceOf(ModelHelper.getFirstType(mm));

         modelElement = obj;
      }

      // resize
      setMinimumSize();

      // add to pattern
      pattern.getNodes().add(getModel());

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
    * This method sets the minimum size for the created {@link PSObject} by calculating the
    * estimated text size.
    */
   protected void setMinimumSize()
   {
      // resize
      StringBuffer text = new StringBuffer(getModel().getName() + ":");//$NON-NLS-1$
      if (getModel().getInstanceOf() != null)
      {
         text.append(getModel().getInstanceOf().getName());
      }
      else
      {
         text.append("null");//$NON-NLS-1$
      }
      Dimension min = TextUtilities.INSTANCE.getStringExtents(text.toString(),
            Commons4EclipseFonts
                  .getFont(Commons4EclipseFonts.FONT_DEFAULT_BOLD));
      setMinHeight(min.height + 7);
      setMinWidth(min.width + 14);
   }
}
