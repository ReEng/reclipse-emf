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
package org.reclipse.tracer.model.definition.xml;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

//import org.reclipse.commons.logging.Logger;
import org.reclipse.tracer.model.definition.TraceDefinition;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;


/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4278 $ $Date: 2010-02-18 13:32:16 +0100 (Do, 18 Feb 2010) $
 */
public class TraceDefinitionReader
{

   public static TraceDefinition load(String fileName) throws FileNotFoundException
   {
      File file = new File(fileName);

      return load(file);
   }


   /**
    * Loads trace definition from given file. If the given file is a zipped file, it tries to load
    * the trace definition from from an entry in the zip file named
    * TraceDefinition.xtracedefinition.
    * 
    * @param file An XML file containing a trace definition or a zipped file containing one entry,
    *           named TraceDefinition.xtracedefinition.
    * @return A trace definition, if loading was successfull, otherwise null
    * @throws FileNotFoundException
    */
   public static TraceDefinition load(File file) throws FileNotFoundException
   {
      try
      {
         ZipFile zipFile = new ZipFile(file);
         ZipEntry zipEntry = zipFile.getEntry(ITraceDefinitionConstants.FILE_NAME);
         InputStream inputStream = zipFile.getInputStream(zipEntry);
         InputStreamReader reader = new InputStreamReader(inputStream);
         BufferedReader bufferedReader = new BufferedReader(reader);

         return load(bufferedReader);
      }
      catch (ZipException exception)
      {
         // do nothing and try to load the file as plain xml file
      }
      catch (IOException exception)
      {
         // Logger.logError(exception);
      }

      // file is not zipped, maybe it's a plain xml file
      return load(new FileReader(file));
   }


   public static TraceDefinition load(Reader reader)
   {
      TraceDefinition traceDefinition = null;

      try
      {
         TraceDefinitionSaxHandler handler = new TraceDefinitionSaxHandler();

         SAXParserFactory factory = SAXParserFactory.newInstance();
         factory.setValidating(true);
         factory.setNamespaceAware(true);

         XMLReader xmlReader = factory.newSAXParser().getXMLReader();
         xmlReader.setContentHandler(handler);
         xmlReader.setErrorHandler(handler);
         xmlReader.setEntityResolver(handler);

         xmlReader.parse(new InputSource(reader));

         traceDefinition = handler.getTraceDefinition();

         reader.close();
      }
      catch (SAXException exception)
      {
         // Logger.logError(exception);
      }
      catch (ParserConfigurationException exception)
      {
         // Logger.logError(exception);
      }
      catch (IOException exception)
      {
         // Logger.logError(exception);
      }

      return traceDefinition;
   }

}
