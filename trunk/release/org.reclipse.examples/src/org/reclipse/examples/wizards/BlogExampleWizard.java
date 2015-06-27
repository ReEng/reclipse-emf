package org.reclipse.examples.wizards;


public class BlogExampleWizard extends AbstractExampleWizard
{

   @Override
   protected String getExampleDescription()
   {
      return "Create an example blog project showing different structural patterns.";
   }


   @Override
   protected String getExampleTitle()
   {
      return "Reclipse Blog Example";
   }


   @Override
   protected String getInitialProjectName()
   {
      return "org.reclipse.examples.blog";
   }


   @Override
   protected String getZipFilePath()
   {
      return "examples/blog.zip";
   }
}
