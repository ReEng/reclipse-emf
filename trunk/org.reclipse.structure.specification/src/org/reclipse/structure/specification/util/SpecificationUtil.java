package org.reclipse.structure.specification.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.reclipse.metamodel.MetaModel;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSBooleanConstraint;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSConnection;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSNodeConstraint;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.PSPath;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.PSSpecificationConstraint;


public class SpecificationUtil
{
   private SpecificationUtil()
   {
      // hide constructor
   }


   public static boolean isCore(PSNode node)
   {
      boolean isCreate = node instanceof PSAnnotation && isCreate((PSAnnotation) node);
      boolean isDirectNegativeNode = ModifierType.NEGATIVE.equals(node.getModifier()) && node.getParents().isEmpty();

      return !isCreate && (isNormal(node) || isDirectNegativeNode);
   }


   public static Collection<PSAnnotation> getAnnotations(PSPatternSpecification pattern)
   {
      if (pattern == null)
      {
         return Collections.emptyList();
      }

      Collection<PSAnnotation> annotations = new ArrayList<PSAnnotation>();

      for (PSNode node : pattern.getNodes())
      {
         if (node instanceof PSAnnotation)
         {
            annotations.add((PSAnnotation) node);
         }
      }

      return annotations;
   }


   public static Collection<PSObject> getObjects(PSPatternSpecification pattern)
   {
      if (pattern == null)
      {
         return Collections.emptyList();
      }

      Collection<PSObject> annotations = new ArrayList<PSObject>();

      for (PSNode node : pattern.getNodes())
      {
         if (node instanceof PSObject)
         {
            annotations.add((PSObject) node);
         }
      }

      return annotations;
   }


   public static boolean isCreate(PSAnnotation annotation)
   {
      return annotation != null && annotation.getType() != null && annotation.getPatternSpecification() != null
            && annotation.getType().equals(annotation.getPatternSpecification());
   }


   public static boolean isReferencing(PSAnnotation annotation)
   {
      return !isCreate(annotation);
   }


   public static Collection<String> getAllNames(PSPatternSpecification pattern)
   {
      Collection<String> names = new HashSet<String>();

      for (PSCombinedFragment fragment : pattern.getCombinedFragments())
      {
         String name = fragment.getName();

         if (names.contains(name))
         {
            System.err.println(String.format("The pattern %1s contains multiple elements with the same name!",
                  pattern.getName()));
         }
         else
         {
            names.add(name);
         }
      }

      return names;
   }


   public static boolean isNormal(PSNode node)
   {
      return ModifierType.NONE.equals(node.getModifier()) && node.getParents().isEmpty();
   }


   public static MetaModel getMetamodel(PSPatternSpecification pattern)
   {
      return ModelHelper.getMetaModel(pattern);
   }


   public static Collection<PSPatternSpecification> getInstancePatterns(PSPatternSpecification pattern)
   {
      Collection<PSPatternSpecification> result = new ArrayList<PSPatternSpecification>();

      collectInstanceSubPatterns(result, pattern);

      return result;
   }


   private static void collectInstanceSubPatterns(Collection<PSPatternSpecification> collection,
         PSPatternSpecification pattern)
   {
      if (!pattern.isAbstract())
      {
         collection.add(pattern);
      }

      for (PSPatternSpecification subPattern : pattern.getSubPatterns())
      {
         collectInstanceSubPatterns(collection, subPattern);
      }
   }


   public static Collection<PSPatternSpecification> getInstancePatterns(PSAnnotation annotation)
   {
      return getInstancePatterns(annotation.getType());
   }


   public static Collection<PSPatternSpecification> getReferencedPatterns(PSPatternSpecification pattern)
   {
      Collection<PSPatternSpecification> result = new HashSet<PSPatternSpecification>();

      // search all referenced patterns
      for (PSNode node : pattern.getNodes())
      {
         if (node instanceof PSAnnotation && !ModelHelper.isCreate((PSAnnotation) node))
         {
            PSPatternSpecification ref = ((PSAnnotation) node).getType();

            // add referenced pattern
            result.add(ref);

            // add referenced sub pattern
            result.addAll(getInstancePatterns(ref));
         }
      }

      // remove those not reachable due to dependency
      result.removeAll(getReferencingPatterns(pattern));

      return result;
   }


   public static Set<PSPatternSpecification> getReferencingPatterns(PSPatternSpecification pattern)
   {
      Set<PSPatternSpecification> result = new HashSet<PSPatternSpecification>();

      for (PSPatternSpecification spec : pattern.getCatalog().getPatternSpecifications())
      {
         // search all referenced patterns
         for (PSNode node : spec.getNodes())
         {
            if (node instanceof PSAnnotation && ((PSAnnotation) node).getType().equals(pattern))
            {
               result.add(spec);
               result.addAll(getInstancePatterns(spec));
            }
         }
      }

      return result;
   }


