package org.reclipse.generator.generation;


import java.io.File;


/**
 * @author mm
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3535 $ $Date: 2007-03-22 02:30:24 +0100 (Do, 22 Mrz 2007) $
 */
public class FileInformation
{

   private String name;


   public String getName()
   {
      return this.name;
   }


   public void setName(String name)
   {
      this.name = name;
   }


   private String path;


   public String getPath()
   {
      return this.path;
   }


   public void setPath(String path)
   {
      this.path = path;
   }


   private String content;


   public String getContent()
   {
      return this.content;
   }


   public void setContent(String content)
   {
      this.content = content;
   }


   private File file;


   public File getFile()
   {
      return this.file;
   }


   public void setFile(File file)
   {
      this.file = file;
      setName(this.file.getName());
      setPath(this.file.getAbsolutePath());
   }

}
