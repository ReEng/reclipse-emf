package org.reclipse.generator.generation;


import java.util.Set;


/**
 * @author mm
 * @author Last editor: $Author: travkin $
 * @version $Revision: 4028 $ $Date: 2009-04-20 11:12:25 +0200 (Mo, 20 Apr 2009) $
 */
public interface IGeneratorTask
{

   /**
    * Determines if this generator task is responsible for generating files
    * out of the given model element.
    * 
    * @param element a model element to be used to generate files from, e.g. a diagram
    * @return <code>true</code> if this generator is able to generate files for the
    *         given model element, <code>false</code> otherwise.
    */
   public boolean isResponsible(Object element);


   public void preProcess(Object element);


   public Set<FileInformation> generateFiles(Object element) throws Exception;

   
   /**
    * Implementing classes are to remove all the temporarily generated model elements
    * when this method is called.
    */
   public void removeTemporarilyGeneratedElements();

}
