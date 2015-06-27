package org.reclipse.structure.specification.ui.edit.parts;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.fujaba.commons.notation.DiagramElement;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSAttributeConstraint;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSFuzzyMetricConstraint;
import org.reclipse.structure.specification.PSFuzzySetRatingConstraint;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.PSMetricConstraint;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.PSPath;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.PSSpecificationConstraint;



/**
 * This is the edit part factory for the pattern specification editor.
 * 
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSEditPartFactory implements EditPartFactory
{

   @Override
   public EditPart createEditPart(EditPart context, Object model)
   {
      EObject realModel = null;
      DiagramElement view = null;
      EditPart part = null;

      if (model instanceof DiagramElement)
      {
         view = (DiagramElement) model;
         realModel = view.getModel();
      }
      else
      {
         return null;
      }

      if (realModel instanceof PSPatternSpecification)
      {
         part = new PSPatternSpecificationEditPart();
      }
      else if (realModel instanceof PSLink)
      {
         part = new PSLinkEditPart();
      }
      else if (realModel instanceof PSPath)
      {
         part = new PSPathEditPart();
      }
      else if (realModel instanceof PSAnnotation)
      {
         part = new PSAnnotationEditPart();
      }
      else if (realModel instanceof PSObject)
      {
         part = new PSObjectEditPart();
      }
      else if (realModel instanceof PSSpecificationConstraint)
      {
         part = new PSSpecificationConstraintEditPart();
      }
      else if (realModel instanceof PSCombinedFragment)
      {
         part = new PSCombinedFragmentEditPart();
      }
      else if (realModel instanceof PSAttributeConstraint)
      {
         part = new PSAttributeConstraintEditPart();
      }
      else if (realModel instanceof PSMetricConstraint)
      {
         part = new PSMetricConstraintEditPart();
      }
      else if (realModel instanceof PSFuzzyMetricConstraint
            || realModel instanceof PSFuzzySetRatingConstraint)
      {
         part = new PSFuzzyConstraintEditPart();
      }

      if (view != null && part != null)
      {
         part.setModel(view);
      }
      return part;
   }
}
