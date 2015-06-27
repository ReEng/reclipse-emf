package org.reclipse.generator.generation;


import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.resource.ImageDescriptor;
import org.fujaba.commons.console.AbstractProcessConsoleJob;
import org.reclipse.generator.IOutputListener;


/**
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author Oleg
 */
public abstract class AbstractGenerator extends AbstractProcessConsoleJob
{
   private Set<IOutputListener> listeners;


   protected Exception exception;


   protected List<IGeneratorTask> tasks;

   protected Set<Object> modelElements;

   protected File output;


   public AbstractGenerator(String category, String title, String description)
   {
      super(category, title, description);

      listeners = new HashSet<IOutputListener>();
      tasks = new ArrayList<IGeneratorTask>();
      modelElements = new HashSet<Object>();
   }


   public AbstractGenerator(String category, String title, String description,
         ImageDescriptor image)
   {
      super(category, title, description, image);

      listeners = new HashSet<IOutputListener>();
      tasks = new ArrayList<IGeneratorTask>();
      modelElements = new HashSet<Object>();
   }


   public void addGeneratorTask(IGeneratorTask task)
   {
      if (task != null)
      {
         tasks.add(task);
      }
   }


   public void addOutputListener(IOutputListener listener)
   {
      listeners.add(listener);
   }


   public void addToElementsToProcess(Object element)
   {
      if (element != null)
      {
         modelElements.add(element);
      }
   }


   public void append(String output)
   {
      info(output);

      for (IOutputListener listener : listeners)
      {
         listener.appendOutput(output);
      }
   }


   /**
    * This method initially tries to find a task that is responsible for handling the model element.
    * Then it iterates over all elements and tries to find a generation task that handles the
    * generation for this element. The tasks can be added by using the
    * addGeneratorTask(IGeneratorTask)-method.
    * 
    * @return A set of files that contains the generated code.
    * @throws Exception
    */
   protected Set<FileInformation> generateFiles() throws Exception
   {
      Set<FileInformation> generatedFiles = new HashSet<FileInformation>();

      // generate elements
      for (Object element : this.modelElements)
      {
         // pass diagram itself to registered tasks
         for (IGeneratorTask task : this.tasks)
         {
            if (task.isResponsible(element))
            {
               Set<FileInformation> files = task.generateFiles(element);
               if (files != null)
               {
                  generatedFiles.addAll(files);
               }
            }
         }
      }

      return generatedFiles;
   }


   public Exception getException()
   {
      return this.exception;
   }


   protected void preProcess()
   {
      // preprocess all elements
      for (Object element : this.modelElements)
      {
         // pass element itself to registered tasks
         for (IGeneratorTask task : this.tasks)
         {
            if (task.isResponsible(element))
            {
               task.preProcess(element);
            }
         }
      }
   }


   public void removeOutputListener(IOutputListener listener)
   {
      listeners.remove(listener);
   }


   public void setOutputTo(File file)
   {
      this.output = file;
   }
}
