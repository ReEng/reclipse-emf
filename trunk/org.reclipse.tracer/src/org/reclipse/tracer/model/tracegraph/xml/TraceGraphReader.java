package org.reclipse.tracer.model.tracegraph.xml;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

//import org.reclipse.commons.logging.Logger;
import org.reclipse.tracer.model.tracegraph.TGTrace;
import org.reclipse.tracer.model.tracegraph.TGTracePath;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;


/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4040 $ $Date: 2009-05-07 15:01:48 +0200 (Do, 07 Mai 2009) $
 */
public class TraceGraphReader
{

   public static TGTrace load(final String fileName)
   {
      final File file = new File(fileName);

      return load(file);
   }


   public static TGTrace load(final File file)
   {
      try
      {
         final ZipFile zipFile = new ZipFile(file);
         final ZipEntry zipEntry = zipFile.getEntry(ITraceConstants.FILE_NAME);
         final InputStream inputStream = zipFile.getInputStream(zipEntry);
         final BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

         return load(bufferedInputStream);
      }
      catch (final ZipException exception)
      {
         // ignore exception, file is not zipped
      }
      catch (final IOException exception)
      {
         // Logger.logError(exception);
      }

      // file is not zipped, maybe it's a plain xml file
      try
      {
         final FileInputStream fileInputStream = new FileInputStream(file);
         final BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
         return load(bufferedInputStream);
      }
      catch (final FileNotFoundException exception)
      {
         // Logger.logError(exception);
      }

      return null;
   }


   private static TGTrace load(final InputStream inputStream)
   {
      TGTrace mcgTrace = null;

      try
      {
         final TraceGraphSaxHandler handler = new TraceGraphSaxHandler();

         final SAXParserFactory factory = SAXParserFactory.newInstance();
         factory.setValidating(false);
         factory.setNamespaceAware(true);

         final XMLReader xmlReader = factory.newSAXParser().getXMLReader();
         xmlReader.setContentHandler(handler);
         xmlReader.setErrorHandler(handler);
         xmlReader.setEntityResolver(handler);

         xmlReader.parse(new InputSource(inputStream));

         mcgTrace = handler.getTGTrace();

         inputStream.close();
      }
      catch (final FileNotFoundException e)
      {
         // Logger.logError("Trace graph file not found.", e);
      }
      catch (final SAXException e)
      {
         // Logger.logError("Error parsing trace graph XML file:", e);
      }
      catch (final ParserConfigurationException e)
      {
         // Logger.logError("Error parsing trace graph XML file:", e);
      }
      catch (final IOException e)
      {
         // Logger.logError("Error opening trace graph XML file:", e);
      }

      return mcgTrace;
   }

}
