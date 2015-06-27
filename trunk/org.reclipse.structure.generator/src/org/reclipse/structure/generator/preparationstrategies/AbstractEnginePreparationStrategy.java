package org.reclipse.structure.generator.preparationstrategies;


import java.io.IOException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.fujaba.commons.console.IControllableReportListener;
import org.reclipse.structure.specification.PSCatalog;


/**
 * This class implements the abstract strategy of a strategy pattern. It allows clients (e.g.,
 * Reclipse and Archimetrix) to choose different strategies to prepare the detection engines for a
 * pattern detection.
 * 
 * @author mvdetten
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public abstract class AbstractEnginePreparationStrategy
{

   protected Resource catalogResource;
   
   protected Resource enginesResource;

   protected IControllableReportListener reporter;

   protected String catalogPath;


   public AbstractEnginePreparationStrategy(Resource catalogResource)
   {
      this.catalogResource = catalogResource;
   }


   /**
    * This abstract method can be overridden by concrete strategies in order to load existing
    * engines or generate new ones.
    */
   public abstract IStatus prepareEngines();


   /**
    * This method loads the pattern catalog from the given path.
    * 
    * @return The loaded catalog or null if no catalog exists in the loaded resource.
    * @throws IOException In case something goes wrong with accessing the resource at the given
    *            path.
    */
   protected PSCatalog loadCatalog() throws IOException
   {
      PSCatalog catalog = null;

      this.reporter.task("Loading catalog input resource");

      this.catalogResource.load(this.catalogResource.getResourceSet().getLoadOptions());
      this.reporter.debug("Catalog input resource loaded.");

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
    * @return Returns the reporter.
    */
   public IControllableReportListener getReporter()
   {
      return this.reporter;
   }


   /**
    * @param reporter The reporter to set.
    */
   public void setReporter(IControllableReportListener reporter)
   {
      this.reporter = reporter;
   }


}
