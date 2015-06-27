package org.reclipse.structure.specification.descriptor;


import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.reclipse.metamodel.MetaModel;
import org.reclipse.structure.specification.PSCatalog;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.util.ModelHelper;


/**
 * This class provides the property descriptor for the {@link PSObject#getInstanceOf() instance of}
 * feature.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSObjectInstanceOfPropertyDescriptor extends
      ItemPropertyDescriptor
{

   private HashSet<EClass> types;


   /**
    * The default constructor.
    * 
    * @see org.eclipse.emf.edit.provider.ItemPropertyDescriptor
    */
   public PSObjectInstanceOfPropertyDescriptor(AdapterFactory adapterFactory,
         ResourceLocator resourceLocator, String displayName,
         String description, EStructuralFeature feature, boolean isSettable,
         boolean multiLine, boolean sortChoices, Object staticImage,
         String category, String[] filterFlags)
   {
      super(adapterFactory, resourceLocator, displayName, description, feature,
            isSettable, multiLine, sortChoices, staticImage, category,
            filterFlags);
   }


   /**
    * Collects the available types from the package specifications in the meta model.
    * 
    * @param object The object for which to filter.
    * @return Returns the collected types.
    */
   @Override
   public Collection<EClass> getComboBoxObjects(Object object)
   {
      if (types == null)
      {
         // check type
         if (object instanceof PSObject)
         {
            // create list
            types = new HashSet<EClass>();

            // get package registry
            Registry reg = getEditingDomain(object).getResourceSet()
                  .getPackageRegistry();

            // get meta model
            PSCatalog catalog = ((PSObject) object).getPatternSpecification()
                  .getCatalog();
            MetaModel mm = ModelHelper.getMetaModel(catalog);

            // add all types from the packages
            for (String uri : mm.getPackages())
            {
               if (reg.containsKey(uri))
               {
                  addTypes(reg.getEPackage(uri));
               }
            }
         }
      }

      return types;
   }


   private void addTypes(EPackage pack)
   {
      for (EClassifier type : pack.getEClassifiers())
      {
         if (type != null && type instanceof EClass)
         {
            types.add((EClass) type);
         }
      }
   }
}
