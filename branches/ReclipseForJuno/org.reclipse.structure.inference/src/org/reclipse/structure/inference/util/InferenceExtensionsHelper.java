package org.reclipse.structure.inference.util;


import java.io.InvalidClassException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.reclipse.structure.inference.IAnnotationEvaluator;


/**
 * This utility class provides access to all registered inference related extensions.
 * 
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author harka
 */
public final class InferenceExtensionsHelper
{
   private static final String EPID_EVALUATORS = "org.reclipse.structure.inference.evaluator"; //$NON-NLS-1$

   private static final String ID = "id"; //$NON-NLS-1$

   private static final String NAME = "name"; //$NON-NLS-1$

   private static final String DESC = "description"; //$NON-NLS-1$

   private static final String CLASS = "class"; //$NON-NLS-1$


   private static List<AnnotationEvaluatorItem> evaluators;


   private InferenceExtensionsHelper()
   {
      // hide constructor
   }


   /**
    * Collects all registered annotation evaluators with their name and their identifier and returns
    * them ordered alphabetically.
    * 
    * @return Returns all registered annotation evaluator items alphabetically.
    */
   public static List<AnnotationEvaluatorItem> getRegisteredEvaluators()
   {
      if (evaluators == null)
      {
         evaluators = new LinkedList<AnnotationEvaluatorItem>();
         // fetch the extension point configurations
         for (IConfigurationElement ce : Platform.getExtensionRegistry()
               .getConfigurationElementsFor(EPID_EVALUATORS))
         {
            try
            {
               // deliver simple attributes
               String id = ce.getAttribute(ID);
               String name = ce.getAttribute(NAME);

               // deliver description when possible
               String desc = null;
               IConfigurationElement[] descContainer = ce.getChildren(DESC);
               if (descContainer.length == 1)
               {
                  desc = descContainer[0].getValue();
               }

               // get class
               Object strategy = ce.createExecutableExtension(CLASS);
               if (!(strategy instanceof IAnnotationEvaluator))
               {
                  throw new InvalidClassException(
                        "The class has to implement IAnnotationEvaluator.");
               }

               // create evaluator
               AnnotationEvaluatorItem item = new AnnotationEvaluatorItem(id,
                     name, desc, (IAnnotationEvaluator) strategy);

               // add the model
               if (evaluators.contains(item))
               {
                  // TODO: throw it in a logger instead?
                  System.out
                        .println("Two annotation evaluators with the same ID wanted to be registered: duplicate ignored.");
               }
               else
               {
                  evaluators.add(item);
               }
            }
            catch (Exception e)
            {
               // TODO: throw it in a logger instead?
               System.out
                     .println("Error on reading the registered annotation evaluator.");
            }
         }

         if (evaluators.isEmpty())
         {
            // log when no inference strategy registered
            // TODO: throw it in a logger instead?
            System.out.println("No annotation evaluator registered.");
         }
         else
         {
            // otherwise sort them
            Collections.sort(evaluators);
         }
      }

      return evaluators;
   }

   /**
    * This class is a tiny wrapper around an {@link IAnnotationEvaluator}, which allows access to
    * the evaluator class itself, as well as its name and a unique identifier.
    * 
    * @version $Revision$ $Date$
    * @author Last editor: $Author$
    * @author harka
    */
   public static class AnnotationEvaluatorItem implements
         Comparable<AnnotationEvaluatorItem>
   {

      private final String id;

      private final String name;

      private final String description;

      private final IAnnotationEvaluator evaluator;


      /**
       * Default constructor.
       * 
       * @param id The unique ID of the evaluator.
       * @param name The name of the evaluator.
       * @param description An optional description.
       * @param evaluator The evaluator.
       */
      public AnnotationEvaluatorItem(String id, String name,
            String description, IAnnotationEvaluator evaluator)
      {
         this.id = id;
         this.name = name;

         if (description == null || description.length() < 1)
         {
            this.description = "No description available.";
         }
         else
         {
            this.description = description;
         }
         this.evaluator = evaluator;
      }


      /**
       * Getter of the identifier for this evaluator.
       * 
       * @return Returns the unique ID.
       */
      public String getID()
      {
         return id;
      }


      /**
       * Getter of the name for this evaluator.
       * 
       * @return Returns the name.
       */
      public String getName()
      {
         return name;
      }


      /**
       * Getter of the description for this evaluator.
       * 
       * @return Returns the description or <code>null</code>.
       */
      public String getDescription()
      {
         return description;
      }


      /**
       * Getter of the evaluator.
       * 
       * @return Returns the evaluator.
       */
      public IAnnotationEvaluator getEvaluator()
      {
         return evaluator;
      }


      @Override
      public int compareTo(AnnotationEvaluatorItem o)
      {
         return getName().compareTo(o.getName());
      }


      @Override
      public boolean equals(Object o)
      {
         if (o instanceof AnnotationEvaluatorItem)
         {
            return getID().equals(((AnnotationEvaluatorItem) o).getID());
         }

         return super.equals(o);
      }
   }
}
