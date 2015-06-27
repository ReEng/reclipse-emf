package org.reclipse.structure.specification.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.fujaba.commons.identifier.Identifier;
import org.reclipse.metamodel.MetaModel;
import org.reclipse.metamodel.internal.MetaModelHelper;
import org.reclipse.structure.specification.ModifierType;
import org.reclipse.structure.specification.OperatorType;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSAttributeConstraint;
import org.reclipse.structure.specification.PSCatalog;
import org.reclipse.structure.specification.PSCombinedFragment;
import org.reclipse.structure.specification.PSCombinedFragmentItem;
import org.reclipse.structure.specification.PSConnection;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.PSSpecificationConstraint;
import org.reclipse.structure.specification.PatternType;


/**
 * This class delivers some basic meta model methods.
 * 
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class ModelHelper
{
   private static Map<MetaModel, List<EClass>> types;

   /**
    * The comparator for any {@link ENamedElement}.
    */
   private final static Comparator<ENamedElement> NAME_COMPARATOR = new Comparator<ENamedElement>()
   {
      @Override
      public int compare(ENamedElement one, ENamedElement two)
      {
         // both null
         if (one == null && two == null)
         {
            return 0;
         }

         // first null
         if (one == null)
         {
            return -1;
         }

         // second null
         if (two == null)
         {
            return 1;
         }

         return one.getName().compareTo(two.getName());
      }
   };

   /**
    * The comparator for any {@link Identifier}.
    */
   private final static Comparator<Identifier> IDENTIFIER_COMPARATOR = new Comparator<Identifier>()
   {
      @Override
      public int compare(Identifier one, Identifier two)
      {
         // both null
         if (one == null && two == null)
         {
            return 0;
         }

         // first null
         if (one == null)
         {
            return -1;
         }

         // second null
         if (two == null)
         {
            return 1;
         }

         return one.getName().compareTo(two.getName());
      }
   };


   /**
    * Checks whether the annotation is a 'create' annotation.
    * 
    * @param annotation The annotation to check.
    * @return Returns <code>true</code> if the annotation is a 'create' annotation,
    *         <code>false</code> otherwise.
    */
   public static boolean isCreate(PSAnnotation annotation)
   {
      return annotation != null
            && annotation.getPatternSpecification() == annotation.getType();
   }


   /**
    * Check whether the link is antecedent to create annotation
    * 
    * @param link The link to check
    * @return Returns <code>true</code> if the link is a 'create' link, <code>false</code>
    *         otherwise.
    */
   public static boolean isCreate(PSLink link)
   {
      PSNode source = link.getSource();
      PSNode target = link.getTarget();

      return ((source instanceof PSAnnotation) && ModelHelper
            .isCreate((PSAnnotation) source))
            || ((target instanceof PSAnnotation) && ModelHelper
                  .isCreate((PSAnnotation) target));
   }


   /**
    * Check wether the given targetNode is annotated.
    * 
    * @param targetNode The node which should be checked if it's annotated in a pattern rule.
    * @return true, if the given node is annotated.
    */
   public static boolean isAnnotated(PSNode targetNode)
   {
      Iterator<PSConnection> iter = targetNode.getIncoming().iterator();
      while (iter.hasNext())
      {
         PSConnection connection = iter.next();
         PSNode sourceNode = connection.getSource();
         if (sourceNode instanceof PSAnnotation)
         {
            if (ModelHelper.isCreate((PSAnnotation) sourceNode))
            {
               return true;
            }
         }
      }
      return false;
   }


   /**
    * Translates a <code>{@link PatternType}</code> to a human readable text.
    * 
    * @param type The pattern type to translate.
    * @return Returns a readable name for the pattern type.
    */
   public static String getReadable(PatternType type)
   {
      switch (type)
      {
         case DESIGN_PATTERN:
            return "Normal";
         case ANTI_PATTERN:
            return "Anti Pattern";
         default:
            return type.getLiteral();
      }
   }


   /**
    * Translates a <code>{@link ModifierType}</code> to a human readable text.
    * 
    * @param type The modifier type to translate.
    * @return Returns a readable name for the modifier type.
    */
   public static String getReadable(ModifierType type)
   {
      switch (type)
      {
         case ADDITIONAL:
            return "Additional";
         case NEGATIVE:
            return "Negative";
         case NONE:
            return "None";
         case SET:
            return "Set";
         default:
            return type.getLiteral();
      }
   }


   /**
    * Translates a <code>{@link ModifierType}</code> to a human readable text.
    * 
    * @param type The modifier type to translate.
    * @return Returns a readable name for the modifier type.
    */
   public static String getReadable(OperatorType type)
   {
      switch (type)
      {
         case EQUAL:
            return "=";
         case UNEQUAL:
            return "!=";
         case GREATER:
            return ">";
         case GREATER_OR_EQUAL:
            return ">=";
         case LESS:
            return "<";
         case LESS_OR_EQUAL:
            return "<=";
         case REGULAR_EXPRESSION:
            return "Regular Expression";
         default:
            return type.getLiteral();
      }
   }


   /**
    * Catches the first EClass from the resource set.
    * 
    * @param ress The ResourceSet to load from.
    * @return Returns the first feasable EClass or <code>null</code>.
    */
   public static EClass getFirstType(MetaModel mm)
   {
      for (EClass type : getTypes(mm))
      {
         return type;
      }

      return null;
   }


   public static List<EClass> getTypes(MetaModel mm)
   {
      // create map when not existing
      if (types == null)
      {
         types = new HashMap<MetaModel, List<EClass>>();
      }

      // get types for mm
      if (!types.containsKey(mm))
      {
         // get package registry
         Registry reg = EPackage.Registry.INSTANCE;

         // prepare type list
         ArrayList<EClass> list = new ArrayList<EClass>();

         // add all types from the packages
         for (String uri : mm.getPackages())
         {
            if (reg.containsKey(uri))
            {
               addTypes(list, reg.getEPackage(uri));
            }
            else
            {
               System.err.println("EPackage '" + uri
                     + "' could not be found in the registry!");
            }
         }

         // sort the list
         Collections.sort(list, NAME_COMPARATOR);

         // map the list
         types.put(mm, list);
      }

      return types.get(mm);
   }


   private static void addTypes(List<EClass> list, EPackage pack)
   {
      for (EClassifier type : pack.getEClassifiers())
      {
         if (type != null && type instanceof EClass)
         {
            list.add((EClass) type);
         }
      }
   }


   /**
    * Catches the first attribute for the given PSObject's type.
    * 
    * @param object The PSObject.
    * @return Returns the EAttribute or <code>null</code>.
    */
   public static EAttribute getFirstAttribute(PSObject object)
   {
      for (EAttribute attr : object.getInstanceOf().getEAllAttributes())
      {
         return attr;
      }

      return null;
   }


   /**
    * Catches the first valid qualifier between the given PSAnnotation and PSObject.
    * 
    * @param source The source PSAnnotation.
    * @param target The target PSObject.
    * @return Returns the first valid qualifier or <code>null</code>.
    */
   public static String getFirstQualifier(PSAnnotation source, PSObject target)
   {
      // go through the source's targets
      for (PSConnection con : getCreateAnnotation(source).getOutgoing())
      {
         // filter links with PSObject as target
         if (con instanceof PSLink && con.getTarget() instanceof PSObject)
         {
            // filter PSObject with adequate type
            if (((PSObject) con.getTarget()).getInstanceOf() == target
                  .getInstanceOf())
            {
               String qualifier = ((PSLink) con).getQualifier();
               if (qualifier != null)
               {
                  return ((PSLink) con).getQualifier();
               }
            }
         }
      }

      return null;
   }


   /**
    * Catches the first valid EReference between the two given PSObject.
    * 
    * @param source The source PSObject.
    * @param target The target PSObject.
    * @return Returns the first valid qualifier or <code>null</code>.
    */
   public static EReference getFirstReference(PSNode source, PSNode target)
   {
      // get adequate for PSObject |--> PSObject
      if (source instanceof PSObject && target instanceof PSObject)
      {
         EClass sourceType = ((PSObject) source).getInstanceOf();
         EClass targetType = ((PSObject) target).getInstanceOf();

         for (EReference ref : sourceType.getEAllReferences())
         {
            if (ref.getEReferenceType().isSuperTypeOf(targetType))
            {
               return ref;
            }
         }
      }

      return null;
   }


   /**
    * Gets the create annotation of the given pattern specification.
    * 
    * @param pattern The pattern in which to search the create annotation.
    * @return Returns the create annotation or <code>null</code> if there is none.
    */
   public static PSAnnotation getCreateAnnotation(PSPatternSpecification pattern)
   {
      for (PSNode node : pattern.getNodes())
      {
         if (node instanceof PSAnnotation && isCreate((PSAnnotation) node))
         {
            return (PSAnnotation) node;
         }
      }

      return null;
   }


   /**
    * Searches the 'create' PSAnnotation for the given PSAnnotation.
    * 
    * @param anno The PSAnnotation for which to search the creating instance.
    * @return Returns the creating PSAnnotation.
    */
   public static PSAnnotation getCreateAnnotation(PSAnnotation anno)
   {
      if (isCreate(anno))
      {
         return anno;
      }

      // search adequate annotation
      PSPatternSpecification other = anno.getPatternSpecification();

      for (PSPatternSpecification pattern : other.getCatalog()
            .getPatternSpecifications())
      {
         if (pattern == anno.getType())
         {
            return getCreateAnnotation(pattern);
         }
      }

      return null;
   }


   /**
    * Collects all types (EClass) for the PSAnnotation connected through a PSLink and returns them.
    * 
    * @param anno The annotation for which to collect.
    * @return Returns all connected types.
    */
   public static HashSet<EClass> getPossibleTypes(PSAnnotation anno)
   {
      HashSet<EClass> result = new HashSet<EClass>();

      // get create annotation
      PSAnnotation cAnno = getCreateAnnotation(anno);

      if (cAnno != null)
      {
         for (PSConnection connection : cAnno.getOutgoing())
         {
            if (connection instanceof PSLink)
            {
               PSLink link = (PSLink) connection;

               if (link.getTarget() instanceof PSObject)
               {
                  PSObject obj = (PSObject) link.getTarget();
                  if (obj.getInstanceOf() != null)
                  {
                     result.add(obj.getInstanceOf());
                  }
               }
            }
         }
      }

      return result;
   }


   /**
    * Collects all types (EClass) of possible attributes for the given PSObject.
    * 
    * @param object The PSObject to check.
    * @param res The resource set to search in.
    * @return Returns the set of possible types.
    */
   public static List<EClass> getPossibleTypes(PSObject object)
   {
      List<EClass> result = new ArrayList<EClass>();

      for (EReference ref : object.getInstanceOf().getEAllReferences())
      {
         result.add(ref.getEReferenceType());
      }

      return result;
   }


   /**
    * @param superType
    * @param subTypeCandidate
    * @return true if there exists a subtype relation from superType to subTypeCandidate or both
    *         types are equal, false otherwise
    */
   public static boolean isAssignableFrom(EClass superType,
         EClass subTypeCandidate)
   {
      if (superType == null || subTypeCandidate == null)
      {
         return false;
      }
      return subTypeCandidate.getEAllSuperTypes().contains(superType)
            || superType.equals(subTypeCandidate)
            || superType.equals(EcorePackage.eINSTANCE.getEObject());
   }


   /**
    * Catches the first (other) PSPatternSpecification for the given PSAnnotation.
    * 
    * @param anno The PSAnnotation for which to search.
    * @return Returns the first PSPatternSpecification or <code>null</code>.
    */
   public static PSPatternSpecification getFirstPatternSpecification(
         PSAnnotation anno)
   {
      for (PSPatternSpecification pattern : anno.getPatternSpecification()
            .getCatalog().getPatternSpecifications())
      {
         if (!pattern.equals(anno.getPatternSpecification()))
         {
            return pattern;
         }
      }
      return null;
   }


   /**
    * Creates an adequate String representation for the given attribute in the form
    * <code>name: type</code>.
    * 
    * @param attr The attribute to represent.
    * @return Returns the String representation.
    */
   public static String getAttributeText(EAttribute attr)
   {
      if (attr == null)
      {
         return null;
      }

      StringBuilder text = new StringBuilder();

      // name
      text.append(attr.getName());
      text.append(": "); //$NON-NLS-1$

      // type (shorten String)
      if (attr.getEAttributeType().getInstanceClassName()
            .equals("java.lang.String")) {//$NON-NLS-1$
         text.append("String"); //$NON-NLS-1$
      }
      else
      {
         text.append(attr.getEAttributeType().getInstanceClassName());
      }

      return text.toString();
   }


   public static boolean isWithinOptionalFragment(PSCombinedFragmentItem item)
   {
      Iterator<PSCombinedFragment> i = item.getParents().iterator();
      while (i.hasNext())
      {
         PSCombinedFragment fragment = i.next();
         if (fragment.getKind() == ModifierType.ADDITIONAL)
         {
            return true;
         }
      }

      return false;
   }


   public static boolean isSearchForThisOptional(PSNode node)
   {
      if (node.getModifier() == ModifierType.ADDITIONAL
            || isWithinOptionalFragment(node))
      {
         return true;
      }
      return false;
   }


   public static boolean isSearchForThisOptional(
         PSSpecificationConstraint constraint)
   {
      if (constraint.isAdditional() || isWithinOptionalFragment(constraint))
      {
         return true;
      }
      return false;
   }


   public static boolean isContainedInSetFragment(
         PSCombinedFragmentItem fragment)
   {
      Iterator<PSCombinedFragment> i = fragment.getParents().iterator();
      while (i.hasNext())
      {
         PSCombinedFragment psCombinedFragment = (PSCombinedFragment) i.next();
         if (ModifierType.SET.equals(psCombinedFragment.getKind()))
         {
            return true;
         }
         else if (isContainedInSetFragment(psCombinedFragment))
         {
            return true;
         }
      }
      return false;
   }


   public static boolean isContainedInNegativeFragment(
         PSCombinedFragmentItem fragment)
   {
      Iterator<PSCombinedFragment> i = fragment.getParents().iterator();
      while (i.hasNext())
      {
         PSCombinedFragment psCombinedFragment = (PSCombinedFragment) i.next();
         if (ModifierType.NEGATIVE.equals(psCombinedFragment.getKind()))
         {
            return true;
         }
         else if (isContainedInSetFragment(psCombinedFragment))
         {
            return true;
         }
      }
      return false;
   }


   public static boolean isContainedInAdditionalFragment(
         PSCombinedFragmentItem fragment)
   {
      Iterator<PSCombinedFragment> i = fragment.getParents().iterator();
      while (i.hasNext())
      {
         PSCombinedFragment psCombinedFragment = (PSCombinedFragment) i.next();
         if (ModifierType.ADDITIONAL.equals(psCombinedFragment.getKind()))
         {
            return true;
         }
         else if (isContainedInAdditionalFragment(psCombinedFragment))
         {
            return true;
         }
      }
      return false;
   }


   public static boolean constainsFragmentsOfType(
         PSPatternSpecification pattern, ModifierType fragmentType)
   {
      for (PSCombinedFragment fragment : pattern.getCombinedFragments())
      {
         if (fragment.getKind() == fragmentType)
         {
            return true;
         }
      }
      return false;
   }


   /**
    * Collects all available types for a pattern {@link PSLink link}.
    * 
    * @param link The pattern link.
    * @return Returns all valid pattern link {@link EReference types}.
    */
   public static List<EReference> getAllValidTypes(PSLink link)
   {
      // get the nodes
      PSNode source = link.getSource();
      PSNode target = link.getTarget();

      // create list
      List<EReference> list = new ArrayList<EReference>();

      // get adequate for object --> object
      if (source instanceof PSObject && target instanceof PSObject)
      {
         // get types
         EClass sourceType = ((PSObject) source).getInstanceOf();
         EClass targetType = ((PSObject) target).getInstanceOf();

         // collect valid references
         for (EReference ref : sourceType.getEAllReferences())
         {
            if (ref.getEReferenceType().isSuperTypeOf(targetType))
            {
               list.add(ref);
            }
         }

         // sort the list
         Collections.sort(list, NAME_COMPARATOR);
      }

      return list;
   }


   /**
    * Collects all available types for a pattern {@link PSObject object}.
    * 
    * @param element The pattern object.
    * @return Returns all valid pattern object {@link EClass types}.
    */
   public static List<EClass> getAllValidTypes(PSObject element)
   {
      // create list
      List<EClass> result = new ArrayList<EClass>();

      // get meta model
      MetaModel mm = getMetaModel(element);

      // add all given types
      result.addAll(getTypes(mm));

      // sort the list
      Collections.sort(result, NAME_COMPARATOR);

      return result;
   }


   /**
    * Collects all available types for a pattern {@link PSAnnotation annotation}.
    * 
    * @param element The pattern annotation.
    * @return Returns all valid pattern annotation {@link PSPatternSpecification types}.
    */
   public static List<PSPatternSpecification> getAllValidTypes(
         PSAnnotation element)
   {
      // create list
      List<PSPatternSpecification> result = new ArrayList<PSPatternSpecification>();

      // add all given patterns
      result.addAll(element.getPatternSpecification().getCatalog()
            .getPatternSpecifications());

      // collect children patterns
      List<PSPatternSpecification> toRemove = new ArrayList<PSPatternSpecification>();

      // add the pattern itself
      toRemove.add(element.getPatternSpecification());

      // recursively remove all children
      while (!toRemove.isEmpty())
      {
         // get next from the list
         PSPatternSpecification current = toRemove.remove(0);

         // remove it from the parent list
         result.remove(current);

         // add all children to the remove list
         for (PSPatternSpecification sub : current.getSubPatterns())
         {
            toRemove.add(sub);
         }
      }

      // sort the list
      Collections.sort(result, IDENTIFIER_COMPARATOR);

      return result;
   }


   /**
    * Collects all available types for a {@link PSPatternSpecification pattern}.
    * 
    * @param element The pattern.
    * @return Returns all valid pattern {@link PSPatternSpecification parents}.
    */
   public static List<PSPatternSpecification> getAllValidParents(
         PSPatternSpecification element)
   {
      // get catalog
      PSCatalog catalog = element.getCatalog();

      // create a list
      List<PSPatternSpecification> all = new ArrayList<PSPatternSpecification>();
      all.addAll(catalog.getPatternSpecifications());

      // add 'null' to the list
      all.add(0, null);

      // create a list of removals
      List<PSPatternSpecification> toRemove = new ArrayList<PSPatternSpecification>();

      // add the pattern itself
      toRemove.add(element);

      // recursively remove all children
      while (!toRemove.isEmpty())
      {
         // get next from the list
         PSPatternSpecification current = toRemove.remove(0);

         // remove it from the parent list
         all.remove(current);

         // add all children to the remove list
         for (PSPatternSpecification sub : current.getSubPatterns())
         {
            toRemove.add(sub);
         }
      }

      // sort the list
      Collections.sort(all, IDENTIFIER_COMPARATOR);

      return all;
   }


   /**
    * Collects all available qualifiers for a pattern {@link PSLink link}.
    * 
    * @param element The pattern link.
    * @return Returns all valid pattern link qualifiers.
    */
   public static List<String> getAllValidQualifiers(PSLink element)
   {
      // only links from annotations to objects
      if (element.getSource() instanceof PSAnnotation
            && element.getTarget() instanceof PSObject)
      {
         // get source
         PSAnnotation source = (PSAnnotation) element.getSource();

         if (!isCreate(source))
         {
            return getAllValidQualifiersFromReferenced(element);
         }
         else if (element.getPatternSpecification().getSuperPattern() != null)
         {
            return getAllValidQualifiersFromInherited(element);
         }
      }

      return Collections.emptyList();
   }


   private static List<String> getAllValidQualifiersFromInherited(PSLink element)
   {
      // get the relevant data
      PSObject target = (PSObject) element.getTarget();
      EClass targetType = target.getInstanceOf();
      PSAnnotation inheritedAnnotation = getCreateAnnotation(element
            .getPatternSpecification().getSuperPattern());

      // collect already existing
      List<String> existing = new ArrayList<String>();
      for (PSConnection out : element.getSource().getOutgoing())
      {
         // check type
         if (out instanceof PSLink && !out.equals(element))
         {
            // get targets type
            EClass toCheck = ((PSObject) out.getTarget()).getInstanceOf();

            // check both types
            if (targetType.equals(toCheck))
            {
               // get the qualifier
               String qualifier = ((PSLink) out).getQualifier();

               // check for null value
               if (qualifier == null)
               {
                  qualifier = ""; //$NON-NLS-1$
               }

               // check if it is already existing
               if (!existing.contains(qualifier))
               {
                  existing.add(qualifier);
               }
            }
         }
      }

      // collect inherited qualifiers
      List<String> inherited = new ArrayList<String>();
      for (PSConnection out : inheritedAnnotation.getOutgoing())
      {
         // check for correct link
         if (out.getTarget() instanceof PSObject)
         {
            // get targets type
            EClass toCheck = ((PSObject) out.getTarget()).getInstanceOf();

            // check both types
            if (targetType.equals(toCheck))
            {
               // get the qualifier
               String qualifier = ((PSLink) out).getQualifier();

               // check for null value
               if (qualifier == null)
               {
                  qualifier = ""; //$NON-NLS-1$
               }

               // check if it is already existing
               if (!existing.contains(qualifier)
                     && !inherited.contains(qualifier))
               {
                  inherited.add(qualifier);
               }
            }
         }
      }
      // sort the list
      Collections.sort(inherited);

      return inherited;
   }


   private static List<String> getAllValidQualifiersFromReferenced(
         PSLink element)
   {
      // get referenced annotation
      PSAnnotation source = getCreateAnnotation(((PSAnnotation) element
            .getSource()).getType());

      // get target
      PSObject target = (PSObject) element.getTarget();

      // get target type
      EClass targetType = target.getInstanceOf();

      // create list
      List<String> list = new ArrayList<String>();

      // go through the create links
      for (PSConnection out : source.getOutgoing())
      {
         // check for correct link
         if (out.getTarget() instanceof PSObject)
         {
            // get targets type
            EClass toCheck = ((PSObject) out.getTarget()).getInstanceOf();

            // check both types
            if (targetType.equals(toCheck))
            {
               // get the qualifier
               String qualifier = ((PSLink) out).getQualifier();

               // only add 'valid' qualifier
               if (qualifier != null)
               {
                  list.add(qualifier);
               }
               else
               {
                  list.add(""); //$NON-NLS-1$
               }
            }
         }
      }

      // sort the list
      Collections.sort(list);

      // return the list
      return list;
   }


   /**
    * Collects all available attributes for a pattern {@link PSObject object}.
    * 
    * @param element The pattern object.
    * @return Returns all valid pattern object {@link EAttribute attributes}.
    */
   public static List<EAttribute> getAllValidAttributes(PSObject element)
   {
      // get type
      EClass type = element.getInstanceOf();

      // create list
      List<EAttribute> list = new ArrayList<EAttribute>();

      // add all attributes
      list.addAll(type.getEAllAttributes());

      // sort list
      Collections.sort(list, NAME_COMPARATOR);

      return list;
   }


   /**
    * Collects all available values for a pattern object's {@link PSAttributeConstraint attribute
    * constraint}.
    * 
    * @param element The pattern attribute constraint.
    * @return Returns all valid attribute values.
    */
   public static List<String> getAllValidValues(PSAttributeConstraint element)
   {
      // get attribute
      EAttribute attribute = element.getAttribute();

      // check it
      if (attribute != null)
      {
         // get data type
         EDataType type = attribute.getEAttributeType();

         if (type != null)
         {
            // create list
            List<String> list = new ArrayList<String>();

            // check for data type
            if (type instanceof EEnum)
            {
               for (EEnumLiteral literal : ((EEnum) type).getELiterals())
               {
                  list.add(literal.getName());
               }
            }
            else if (type.equals(EcorePackage.eINSTANCE.getEBoolean()))
            {
               list.add("true"); //$NON-NLS-1$
               list.add("false"); //$NON-NLS-1$
            }

            // return it
            return list;
         }
      }

      return Collections.emptyList();
   }


   public static Collection<MetaModel> getAllMetaModels()
   {
      return MetaModelHelper.getModels().values();
   }


   public static MetaModel getMetaModel(PSCatalog catalog)
   {
      return getMetaModel(catalog.getMetamodel());
   }


   public static MetaModel getMetaModel(PSNode node)
   {
      return getMetaModel(node.getPatternSpecification());
   }


   public static MetaModel getMetaModel(PSPatternSpecification spec)
   {
      return getMetaModel(spec.getCatalog());
   }


   public static MetaModel getMetaModel(String id)
   {
      return MetaModelHelper.getModels().get(id);
   }

	public static boolean isMatchingRequired(PSNode node, PSNode trigger)
	{
		return !ModelHelper.getCreateAnnotation(trigger.getPatternSpecification()).equals(node) && node.equals(trigger)
				|| ModelHelper.isAnnotated(node)
				|| (!ModelHelper.isContainedInNegativeFragment(node) && !ModelHelper.isSearchForThisOptional(node));
	}


	public static boolean isAnnotatedOptionalNodeToBeCheckedLater(PSNode theNodeToCheck, PSNode trigger)
	{
		return theNodeToCheck != trigger && ModelHelper.isAnnotated(theNodeToCheck)
				&& theNodeToCheck.getModifier() == ModifierType.ADDITIONAL;
	}



	/**
	 * One set overlaps another one if they contain at least one equal child.
	 * 
	 * @param fragment1
	 * @param fragment2
	 * @return true if fragment1 and fragment2 have an overlapping area
	 */
	public static boolean overlaps(PSCombinedFragment fragment1, PSCombinedFragment fragment2)
	{
		if (!fragment1.equals(fragment2))
		{
			for (PSCombinedFragmentItem child : fragment1.getChildren())
			{
				if (fragment2.getChildren().contains(child))
				{
					return true;
				}
			}
		}

		return false;
	}


	/**
	 * Returns an element that is contained in no fragment and connected to an element to the set. Returns null, if such
	 * an element does not exist.
	 * 
	 * @param set
	 * @return connected element or null
	 */
	public static PSNode getConnectedElementInNoFragment(PSCombinedFragment set)
	{
		Iterator<PSCombinedFragmentItem> children = set.getChildren().iterator();
		PSAnnotation createAnno = ModelHelper.getCreateAnnotation(set.getPatternSpecification());
		while (children.hasNext())
		{
			PSCombinedFragmentItem child = children.next();
			if (child instanceof PSNode)
			{
				Iterator<PSConnection> outgoingConnections = ((PSNode) child).getOutgoing().iterator();
				while (outgoingConnections.hasNext())
				{
					PSConnection connection = outgoingConnections.next();
					PSNode target = connection.getTarget();

					// target node is connected to an element in the set
					if (target != createAnno && !set.getChildren().contains(target)
							&& !ModelHelper.isContainedInAdditionalFragment(target))
					{
						// target node is not contained in any additional fragment and not in the current
						// set
						return target;
					}
				}
				Iterator<PSConnection> incomingConnections = ((PSNode) child).getIncoming().iterator();
				while (incomingConnections.hasNext())
				{
					PSConnection connection = incomingConnections.next();
					PSNode source = connection.getSource();
					// source node is connected to an element in the set
					if (source != createAnno && !set.getChildren().contains(source)
							&& !ModelHelper.isContainedInAdditionalFragment(source))
					{
						// source node is not contained in any fragment
						return source;
					}
				}
			}

		}
		return null;
	}


	/**
	 * Collects all {@link PSNode nodes} that are children in both given fragments. TODO: move to model helper!
	 * 
	 * @param first The first fragment.
	 * @param second The second fragment.
	 * @return Returns the {@link PSNode nodes} in both fragments.
	 */
	public static Set<PSNode> getOverlappingNodes(PSCombinedFragment first, PSCombinedFragment second)
	{
		Set<PSNode> result = new HashSet<PSNode>();

		for (PSCombinedFragmentItem child : first.getChildren())
		{
			if (child instanceof PSNode && second.getChildren().contains(child))
			{
				// item is in first and in second fragment
				result.add((PSNode) child);
			}
		}

		return result;
	}


	public static boolean nodeIsInSet(PSNode node, PSCombinedFragment set)
	{
		return set.getChildren().contains(node);
	}


	public static boolean nodeIsInNoSet(PSNode node)
	{
		return !ModelHelper.isContainedInSetFragment(node);
	}


	public static Collection<PSNode> getNodes(PSCombinedFragment fragment)
	{
		Set<PSNode> result = new HashSet<PSNode>();

		for (PSCombinedFragmentItem item : fragment.getChildren())
		{
			if (item instanceof PSNode)
			{
				result.add((PSNode) item);
			}
		}

		return result;
	}


	public static Collection<PSConnection> getConnections(Collection<PSNode> nodes)
	{
		Set<PSConnection> result = new HashSet<PSConnection>();

		for (PSNode node : nodes)
		{
			result.addAll(node.getIncoming());
			result.addAll(node.getOutgoing());
		}

		return result;
	}
}
