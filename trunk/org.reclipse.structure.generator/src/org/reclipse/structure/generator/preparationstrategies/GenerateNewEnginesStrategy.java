package org.reclipse.structure.generator.preparationstrategies;


import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.reclipse.structure.generator.Generator;
import org.reclipse.structure.specification.PSCatalog;


/**
 * This strategy generates new pattern detection engines for a given pattern catalog.
 * 
 * @author mvdetten
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 * @see AbstractEnginePreparationStrategy
 */
public class GenerateNewEnginesStrategy extends AbstractEnginePreparationStrategy
{

   /**
    * The constructor.
    * 
    * @param catalogResource The catalog for which the detection engines should be generated.
    */
   public GenerateNewEnginesStrategy(Resource catalogResource)
   {
      super(catalogResource);
   }


   /**
    * Loads the pattern catalog, creates a new engines resource (or overwrites an existing one),
    * generates the detection engines and saves the resource.
    */
   @Override
   public IStatus prepareEngines()
   {
      PSCatalog catalog;
      try
      {
         catalog = loadCatalog();
      }
      catch (IOException e)
      {
         return this.reporter.error("Pattern catalog could not be loaded: " + e.getMessage());
      }

      try
      {
         createAndLoadEnginesResource();
      }
      catch (IOException io)
      {
         return this.reporter.error("Could not create engines resource: " + io.getLocalizedMessage());
      }

      // check for existence
      if (enginesResource == null || !enginesResource.isLoaded())
      {
         return this.reporter.error("Engines resource could not be loaded!");
      }

      generateEngines(catalog);

      try
      {
         saveGeneratedEngines();
      }
      catch (IOException e)
      {
         return this.reporter.error("Could not save engines resource:" + e.getLocalizedMessage());
      }

      return Status.OK_STATUS;
   }


   /**
    * Generates the detection engines for a given catalog. The generation result is put into a
    * collection of EObjects.
    * 
    * @param catalog The pattern catalog for which the engines will be generated.
    */
   protected void generateEngines(PSCatalog catalog)
   {
      Collection<EObject> contents;
      contents = new HashSet<EObject>();

      Generator generator = new Generator(reporter);
      generator.generate(contents, catalog);
      enginesResource.getContents().addAll(contents);
   }


   /**
    * Saves the resource with the generated pattern detection engines.
    * 
    * @throws IOException In case the saving goes wrong.
    */
   protected void saveGeneratedEngines() throws IOException
   {
      this.reporter.task("Saving generated engines resource");
      this.enginesResource.save(Collections.emptyMap());
      this.reporter.debug("Engines resource has been saved.");
   }


   /**
    * Loads the engines resource which has the name "'catalog name'.ecore" . If a persistent
    * resource of that name already exists, it is overwritten, i.e. loaded and emptied. If no such
    * resource exists, it is created.
    * 
    * @throws IOException In case something goes wrong with creating the new resource.
    */
   protected void createAndLoadEnginesResource() throws IOException
   {
      // get output resource
      URI enginesResourceURI = catalogResource.getURI().appendFileExtension("ecore");
      this.enginesResource = null;

      // try to get existing resource
      this.reporter.task("Preparing engines resource");
      try
      {
         this.enginesResource = this.catalogResource.getResourceSet().getResource(enginesResourceURI, true);
         this.enginesResource.getContents().clear();
         this.reporter.debug("Existing engines resource found, it will be overwritten.");
      }
      catch (WrappedException e)
      {
         this.reporter.debug("Creating new engines resource...");
      }

      if (this.enginesResource == null)
      {
         this.enginesResource = this.catalogResource.getResourceSet().createResource(enginesResourceURI);
         this.enginesResource.save(Collections.emptyMap());
         this.enginesResource.load(this.catalogResource.getResourceSet().getLoadOptions());
         this.reporter.debug("New engines resource has been created.");
      }
   }

}
