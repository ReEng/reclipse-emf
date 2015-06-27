package org.reclipse.behavior.inference.output;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.reclipse.behavior.inference.Annotation;
import org.reclipse.behavior.inference.BehavioralAnalysisPlugin;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;


/**
 * @author lowende
 * @author Last editor: $Author:$
 * @version $Revision:$ $Date:$
 */
public class BehavioralAnalysisResultLoader
{

   public static Set<Annotation> load(final String fileName)
   {
      final File file = new File(fileName);

      return load(file);
   }


   public static Set<Annotation> load(final File file)
   {
      try
      {
         return load(new FileInputStream(file));
      }
      catch (final FileNotFoundException exception)
      {
         BehavioralAnalysisPlugin.logError("File '" + file.getAbsolutePath()
               + "' not found!", exception);
      }

      return null;
   }


   public static Set<Annotation> load(final InputStream inputStream)
   {
      Set<Annotation> annotations = null;

      try
      {
         final BehavioralAnalysisResultSaxHandler handler = new BehavioralAnalysisResultSaxHandler();

         final SAXParserFactory factory = SAXParserFactory.newInstance();
         factory.setValidating(false);
         factory.setNamespaceAware(true);

         final XMLReader xmlReader = factory.newSAXParser().getXMLReader();
         xmlReader.setContentHandler(handler);
         xmlReader.setErrorHandler(handler);
         xmlReader.setEntityResolver(handler);

         xmlReader.parse(new InputSource(inputStream));

         annotations = handler.getAnnotations();

         inputStream.close();
      }
      catch (final FileNotFoundException e)
      {
         BehavioralAnalysisPlugin.logError(
               "Behavioral analysis results file not found.", e);
      }
      catch (final SAXException e)
      {
         BehavioralAnalysisPlugin.logError(
               "Error parsing behavioral analysis results XML file:", e);
      }
      catch (final ParserConfigurationException e)
      {
         BehavioralAnalysisPlugin.logError(
               "Error parsing behavioral analysis results XML file:", e);
      }
      catch (final IOException e)
      {
         BehavioralAnalysisPlugin.logError(
               "Error opening behavioral analysis results XML file:", e);
      }

      return annotations;
   }

}
