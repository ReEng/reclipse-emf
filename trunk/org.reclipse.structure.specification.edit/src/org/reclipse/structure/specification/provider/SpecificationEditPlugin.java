/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.reclipse.structure.specification.provider;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.fujaba.commons.identifier.provider.IdentifierEditPlugin;


/**
 * This is the central singleton for the Specification edit plugin.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public final class SpecificationEditPlugin extends EMFPlugin
{
   /**
    * Keep track of the singleton.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final SpecificationEditPlugin INSTANCE = new SpecificationEditPlugin();

   /**
    * Keep track of the singleton.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private static Implementation plugin;

   /**
    * Create the instance.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public SpecificationEditPlugin()
   {
      super
        (new ResourceLocator [] 
         {
           IdentifierEditPlugin.INSTANCE,
         });
   }

   /**
    * Returns the singleton instance of the Eclipse plugin.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the singleton instance.
    * @generated
    */
   @Override
   public ResourceLocator getPluginResourceLocator()
   {
      return plugin;
   }

   /**
    * Returns the singleton instance of the Eclipse plugin.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the singleton instance.
    * @generated
    */
   public static Implementation getPlugin()
   {
      return plugin;
   }

   /**
    * The actual implementation of the Eclipse <b>Plugin</b>.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static class Implementation extends EclipsePlugin
   {
      /**
       * Creates an instance.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      public Implementation()
      {
         super();

         // Remember the static instance.
         //
         plugin = this;
      }
   }

}
