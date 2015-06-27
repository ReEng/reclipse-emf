package org.reclipse.generator.generation;


/**
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author lowende
 */
public abstract class AbstractGeneratorTask implements IGeneratorTask
{

   private AbstractGenerator generator;


   public AbstractGeneratorTask(AbstractGenerator generator)
   {
      this.generator = generator;
   }


   protected void fireOutput(String message)
   {
      generator.append(message);
   }
}
