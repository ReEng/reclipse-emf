/**
 * Copyright 2011 University of Paderborn. All rights reserved. This program and the accompanying materials are made
 * available under the terms of the GNU Lesser General Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/lgpl.html
 * 
 * Contributors: Aljoscha Hark - initial API and implementation.
 */
package org.reclipse.patterns.structure.generator.ui;


import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.fujaba.commons.console.AbstractProcessConsoleJob;
import org.fujaba.commons.console.ReportLevel;
import org.reclipse.structure.generator.Generator;
import org.reclipse.structure.specification.PSCatalog;


/**
 * This class generates story patterns from a given {@link PSCatalog catalog} that can be used to search those patterns
 * on host graphs.
 */
public class ExportPatternsJob extends AbstractProcessConsoleJob
{
   private final String inputPath;

   private final String outputPath;

   private final ResourceSet resourceSet;

   private final Generator generator;


   /**
    * Creates a new job that generates story patterns for the whole catalog.
    * 
    * @param inputPath The path to a (platform) resource that contains a {@link PSCatalog catalog}.
    * @param outputPath The path to the (platform) resource that should be created.
    */
   public ExportPatternsJob(String inputPath, String outputPath, ReportLevel reportLevel)
   {
      super("Reclipse", "Story Diagram Generation", outputPath, reportLevel);

      this.inputPath = inputPath;
      this.outputPath = outputPath;

      resourceSet = new ResourceSetImpl();
      generator = new Generator(this);
   }


   @Override
   public IStatus start(IProgressMonitor monitor)
   {
      // create resource
      Resource inputResource = resourceSet.createResource(URI.createPlatformResourceURI(inputPath, true));

      // load
      task("Loading catalog input resource");
      try
      {
         inputResource.load(resourceSet.getLoadOptions());
         info("Catalog input resource loaded.");
      }
      catch (IOException e)
      {
         e.printStackTrace();
         return error("The input file could not be loaded: " + e.getLocalizedMessage());
      }

      // search catalog
      PSCatalog catalog = null;
      for (EObject content : inputResource.getContents())
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
         return error("The input file does not contain a valid catalog!");
      }

      // get output resource
      URI outputURI = URI.createPlatformResourceURI(outputPath, true);
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
         info("Creating new output resource");
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

      // start generation
      task("Starting generation process");
      generator.generate(outputResource.getContents(), catalog);

      // save resources
      task("Saving generated resource");
      try
      {
         outputResource.save(Collections.emptyMap());
         info("Output resource has been saved.");
      }
      catch (IOException e)
      {
         e.printStackTrace();
         return error("Could not save output resource: " + e.getLocalizedMessage());
      }

      return Status.OK_STATUS;
   }
}
