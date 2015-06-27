package org.reclipse.structure.inference;


import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.fujaba.commons.console.AbstractProcessConsoleJob;
import org.fujaba.commons.console.ReportLevel;
import org.reclipse.structure.generator.GeneratorConstants;
import org.reclipse.structure.specification.PSCatalog;


/**
 * {@link org.eclipse.core.runtime.jobs.Job Job} that detects patterns from a given catalog in a
 * given host graph by executing detection engines.
 * 
 * @author mvdetten
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class DetectPatternsJob extends AbstractProcessConsoleJob
{

   private boolean isAnnotateAdditionalElements;

   private final InferenceEngine inferenceEngine;

   private IAnnotationEvaluator evaluator;

   private PSCatalog catalog;

   private final Resource catalogResource;

   private final Resource enginesResource;

   private final Resource hostGraphResource;


   /**
    * The constructor
    * 
    * @param catalogResource A resource that contains the catalog with the patterns that are to be
    *           detected.
    * @param enginesResource A resource that contains the (loaded or generated) detection engines.
    * @param hostGraphResource A resource that contains the host graph in which the patterns are to
    *           be detected.
    * @param reportLevel The report level, as chosen by the user.
    */
   public DetectPatternsJob(Resource catalogResource, Resource enginesResource, Resource hostGraphResource,
         ReportLevel reportLevel)
   {
      super("Reclipse", "Structural Inference", "Structural inference to detect patterns in a given host graph",
            reportLevel);
      this.catalogResource = catalogResource;
      this.enginesResource = enginesResource;
      this.hostGraphResource = hostGraphResource;
      boolean isDebug = ReportLevel.DEBUG.equals(reportLevel);
      this.inferenceEngine = new InterpreterInferenceEngine(this, isDebug);
   }


   /**
    * Starts the pattern detection. First the catalog, detection engines, and host graph are loaded
    * from the corresponding resources. Then the inference engine that executes the actual detection
    * is configured and started.
    * 
    * @see org.fujaba.commons.console.AbstractProcessConsoleJob#start(org.eclipse.core.runtime.IProgressMonitor)
    */
   @Override
   public IStatus start(IProgressMonitor monitor)
   {
      monitor.beginTask(getName(), IProgressMonitor.UNKNOWN);

      this.catalog = loadCatalog();

      EPackage enginesPackage = loadEnginesPackage();

      Collection<EObject> hostGraph = loadHostGraph();

      // create, configure and start engine
      inferenceEngine.setAnnotationEvaluator(evaluator);
      inferenceEngine.setCatalog(catalog);
      inferenceEngine.setHost(hostGraph);
      inferenceEngine.setEnginePackage(enginesPackage);
      inferenceEngine.setSearchForAdditionalElements(isAnnotateAdditionalElements);

      return inferenceEngine.start(monitor);
   }


   /**
    * Loads the pattern specification catalog from the corresponding resource. Produces an error
    * when the catalog cannot be loaded.
    * 
    * @return The pattern catalog that was loaded from the catalog resource.
    */
   private PSCatalog loadCatalog()
   {
      PSCatalog catalog = null;

      task("Loading catalog input resource");

      try
      {
         this.catalogResource.load(this.getLoadOptions(catalogResource));
      }
      catch (IOException e)
      {
         error("Catalog could not be loaded: " + e.getMessage());
      }
      debug("Catalog input resource loaded.");

      // get catalog from loaded resource
      for (EObject content : this.catalogResource.getContents())
      {
         if (content instanceof PSCatalog)
         {
            return catalog = (PSCatalog) content;
         }
      }

      return catalog;
   }


   /**
    * Loads the engines resource and then subsequently the package with the engines from that.
    * 
    * @return An EPackage that contains the pattern detection engines.
    */
   private EPackage loadEnginesPackage()
   {
      // Unload and reload the resource to make sure that newly generated engines are really found.
      this.enginesResource.unload();
      try
      {
         this.enginesResource.load(this.getLoadOptions(enginesResource));
      }
      catch (IOException e)
      {
         error("Engines resource could not be loaded: " + e.getMessage());
      }
      EList<EObject> enginesAndAnnotations = this.enginesResource.getContents();

      // search annotations and engines packages
      EPackage enginesPackage = null;
      // go through contents
      for (EObject content : enginesAndAnnotations)
      {
         if (content instanceof EPackage)
         {
            EPackage pack = (EPackage) content;
            this.enginesResource.getResourceSet().getPackageRegistry().put(pack.getNsURI(), pack);

            if (GeneratorConstants.PACKAGE_URI.equals(pack.getNsURI()))
            {
               enginesPackage = (EPackage) content;
            }
         }
      }

      if (enginesPackage == null)
      {
         error("There are no valid engines available in the corresponding resource!");
      }
      return enginesPackage;
   }


   /**
    * Loads the host graph from the corresponding resource specified by the user. Resolves all its
    * references in case that the host graph is distributed over several models.
    * 
    * @return The EObjects in the host graph in form of a list.
    */
   private Collection<EObject> loadHostGraph()
   {
      Collection<EObject> hostGraph;

      // load
      task("Loading host graph resource");
      try
      {
         hostGraphResource.load(this.getLoadOptions(hostGraphResource));

         // this call is very heavy, but otherwise some parts of the model may not be analyzed
         debug("Resolving all proxies on the host graph resource.");
         EcoreUtil.resolveAll(hostGraphResource.getResourceSet());
         info("Host graph resource loaded.");
      }
      catch (IOException e)
      {
         error("The host graph input resource could not be loaded: " + e.getLocalizedMessage());
      }

      // go through contents
      hostGraph = hostGraphResource.getContents();
      return hostGraph;
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
   }


   public void setAnnotateAdditionalElements(boolean isAnnotateAdditionalElements)
   {
      this.isAnnotateAdditionalElements = isAnnotateAdditionalElements;
   }


   private Map<Object, Object> getLoadOptions(Resource resource)
   {
      Map<Object, Object> options = resource.getResourceSet().getLoadOptions();

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
