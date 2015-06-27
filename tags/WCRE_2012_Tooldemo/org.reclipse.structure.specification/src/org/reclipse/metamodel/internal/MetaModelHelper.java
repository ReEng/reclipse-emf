package org.reclipse.metamodel.internal;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;
import org.reclipse.metamodel.AbstractElementLabeler;
import org.reclipse.metamodel.IASTPreparer;
import org.reclipse.metamodel.ITriggerChooser;
import org.reclipse.metamodel.MetaModel;


public final class MetaModelHelper
{
   private static final String KEY_METAMODEL = "org.reclipse.structure.specification.metamodel"; //$NON-NLS-1$

   private static final String KEY_ID = "id"; //$NON-NLS-1$

   private static final String KEY_NAME = "name"; //$NON-NLS-1$

   private static final String KEY_VERSION = "version"; //$NON-NLS-1$

   private static final String KEY_DESCRIPTION = "description"; //$NON-NLS-1$

   private static final String KEY_TRIGGERER = "trigger_chooser"; //$NON-NLS-1$

   private static final String KEY_LABELER = "labeler"; //$NON-NLS-1$

   private static final String KEY_PREPARER = "ast_preparer"; //$NON-NLS-1$

   private static final String KEY_PACKAGE = "package"; //$NON-NLS-1$

   private static final String KEY_URI = "uri"; //$NON-NLS-1$


   private static final ITriggerChooser DEFAULT_CHOOSER = new ITriggerChooser()
   {
      @Override
      public EClass getTrigger(Collection<EClass> possibilities)
      {
         if (possibilities != null && !possibilities.isEmpty())
         {
            for (EClass type : possibilities)
            {
               return type;
            }
         }

         return null;
      }
   };

   private static final AbstractElementLabeler DEFAULT_LABELER = new AbstractElementLabeler()
   {
      @Override
      public String getFullText(EObject element)
      {
         return getText(element);
      }


      @Override
      public Image getImage(EObject element)
      {
         return null;
      }


      @Override
      public String getText(EObject element)
      {
         return element.toString();
      }
   };

   private static Map<String, MetaModel> models;


   public static Map<String, MetaModel> getModels()
   {
      if (models == null)
      {
         models = new HashMap<String, MetaModel>();

         // go through all registered extensions
         for (IConfigurationElement ce : Platform.getExtensionRegistry()
               .getConfigurationElementsFor(KEY_METAMODEL))
         {
            // deliver simple attributes
            String id = ce.getAttribute(KEY_ID);
            String name = ce.getAttribute(KEY_NAME);
            String description = ce.getAttribute(KEY_DESCRIPTION);
            String version = ce.getAttribute(KEY_VERSION);

            // create model
            MetaModel model = new MetaModel(id, name, description, version);

            // get trigger chooser
            if (ce.getAttribute(KEY_TRIGGERER) != null)
            {
               try
               {
                  Object elem = ce.createExecutableExtension(KEY_TRIGGERER);
                  if (elem instanceof ITriggerChooser)
                  {
                     model.setTriggerChooser((ITriggerChooser) elem);
                  }
               }
               catch (CoreException e)
               {
                  // default will be added later
               }
            }

            // get labeler
            if (ce.getAttribute(KEY_LABELER) != null)
            {
               try
               {
                  Object elem = ce.createExecutableExtension(KEY_LABELER);
                  if (elem instanceof AbstractElementLabeler)
                  {
                     model.setLabeler((AbstractElementLabeler) elem);
                  }
               }
               catch (CoreException e)
               {
                  // default will be added later
               }
            }

            // get AST preparer
            if (ce.getAttribute(KEY_PREPARER) != null)
            {
               try
               {
                  Object elem = ce.createExecutableExtension(KEY_PREPARER);
                  if (elem instanceof IASTPreparer)
                  {
                     model.setASTPreparer((IASTPreparer) elem);
                  }
               }
               catch (CoreException e)
               {
                  // default will be added later
               }
            }

            // add packages
            for (IConfigurationElement pack : ce.getChildren(KEY_PACKAGE))
            {
               model.addPackage(pack.getAttribute(KEY_URI));
            }

            // add the model
            if (isValid(model))
            {
               models.put(id, model);
            }
            else
            {
               // TODO: throw to log
            }
         }
      }

      return models;
   }


   private static boolean isValid(MetaModel model)
   {
      // check for package uri existence
      if (model.getPackages().isEmpty())
      {
         return false;
      }

      // check labeler
      if (model.getLabeler() == null)
      {
         // assign default labeler
         model.setLabeler(DEFAULT_LABELER);
      }

      // check for trigger chooser
      if (model.getTriggerChooser() == null)
      {
         // assign default
         model.setTriggerChooser(DEFAULT_CHOOSER);
      }

      return true;
   }
}
