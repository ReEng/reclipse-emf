package org.reclipse.tests.structure;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSConnection;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.util.ModelHelper;
import org.reclipse.tests.structure.util.Helper;


/**
 * This test case test different story pattern generation related functions.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class GeneratorTest
{

   private static final String PATH_CATALOG = "/org.reclipse.tests/resources/generator.psc";

   private static final String PACK_NS_ANNOTATIONS = "http://org.reclipse.patterns.structure.inference/engines";

   private static final String PACK_NS_ENGINES = "http://org.reclipse.patterns.structure.inference.engines/engines";

   private static final String SUFFIX_ENGINE = "Engine";

   private static final String SUFFIX_ANNOTATION = "Annotation";

   private static final String PREFIX_ANNOTATE = "annotate_";

   private static final String PREFIX_ROLE = "role_";

   private static final String PREFIX_TRIGGER = "Trigger_";

   private static final String NAME_ANNOTATE = "annotate";

   private static final String NAME_APP_FAILED = "applicationAlreadyFailed";

   private static final String NAME_FIND_ADDITIONALS = "findOptionalElements";

   private static final String NAME_FIND_SET_FRAGMENTS = "findSetFragments"; // max. 1 mal wenn set frag. vorhanden

   private static final String NAME_FIND_SUBGRAPH_IN_SET = "findSubGraphInSet"; // 1 mal pro set

   private static final String NAME_MATCH_PAIRS = "matchPairs"; // 1 mal pro ueberschneidung

   private static final String NAME_REMOVE_INSTANCES = "removeInstances"; // je set 1 mal "pro matchPairs"

   private static final String NAME_CHECK_LINKS_BETWEEN_SETS = "checkLinksBetweenSets"; // fuer jede richtung einmal,
                                                                                        // wenn ein link zwischen zwei
                                                                                        // alleinstehenden set fragments
                                                                                        // existiert

   private static Resource story;


   @BeforeClass
   public static void start()
   {
      story = Helper.getStoryResource(PATH_CATALOG);
   }


   @AfterClass
   public static void stop()
   {
      story.unload();
      story = null;
   }


   @Test
   public void checkGeneratedAnnotations()
   {
      String message;

      // assert the resource is not null and loaded
      message = "the story engines resource is null";
      assertNotNull(message, story);

      message = "the story engines resource is not loaded";
      assertTrue(message, story.isLoaded());

      // collect all contained packages
      Map<String, Set<String>> packs = new HashMap<String, Set<String>>();
      for (EObject content : story.getContents())
      {
         if (content instanceof EPackage)
         {
            // collect all classifier names
            Set<String> list = new HashSet<String>();
            for (EClassifier type : ((EPackage) content).getEClassifiers())
            {
               list.add(type.getName());
            }

            // cache it
            packs.put(((EPackage) content).getNsURI(), list);
         }
      }

      // check that the annotations package seems to be valid
      message = "generated story pattern resource does not contain a package for the annotations";
      assertTrue(message, packs.containsKey(PACK_NS_ANNOTATIONS));
      for (PSPatternSpecification pattern : Helper.getCatalog(Helper.getCatalogResource(PATH_CATALOG))
            .getPatternSpecifications())
      {
         if (!pattern.isAbstract())
         {
            boolean condition = packs.get(PACK_NS_ANNOTATIONS).contains(pattern.getName() + SUFFIX_ANNOTATION);
            message = "generated story pattern resource does not contain an annotation class for the pattern '";
            assertTrue(message + pattern.getName() + "'", condition);
         }
      }
   }


   @Test
   public void checkGeneratedEngines()
   {
      String message;

      // assert the resource is not null and loaded
      message = "the story engines resource is null";
      assertNotNull(message, story);

      message = "the story engines resource is not loaded";
      assertTrue(message, story.isLoaded());

      // collect all contained packages
      Map<String, Set<String>> packs = new HashMap<String, Set<String>>();
      for (EObject content : story.getContents())
      {
         if (content instanceof EPackage)
         {
            // collect all classifier names
            Set<String> list = new HashSet<String>();
            for (EClassifier type : ((EPackage) content).getEClassifiers())
            {
               list.add(type.getName());
            }

            // cache it
            packs.put(((EPackage) content).getNsURI(), list);
         }
      }

      // check that the engines package seems to be valid
      message = "generated story pattern resource does not contain a package for the engines";
      assertTrue(message, packs.containsKey(PACK_NS_ENGINES));
      for (PSPatternSpecification pattern : Helper.getCatalog(Helper.getCatalogResource(PATH_CATALOG))
            .getPatternSpecifications())
      {
         if (!pattern.isAbstract())
         {
            boolean condition = packs.get(PACK_NS_ENGINES).contains(pattern.getName() + SUFFIX_ENGINE);
            message = "generated story pattern resource does not contain an engine class for the pattern '";
            assertTrue(message + pattern.getName() + "'", condition);
         }
      }
   }


   @Test
   public void checkGeneratedOperations()
   {
      String message;

      // assert the resource is not null
      message = "the story engines resource is null";
      assertNotNull(message, story);

      // assert the resource is loaded
      message = "the story engines resource is not loaded";
      assertTrue(message, story.isLoaded());

      // find engines package
      EPackage engines = null;
      for (EObject content : story.getContents())
      {
         if (content instanceof EPackage && ((EPackage) content).getNsURI().equals(PACK_NS_ENGINES))
         {
            engines = (EPackage) content;
         }
      }

      // assert the engines package is found
      message = "the story engines package is not generated";
      assertNotNull(message, engines);

      // test the patterns
      for (PSPatternSpecification pattern : Helper.getCatalog(Helper.getCatalogResource(PATH_CATALOG))
            .getPatternSpecifications())
      {
         // get engine
         message = "the engine is not generated";
         EClass engine = (EClass) engines.getEClassifier(pattern.getName() + SUFFIX_ENGINE);
         assertNotNull(message, engine);

         // collect operation names
         Set<String> generated = new HashSet<String>();

         for (EOperation op : engine.getEOperations())
         {
            generated.add(op.getName());
         }

         // collect needed names
         Set<String> needed = getNeededRoleNames(pattern);

         if (!pattern.isAbstract())
         {
            // check for all needed operations
            for (String name : needed)
            {
               message = "the operation '" + engine.getName() + "." + name + "' is missing";
               assertTrue(message, generated.contains(name));
            }
         }
      }
   }


   private Set<String> getNeededRoleNames(PSPatternSpecification pattern)
   {
      Set<String> needed = new HashSet<String>();

      // add common names
      needed.add(NAME_ANNOTATE);
      needed.add(NAME_APP_FAILED);
      needed.add(NAME_FIND_ADDITIONALS);

      // HierarchSetFragment
      if (pattern.getName().equals("HierarchSetFragment"))
      {
         needed.add(NAME_FIND_SET_FRAGMENTS);
         needed.add(NAME_FIND_SUBGRAPH_IN_SET + 0);
      }

      // MultiSetFragments
      if (pattern.getName().equals("MultiSetFragments"))
      {
         needed.add(NAME_FIND_SET_FRAGMENTS);
         needed.add(NAME_FIND_SUBGRAPH_IN_SET + 0);
         needed.add(NAME_FIND_SUBGRAPH_IN_SET + 1);
         needed.add(NAME_CHECK_LINKS_BETWEEN_SETS + 1);
         needed.add(NAME_CHECK_LINKS_BETWEEN_SETS + 2);
      }

      // OverlappingSetFragments
      if (pattern.getName().equals("OverlappingSetFragments"))
      {
         needed.add(NAME_FIND_SET_FRAGMENTS);
         needed.add(NAME_FIND_SUBGRAPH_IN_SET + 0);
         needed.add(NAME_FIND_SUBGRAPH_IN_SET + 1);
         needed.add(NAME_MATCH_PAIRS + "_Set0_Set1");
         needed.add(NAME_REMOVE_INSTANCES + "_Set0");
         needed.add(NAME_REMOVE_INSTANCES + "_Set1");
      }

      // annotate role names
      for (PSNode node : pattern.getNodes())
      {
         if (node instanceof PSAnnotation && ModelHelper.isCreate((PSAnnotation) node))
         {
            for (PSConnection annotated : node.getOutgoing())
            {
               if (annotated instanceof PSLink)
               {
                  String name = ((PSLink) annotated).getQualifier().substring(0, 1).toUpperCase()
                        + ((PSLink) annotated).getQualifier().substring(1);

                  needed.add(PREFIX_ANNOTATE + PREFIX_ROLE + name);
               }
            }
         }
      }

      // trigger roles
      for (PSConnection conn : pattern.getConnections())
      {
         String name = conn.getName().substring(0, 1).toUpperCase() + conn.getName().substring(1);
         if (conn instanceof PSLink && conn.getSource() instanceof PSAnnotation)
         {
            PSAnnotation anno = (PSAnnotation) conn.getSource();
            if (!ModelHelper.isCreate(anno))
            {
               needed.add(PREFIX_ANNOTATE + PREFIX_ROLE + PREFIX_TRIGGER + name);
            }
         }
      }

      return needed;
   }
}
