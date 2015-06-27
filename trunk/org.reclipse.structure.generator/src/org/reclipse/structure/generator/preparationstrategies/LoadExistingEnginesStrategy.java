package org.reclipse.structure.generator.preparationstrategies;


import java.io.IOException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.resource.Resource;


/**
 * This strategy attempts to load existing, i.e. previously generated, pattern detection engines
 * from a given resource.
 * 
 * @author mvdetten
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 * @see AbstractEnginePreparationStrategy
 */
public class LoadExistingEnginesStrategy extends AbstractEnginePreparationStrategy
{

   /**
    * The constructor.
    * 
    * @param catalogResource The resource with the catalog that contains the pattern which are to be detected by the engines.
    * @param enginesResource The resource with the pattern detection engines.
    */
   public LoadExistingEnginesStrategy(Resource catalogResource, Resource enginesResource)
   {
      super(catalogResource);
      this.enginesResource = enginesResource;
   }

   /**
    * Loads the pattern catalog and after that the pattern detection engines. Produces an error when the loading fails. 
    */
   @Override
   public IStatus prepareEngines()
   {
      try
      {
         loadCatalog();
      }
      catch (IOException e)
      {
         return this.reporter.error("Pattern catalog could not be loaded: " + e.getMessage());
      }

      try
      {
         loadEnginesResource();
      }
      catch (IOException e)
      {
         return this.reporter.error("The input resource could not be loaded: " + e.getLocalizedMessage());
      }

      return Status.OK_STATUS;
   }


   /**
    * Loads the engines resource.
    * 
    * @throws IOException In case something goes wrong with the loading.
    */
   private void loadEnginesResource() throws IOException
   {
      this.reporter.task("Loading annotations and engines");
      this.enginesResource.load(this.catalogResource.getResourceSet().getLoadOptions());
      this.reporter.debug("Annotations and engines input resource loaded.");
   }

}
