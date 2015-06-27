/*
 * The FUJABA ToolSuite RE project:
 *
 *   FUJABA is the acronym for 'From Uml to Java And Back Again'
 *   and originally aims to provide an environment for round-trip
 *   engineering using UML as visual programming language. During
 *   the last years, the environment has become a base for several
 *   research activities, e.g. distributed software, database
 *   systems, modelling mechanical and electrical systems and
 *   their simulation. Thus, the environment has become a project,
 *   where this source code is part of. Further details are avail-
 *   able via http://www.fujaba.de
 *
 *      Copyright (C) Fujaba Development Group
 *
 *   This library is free software; you can redistribute it and/or
 *   modify it under the terms of the GNU Lesser General Public
 *   License as published by the Free Software Foundation; either
 *   version 2.1 of the License, or (at your option) any later version.
 *
 *   You should have received a copy of the GNU Lesser General Public
 *   License along with this library; if not, write to the Free
 *   Software Foundation, Inc., 59 Temple Place, Suite 330, Boston,
 *   MA 02111-1307, USA or download the license under
 *   http://www.gnu.org/copyleft/lesser.html
 *
 * WARRANTY:
 *
 *   This library is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *   GNU Lesser General Public License for more details.
 *
 * Contact address:
 *
 *   Fujaba Management Board
 *   Software Engineering Group
 *   University of Paderborn
 *   Warburgerstr. 100
 *   D-33098 Paderborn
 *   Germany
 *
 *   URL  : http://www.fujaba.de
 *   email: info@fujaba.de
 *
 */
package org.reclipse.behavior.inference.input;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.reclipse.behavior.inference.StructuralAnnotation;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3736 $ $Date: 2007-09-04 21:16:47 +0200 (Di, 04 Sep 2007) $
 */
public class StructuralAnnotationsWriter
{

   private final Set<StructuralAnnotation> annotations;


   /**
    * @param annotations the annotations to be saved.
    */
   public StructuralAnnotationsWriter(
         final Set<StructuralAnnotation> annotations)
   {
      this.annotations = annotations;
   }


   /**
    * @param file The file to save the document in.
    * @return true, if file has been written
    */
   public boolean save(String fileName, final boolean zipped)
   {
      try
      {
         Writer writer;
         ZipOutputStream zipOutputStream = null;
         if (zipped)
         {
            if (!fileName
                  .endsWith(IStructuralAnnotationsConstants.ZIP_FILE_SUFFIX))
            {
               fileName += IStructuralAnnotationsConstants.ZIP_FILE_SUFFIX;
            }
            final File file = new File(fileName);
            final FileOutputStream fileOutputStream = new FileOutputStream(file);
            zipOutputStream = new ZipOutputStream(fileOutputStream);
            final ZipEntry zipEntry = new ZipEntry(
                  IStructuralAnnotationsConstants.FILE_NAME);
            zipOutputStream.putNextEntry(zipEntry);

            final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                  zipOutputStream);
            writer = new BufferedWriter(outputStreamWriter);
         }
         else
         {
            if (!fileName
                  .endsWith(IStructuralAnnotationsConstants.XML_FILE_SUFFIX))
            {
               fileName += IStructuralAnnotationsConstants.XML_FILE_SUFFIX;
            }
            final FileWriter fileWriter = new FileWriter(fileName);
            writer = new BufferedWriter(fileWriter);
         }

         generateXMLDocument(writer);
         writer.flush();
         writer.close();

         if (zipOutputStream != null)
         {
            zipOutputStream.close();
         }
      }
      catch (final IOException e)
      {
         return false;
      }

      return true;
   }


   public void generateXMLDocument(final Writer writer) throws IOException
   {
      writer.write("<?xml version=\"1.0\" standalone=\"no\"?>\n\n");
      writer.write("<!DOCTYPE ");
      writer.write(IStructuralAnnotationsConstants.STRUCTURAL_ANNOTATIONS_TAG);
      writer.write(" SYSTEM \"");
      writer.write(IStructuralAnnotationsConstants.SYSTEM_ID);
      writer.write("\">\n\n");

      generateXMLTagForGFRN(writer);
   }


   private void generateXMLTagForGFRN(final Writer writer) throws IOException
   {
      writer.write("<");
      writer.write(IStructuralAnnotationsConstants.STRUCTURAL_ANNOTATIONS_TAG);
      writer.write(" ");
      writer.write(IStructuralAnnotationsConstants.SIZE_ATTRIBUTE);
      writer.write("=\"");
      writer.write(Integer.toString(this.annotations.size()));
      writer.write("\">\n\n");

      for (final StructuralAnnotation annotation : this.annotations)
      {
         generateXMLTagForGFRNAnnotation(writer, annotation);
      }

      writer.write("</");
      writer.write(IStructuralAnnotationsConstants.STRUCTURAL_ANNOTATIONS_TAG);
      writer.write(">\n");
   }


   private void generateXMLTagForGFRNAnnotation(final Writer writer,
         final StructuralAnnotation annotation) throws IOException
   {
      writer.write("  <");
      writer.write(IStructuralAnnotationsConstants.STRUCTURAL_ANNOTATION_TAG);
      writer.write(" ");
      writer.write(IStructuralAnnotationsConstants.NAME_ATTRIBUTE);
      writer.write("=\"");
      writer.write(annotation.getType());
      writer.write("\" ");
      writer.write(IStructuralAnnotationsConstants.FUZZY_BELIEF_ATTRIBUTE);
      writer.write("=\"");
      writer.write(Double.toString(annotation.getFuzzyBelief()));
      writer.write("\">\n");

      for (final String key : annotation.keysOfNodes())
      {
         generateXMLTagForBinding(writer, key, annotation.getFromNodes(key));
      }

      writer.write("  </");
      writer.write(IStructuralAnnotationsConstants.STRUCTURAL_ANNOTATION_TAG);
      writer.write(">\n\n");
   }


   private void generateXMLTagForBinding(final Writer writer, final String key,
         final String value) throws IOException
   {
      writer.write("    <");
      writer.write(IStructuralAnnotationsConstants.BOUND_OBJECT_TAG);
      writer.write(" ");
      writer.write(IStructuralAnnotationsConstants.KEY_ATTRIBUTE);
      writer.write("=\"");
      writer.write(key);
      writer.write("\" ");
      writer.write(IStructuralAnnotationsConstants.NAME_ATTRIBUTE);
      writer.write("=\"");
      writer.write(value);
      writer.write("\"/>\n");
   }

}
