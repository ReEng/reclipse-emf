package org.reclipse.structure.specification.ui.utils;


import org.fujaba.commons.utils.Class2EClassRegistry;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.impl.PSCombinedFragmentImpl;
import org.reclipse.structure.specification.impl.PSAnnotationImpl;
import org.reclipse.structure.specification.impl.PSAttributeConstraintImpl;
import org.reclipse.structure.specification.impl.PSCatalogImpl;
import org.reclipse.structure.specification.impl.PSFunctionParameterImpl;
import org.reclipse.structure.specification.impl.PSLinkImpl;
import org.reclipse.structure.specification.impl.PSObjectImpl;
import org.reclipse.structure.specification.impl.PSPathImpl;
import org.reclipse.structure.specification.impl.PSPatternSpecificationImpl;
import org.reclipse.structure.specification.impl.PSSpecificationConstraintImpl;



/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public abstract class ModelRegistrator
{
   public static void initialize()
   {
      SpecificationPackage pack = SpecificationPackage.eINSTANCE;
      Class2EClassRegistry.registerClass(PSCatalogImpl.class, pack.getPSCatalog());
      Class2EClassRegistry.registerClass(PSPatternSpecificationImpl.class, pack.getPSPatternSpecification());

      Class2EClassRegistry.registerClass(PSLinkImpl.class, pack.getPSLink());
      Class2EClassRegistry.registerClass(PSPathImpl.class, pack.getPSPath());

      Class2EClassRegistry.registerClass(PSObjectImpl.class, pack.getPSObject());
      Class2EClassRegistry.registerClass(PSAnnotationImpl.class, pack.getPSAnnotation());
      Class2EClassRegistry.registerClass(PSCombinedFragmentImpl.class, pack.getPSCombinedFragment());
      Class2EClassRegistry.registerClass(PSSpecificationConstraintImpl.class, pack.getPSSpecificationConstraint());

      Class2EClassRegistry.registerClass(PSFunctionParameterImpl.class, pack.getPSFunctionParameter());
      Class2EClassRegistry.registerClass(PSAttributeConstraintImpl.class, pack.getPSAttributeConstraint());
   }
}
