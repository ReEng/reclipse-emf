package org.reclipse.behavior.inference.input;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.reclipse.behavior.inference.BehavioralAnalysisPlugin;
import org.reclipse.behavior.inference.BehavioralPatternsCatalog;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3805 $ $Date: 2007-09-13 19:57:44 +0200 (Do, 13 Sep 2007) $
 */
public class BehavioralPatternsCatalogReader
{

   public static BehavioralPatternsCatalog load(final String fileName)
         throws FileNotFoundException
   {
      final File file = new File(fileName);
      return load(file);
   }


   public static BehavioralPatternsCatalog load(final File file)
         throws FileNotFoundException
   {
      return load(new FileReader(file));
   }


   public static BehavioralPatternsCatalog load(final Reader reader)
   {
      BehavioralPatternsCatalog catalog = null;

      try
      {
         final BehavioralPatternsCatalogSaxHandler handler = new BehavioralPatternsCatalogSaxHandler();

         final SAXParserFactory factory = SAXParserFactory.newInstance();
         factory.setValidating(true);
         factory.setNamespaceAware(true);

         final XMLReader xmlReader = factory.newSAXParser().getXMLReader();
         xmlReader.setContentHandler(handler);
         xmlReader.setErrorHandler(handler);
         xmlReader.setEntityResolver(handler);

         xmlReader.parse(new InputSource(reader));

         catalog = handler.getCatalog();

         reader.close();
      }
      catch (final SAXException e)
      {
         BehavioralAnalysisPlugin.logError(
               "Error parsing Behavioral Patterns Catalog.", e);
      }
      catch (final ParserConfigurationException e)
      {
         BehavioralAnalysisPlugin.logError(
               "Error parsing Behavioral Patterns Catalog.", e);
      }
      catch (final IOException e)
      {
         BehavioralAnalysisPlugin.logError(
               "Error opening Behavioral Patterns Catalog.", e);
      }

      return catalog;
   }

}
