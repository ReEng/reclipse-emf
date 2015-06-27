package org.reclipse.generator.compilation;


import java.util.Set;

import org.reclipse.generator.generation.AbstractGenerator;
import org.reclipse.generator.generation.FileInformation;
import org.reclipse.generator.generation.JavaFileInformation;


/**
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author mm
 */
public abstract class JavaCompiler
{

   public abstract void compileJavaFiles(AbstractGenerator output,
         Set<JavaFileInformation> javaFiles) throws Exception;


   public abstract void exportNonJavaFiles(Set<FileInformation> nonJavaFiles)
         throws Exception;


   /**
    * Remove all temporarily generated files and empty the containers. This method is executed after
    * completing all generation tasks.
    */
   public abstract void cleanUp();
}