   public static boolean isAdditionalElements(PSPatternSpecification pattern)
   {
      // check fragments
      for (PSCombinedFragment fragment : pattern.getCombinedFragments())
      {
         if (ModifierType.ADDITIONAL.equals(fragment.getKind()))
         {
            return true;
         }

         // check constraint
         if (fragment.getConstraint() != null && fragment.getConstraint() instanceof PSBooleanConstraint
               && ((PSBooleanConstraint) fragment.getConstraint()).isAdditional())
         {
            return true;
         }
      }

      // check nodes
      for (PSNode node : pattern.getNodes())
      {
         if (ModifierType.ADDITIONAL.equals(node.getModifier()))
         {
            return true;
         }

         // check constraints
         for (PSNodeConstraint constraint : node.getNodeConstraints())
         {
            if (constraint instanceof PSBooleanConstraint && ((PSBooleanConstraint) constraint).isAdditional())
            {
               return true;
            }
         }
      }

      // check constraints
      for (PSSpecificationConstraint constraint : pattern.getConstraints())
      {
         if (constraint.isAdditional())
         {
            return true;
         }
      }

      return false;
   }


   public static boolean isSetSearchRequired(PSPatternSpecification pattern)
   {
      for (PSCombinedFragment fragment : pattern.getCombinedFragments())
      {
         if (ModifierType.SET.equals(fragment.getKind()))
         {
            return true;
         }
      }
      return false;
   }


   public static boolean hasNegativeFragments(PSPatternSpecification pattern)
   {
      return hasFragments(pattern, ModifierType.NEGATIVE);
   }


   private static boolean hasFragments(PSPatternSpecification pattern, ModifierType type)
   {
      for (PSCombinedFragment fragment : pattern.getCombinedFragments())
      {
         if (type.equals(fragment.getKind()))
         {
            return true;
         }
      }

      return false;
   }


   public static boolean hasSetFragments(PSPatternSpecification pattern)
   {
      return hasFragments(pattern, ModifierType.SET);
   }


   public static boolean hasAntecedentPatterns(PSPatternSpecification pattern)
   {
      for (PSAnnotation annotation : getAnnotations(pattern))
      {
         if (isNormal(annotation) && isReferencing(annotation))
         {
            return true;
         }
      }

      return false;
   }


   public static boolean hasSetElementsAnnotated(PSPatternSpecification pattern)
   {
      for (PSConnection out : getCreateAnnotation(pattern).getOutgoing())
      {
         if (isInSet(out.getTarget()))
         {
            return true;
         }
      }

      return false;
   }


   public static boolean isInSet(PSNode node)
   {
      for (PSCombinedFragment fragment : node.getParents())
      {
         if (ModifierType.SET.equals(fragment.getKind()))
         {
            return true;
         }
      }

      return ModifierType.SET.equals(node.getModifier());
   }


   public static PSAnnotation getCreateAnnotation(PSPatternSpecification pattern)
   {
      for (PSAnnotation annotation : getAnnotations(pattern))
      {
         if (isCreate(annotation))
         {
            return annotation;
         }
      }
      return null;
   }


   public static Collection<PSLink> getLinks(PSPatternSpecification pattern)
   {
      Collection<PSLink> links = new ArrayList<PSLink>();
      for (PSConnection connection : pattern.getConnections())
      {
         if (connection instanceof PSLink)
         {
            links.add((PSLink) connection);
         }
      }

      return links;
   }


   public static Collection<PSPath> getPaths(PSPatternSpecification pattern)
   {
      Collection<PSPath> paths = new ArrayList<PSPath>();
      for (PSConnection connection : pattern.getConnections())
      {
         if (connection instanceof PSPath)
         {
            paths.add((PSPath) connection);
         }
      }

      return paths;
   }


   public static Collection<PSNode> getAnnotatedElements(PSPatternSpecification pattern)
   {
      Collection<PSNode> nodes = new ArrayList<PSNode>();
      for (PSConnection connection : getCreateAnnotation(pattern).getOutgoing())
      {
         nodes.add(connection.getTarget());
      }

      return nodes;
   }


   public static boolean isConnectedToSet(PSNode node)
   {
      for (PSConnection in : node.getIncoming())
      {
         if (isInSet(in.getSource()))
         {
            return true;
         }
      }

      for (PSConnection out : node.getOutgoing())
      {
         if (isInSet(out.getTarget()))
         {
            return true;
         }
      }

      return false;
   }
}
