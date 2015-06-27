package org.reclipse.structure.inference;


import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.fujaba.commons.console.AbstractProcessConsoleJob;
import org.fujaba.commons.console.ReportLevel;
import org.reclipse.structure.generator.Generator;
import org.reclipse.structure.generator.GeneratorConstants;
import org.reclipse.structure.specification.PSCatalog;


public class DetectPatternsJob extends AbstractProcessConsoleJob
{
   private final ResourceSet resourceSet;


   private final String catalogPath;

   private final String hostPath;

   private final String enginesPath;


   private String outputPath;


   private boolean isCreateEnginesResource;

   private boolean isUsingExistingEngines;

   private boolean isAnnotateAdditionalElements;

   private InferenceEngine inferenceEngine;

   private IAnnotationEvaluator evaluator;


   public DetectPatternsJob(String catalogPath, String hostPath, String enginesPath, ReportLevel reportLevel)
   {
      super("Reclipse", "Structural Inference", catalogPath, reportLevel);

      this.resourceSet = new ResourceSetImpl();
      this.inferenceEngine = new InterpreterInferenceEngine(this, ReportLevel.DEBUG.equals(reportLevel));

      this.catalogPath = catalogPath;
      this.hostPath = hostPath;
      this.enginesPath = enginesPath;
   }


   public InferenceEngine getInferenceEngine()
   {
      return inferenceEngine;
   }


   public void setEvaluator(IAnnotationEvaluator evaluator)
   {
      this.evaluator = evaluator;
   }


   public void setUseExistingEngines(boolean isUsingExistingEngines)
   {
      this.isUsingExistingEngines = isUsingExistingEngines;
   }


   public void setAnnotateAdditionalElements(boolean isAnnotateAdditionalElements)
   {
      this.isAnnotateAdditionalElements = isAnnotateAdditionalElements;
   }


   public void setCreateEnginesResource(boolean isCreateEnginesResource)
   {
      this.isCreateEnginesResource = isCreateEnginesResource;
   }


   public void setOutputPath(String outputPath)
   {
      this.outputPath = outputPath;
   }


