package org.reclipse.structure.specification.ui.palette;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.requests.CreationFactory;
import org.fujaba.commons.FujabaCommonsPlugin;
import org.fujaba.commons.utils.Class2EClassRegistry;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.ui.utils.PSConstants;



/**
 * TODO: describe type
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSCombinedFragmentCreationFactory implements CreationFactory
{

   private EClass type;

   private ModifierType kind;


   /**
    * The default constructor.
    * 
    * @param jClass The class.
    */
   public PSCombinedFragmentCreationFactory(Class<?> jClass, ModifierType kind)
   {
      this.kind = kind;

      if (jClass == null)
      {
         throw new IllegalArgumentException(
               "The template argument of a creation factory must not be null and it"
                     + " must be assignable from EObject.");
      }

      EClass eClass = Class2EClassRegistry.getEClass(jClass);

      if (eClass == null)
      {
         FujabaCommonsPlugin
               .getDefault()
               .logError(
                     "Could not find the corresponding EClass to "
                           + jClass
                           + ". Please, register one at Class2EClassRegistry in order make your palette work properly.");
      }
      this.type = eClass;
   }


   @Override
   public Object getNewObject()
   {
      if (type != null)
      {
         EObject newObject = EcoreUtil.create(type);

         if (newObject instanceof PSCombinedFragment)
         {
            PSCombinedFragment fragment = ((PSCombinedFragment) newObject);
            fragment.setKind(kind);
            fragment.setWeight(PSConstants.DEFAULT_WEIGHT);
         }

         return newObject;
      }

      return null;
   }


   @Override
   public Object getObjectType()
   {
      return type.getInstanceClass();
   }
}
