package org.reclipse.structure.specification.ui.properties.sections;


import org.eclipse.emf.ecore.EStructuralFeature;
import org.fujaba.commons.properties.section.AbstractXtextSection;
import org.reclipse.structure.specification.PSSpecificationConstraint;
import org.reclipse.structure.specification.SpecificationPackage;
import org.storydriven.storydiagrams.expressions.common.ui.internal.ExpressionsActivator;

import com.google.inject.Injector;


/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSSpecificationConstraintExpressionSection extends
      AbstractXtextSection
{
   private static final String INJECTOR_ID = "org.storydriven.modeling.expressions.common.Expressions"; //$NON-NLS-1$


   @Override
   protected Injector getInjector()
   {
      return ExpressionsActivator.getInstance().getInjector(INJECTOR_ID);
   }


   @Override
   protected String getFeatureAsString()
   {
      String string = getElement().getExpression();
      if (string == null)
      {
         return ""; //$NON-NLS-1$
      }
      return string;
   }


   @Override
   protected Object getNewFeatureValue(String newText)
   {
      return getText();
   }


   @Override
   protected Object getOldFeatureValue()
   {
      return getFeatureAsString();
   }


   @Override
   protected EStructuralFeature getFeature()
   {
      return SpecificationPackage.Literals.PS_SPECIFICATION_CONSTRAINT__EXPRESSION;
   }


   @Override
   protected PSSpecificationConstraint getElement()
   {
      return (PSSpecificationConstraint) super.getElement();
   }


   @Override
   protected String getLabelText()
   {
      return "Expression";
   }
}
