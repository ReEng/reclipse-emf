package org.reclipse.tests.structure.util;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.fujaba.commons.console.ReportLevel;
import org.osgi.framework.Bundle;
import org.reclipse.structure.generator.PrepareDetectionEnginesJob;
import org.reclipse.structure.generator.preparationstrategies.GenerateNewEnginesStrategy;
import org.reclipse.structure.inference.DetectPatternsJob;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.evaluation.SimilarityEvaluator;
import org.reclipse.structure.inference.notification.InferenceProgressProvider;
import org.reclipse.structure.specification.PSPatternSpecification;


public final class TestsUtil
{
   private static final String TESTS_PROJECT = "tests";

   private static final String TESTS_PLUGIN = "org.reclipse.tests";

   private static final String CATALOG_CONTAINER = "resources";


   /**
    * Checks the size of a collection.
    * 
    * @param collection The collection to be checked.
    * @param size The expected size.
    */
   public static void checkSize(Collection<?> collection, int size)
   {
      // check for empty list
      if (size == 0)
      {
         assertTrue(collection == null || collection.isEmpty());
         return;
      }

      // check results
      String message = "the list is null";
      assertNotNull(message, collection);

      // check size
      message = "found " + collection.size() + " elements instead of " + size;
      assertEquals(message, size, collection.size());
   }


   public static PSPatternSpecification getPattern(Map<PSPatternSpecification, Collection<ASGAnnotation>> results,
         String name)
   {
      for (PSPatternSpecification pattern : results.keySet())
      {
         if (name.equals(pattern.getName()))
         {
            return pattern;
         }
      }

      return null;
   }


   /**
    * Copies the given file from the plug-in directory <code>resources</code> to a test project when
    * not already done and returns the 'new' path.
    * 
    * @param filename The file name to get the path for.
    * @return Returns the platform resource path or <code>null</code> on errors.
    */
   public static String getPath(String filename)
   {
      IProject project = getTestProject();

      // get file
      IFile file = project.getFile(filename);
      if (!file.isAccessible())
      {
         // copy from plugin resource
         try
         {
            // prepare source
            Bundle bundle = Platform.getBundle(TESTS_PLUGIN);
            IPath sourcePath = new Path(CATALOG_CONTAINER + IPath.SEPARATOR + filename);
            InputStream source = FileLocator.openStream(bundle, sourcePath, false);

            // copy file
            IFile target = getTestProject().getFile(filename);
            target.create(source, true, new NullProgressMonitor());
         }
         catch (IOException e)
         {
            e.printStackTrace();
            return null;
         }
         catch (CoreException e)
         {
            e.printStackTrace();
            return null;
         }
      }

      return file.getFullPath().toString();
   }


   private static IProject getTestProject()
   {
      IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(TESTS_PROJECT);
      if (!project.isAccessible())
      {
         try
         {
            project.create(new NullProgressMonitor());
            project.open(new NullProgressMonitor());
         }
         catch (CoreException e)
         {
            e.printStackTrace();
            return null;
         }
      }

      return project;
   }


   public static Map<PSPatternSpecification, Collection<ASGAnnotation>> getResults(String catalogPath, String hostPath,
         boolean annotateAdditionals)
   {
      Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
      ResourceSet resourceSet = new ResourceSetImpl();
      Resource catalogResource = resourceSet.getResource(URI.createPlatformResourceURI(catalogPath, true), true);
      Resource hostGraphResource = resourceSet.getResource(URI.createPlatformResourceURI(hostPath, true), true);
      Resource enginesResource = resourceSet
            .createResource(URI.createPlatformResourceURI(catalogPath + ".ecore", true));

      GenerateNewEnginesStrategy preparationStrategy = new GenerateNewEnginesStrategy(catalogResource);
      PrepareDetectionEnginesJob preparationJob = new PrepareDetectionEnginesJob(preparationStrategy,
            ReportLevel.DEBUG);
      preparationJob.schedule();
      try
      {
         preparationJob.join();
      }
      catch (InterruptedException e)
      {
         e.printStackTrace();
      }


      // prepare job
      DetectPatternsJob detectionJob = new DetectPatternsJob(catalogResource, enginesResource, hostGraphResource,
            ReportLevel.DEBUG);
      detectionJob.setEvaluator(new SimilarityEvaluator());
      detectionJob.setAnnotateAdditionalElements(annotateAdditionals);

      // prepare results collector
      ResultCollector collector = new ResultCollector();
      InferenceProgressProvider.get(detectionJob.getEngine()).addListener(collector);

      // start job
      detectionJob.schedule();
      try
      {
         detectionJob.join();
      }
      catch (InterruptedException e)
      {
         e.printStackTrace();
      }

      return collector.getCurrentResults();
   }


   private TestsUtil()
   {
      // hide constructor
   }
}