   @Override
   public IStatus start(IProgressMonitor monitor)
   {
      monitor.beginTask(getName(), IProgressMonitor.UNKNOWN);

      // search catalog
      PSCatalog catalog = null;
      {
         // create resource
         Resource catalogResource = resourceSet.createResource(URI.createPlatformResourceURI(catalogPath, true));

         // load
         task("Loading catalog input resource");
         try
         {
            catalogResource.load(resourceSet.getLoadOptions());
            debug("Catalog input resource loaded.");
         }
         catch (IOException e)
         {
            e.printStackTrace();
            return error("The catalog input resource could not be loaded: " + e.getLocalizedMessage());
         }

         // go through contents
         for (EObject content : catalogResource.getContents())
         {
            if (content instanceof PSCatalog)
            {
               catalog = (PSCatalog) content;
               break;
            }
         }

         // check
         if (catalog == null)
         {
            return error("The catalog input resource does not contain a valid catalog!");
         }
      }

      // search annotations and engines packages
      EPackage annotationsPackage = null;
      EPackage enginesPackage = null;
      {
         // prepare contents
         Collection<EObject> contents = null;
         if (isUsingExistingEngines)
         {
            // create resource
            Resource packagesResource = resourceSet.createResource(URI.createPlatformResourceURI(enginesPath, true));

            // load
            task("Loading annotations and engines");
            try
            {
               packagesResource.load(resourceSet.getLoadOptions());
               debug("Annotations and engines input resource loaded.");
            }
            catch (IOException e)
            {
               e.printStackTrace();
               return error("The input resource could not be loaded: " + e.getLocalizedMessage());
            }

            contents = packagesResource.getContents();
         }
         else if (!isCreateEnginesResource)
         {
            // prepare content to insert the generated packages into
            contents = new HashSet<EObject>();

            // start generation
            Generator generator = new Generator(this);
            generator.generate(contents, catalog);
         }

         // check for content
         if (contents == null)
         {
            return error("The there are no valid engines available!");
         }

         // go through contents
         for (EObject content : contents)
         {
            if (content instanceof EPackage)
            {
               EPackage pack = (EPackage) content;

               if (GeneratorConstants.PACKAGE_URI.equals(pack.getNsURI()))
               {
                  enginesPackage = (EPackage) content;
               }
               else if ("http://ns.reclipse.org/structure/generation/annotations".equals(pack.getNsURI()))
               {
                  annotationsPackage = (EPackage) content;
               }
            }
         }

         // check for existence of both packages
         if (enginesPackage == null || annotationsPackage == null)
         {
            return error("There are no valid engines available!");
         }
      }

      // search host
      Collection<EObject> host = null;
      {
         // create resource
         Resource hostResource = resourceSet.createResource(URI.createPlatformResourceURI(hostPath, true));

         // load
         task("Loading host graph resource");
         try
         {
            hostResource.load(getLoadOptions(hostResource));

            // this call is very heavy, but otherwise some parts of the model may not be analyzed
            debug("Resolving all proxies on the host graph resource.");
            EcoreUtil.resolveAll(hostResource.getResourceSet());
            info("Host graph resource loaded.");
         }
         catch (IOException e)
         {
            e.printStackTrace();
            return error("The host graph input resource could not be loaded: " + e.getLocalizedMessage());
         }

         // go through contents
         host = hostResource.getContents();
      }

      // create, configure and start engine
      inferenceEngine.setAnnotationEvaluator(evaluator);
      inferenceEngine.setCatalog(catalog);
      inferenceEngine.setHost(host);
      inferenceEngine.setEnginePackage(enginesPackage);
      inferenceEngine.setSearchForAdditionalElements(isAnnotateAdditionalElements);

      // save the generated engines
      {
         // get output resource
         URI outputURI = URI.createPlatformResourceURI(catalogPath, true).appendFileExtension("ecore");
         Resource outputResource = null;

         // try to get existing resource
         task("Preparing output resource");
         try
         {
            outputResource = resourceSet.getResource(outputURI, true);
            outputResource.getContents().clear();
            debug("Existing resource found, it will be overwritten.");
         }
         catch (WrappedException e)
         {
            debug("Creating new output resource...");
         }

         if (outputResource == null)
         {
            try
            {
               outputResource = resourceSet.createResource(outputURI);
               outputResource.save(Collections.emptyMap());
               outputResource.load(resourceSet.getLoadOptions());
               debug("New output resource has been created.");
            }
            catch (WrappedException e)
            {
               return error("Could not create output resource: " + e.exception().getLocalizedMessage());
            }
            catch (IOException io)
            {
               return error("Could not create output resource: " + io.getLocalizedMessage());
            }
         }

         // check for existence
         if (outputResource == null || !outputResource.isLoaded())
         {
            return error("Output resource could not be loaded!");
         }

         // add the generated packages
         outputResource.getContents().add(annotationsPackage); // FIXME: do we need it
         outputResource.getContents().add(enginesPackage);

         // save resources
         task("Saving generated resource");
         try
         {
            outputResource.save(Collections.emptyMap());
            debug("Output resource has been saved.");
         }
         catch (IOException e)
         {
            e.printStackTrace();
            return error("Could not save output resource:" + e.getLocalizedMessage());
         }
      }

      return inferenceEngine.start(monitor);
   }


   private Map<Object, Object> getLoadOptions(Resource resource)
   {
      Map<Object, Object> options = resourceSet.getLoadOptions();

      options.put(XMLResource.OPTION_DEFER_ATTACHMENT, Boolean.TRUE);
      options.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
      options.put(XMLResource.OPTION_USE_DEPRECATED_METHODS, Boolean.TRUE);
      options.put(XMLResource.OPTION_USE_PARSER_POOL, new XMLParserPoolImpl());
      options.put(XMLResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP, new HashMap<Object, Object>());
      ((ResourceImpl) resource).setIntrinsicIDToEObjectMap(new HashMap<String, EObject>());

      return options;
   }


   public InferenceEngine getEngine()
   {
      return inferenceEngine;
   }
}
