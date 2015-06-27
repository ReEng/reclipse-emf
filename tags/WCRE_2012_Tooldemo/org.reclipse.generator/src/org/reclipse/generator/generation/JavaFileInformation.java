package org.reclipse.generator.generation;


import java.io.File;


/**
 * @author mm
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3535 $ $Date: 2007-03-22 02:30:24 +0100 (Do, 22 Mrz 2007) $
 */
public class JavaFileInformation extends FileInformation
{

   private String packageName;


   public String getPackageName()
   {
      return this.packageName;
   }


   public void setPackageName(String packageName)
   {
      this.packageName = packageName;
   }


   private File classFile;


   public File getClassFile()
   {
      return this.classFile;
   }


   public void setClassFile(File classFile)
   {
      this.classFile = classFile;
   }

}
