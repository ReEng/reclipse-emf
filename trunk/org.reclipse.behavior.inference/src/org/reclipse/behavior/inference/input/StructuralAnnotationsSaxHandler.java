package org.reclipse.behavior.inference.input;


import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.reclipse.behavior.inference.BehavioralAnalysisPlugin;
import org.reclipse.behavior.inference.StructuralAnnotation;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3805 $ $Date: 2007-09-13 19:57:44 +0200 (Do, 13 Sep 2007) $
 */
public class StructuralAnnotationsSaxHandler extends DefaultHandler
{

   private static final String SYSTEM_ID_RESOURCE = "org/reclipse/behavior/inference/dtds/StructuralAnnotations.dtd";


   private StructuralAnnotation currentAnnotation;


   private Set<StructuralAnnotation> annotations;


   public void setAnnotations(final Set<StructuralAnnotation> annotations)
   {
      this.annotations = annotations;
   }


   public Set<StructuralAnnotation> getAnnotations()
   {
      return this.annotations;
   }


   /**
    * @throws IOException
    * @see org.xml.sax.EntityResolver#resolveEntity(java.lang.String, java.lang.String)
    */
   @Override
   public InputSource resolveEntity(final String publicId, final String systemId)
         throws SAXException,
            IOException
   {
      if (IStructuralAnnotationsConstants.SYSTEM_ID.equals(systemId))
      {
         ClassLoader classLoader = getClass().getClassLoader();
         if (classLoader == null)
         {
            classLoader = ClassLoader.getSystemClassLoader();
         }

         final URL systemIdURL = classLoader.getResource(SYSTEM_ID_RESOURCE);

         if (systemIdURL != null)
         {
            try
            {
               return new InputSource(systemIdURL.openStream());
            }
            catch (final Exception e)
            {
               BehavioralAnalysisPlugin.logError(
                     "Could not resolve SYSTEM or PUBLIC"
                           + " reference for DTD.", e);
               throw new SAXException(e);
            }
         }
      }

      return super.resolveEntity(publicId, systemId);
   }


   /**
    * @see org.xml.sax.ContentHandler#startDocument()
    */
   @Override
   public void startDocument() throws SAXException
   {
      if (this.annotations != null)
      {
         this.annotations.clear();
      }
      else
      {
         this.annotations = new HashSet();
      }
   }


   /**
    * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String,
    *      java.lang.String, org.xml.sax.Attributes)
    */
   @Override
   public void startElement(final String uri, final String name,
         final String qName, final Attributes attrs)
   {
      try
      {
         if (IStructuralAnnotationsConstants.STRUCTURAL_ANNOTATION_TAG
               .equals(name))
         {
            this.currentAnnotation = createStructuralAnnotation(attrs);
         }
         else if (IStructuralAnnotationsConstants.BOUND_OBJECT_TAG.equals(name))
         {
            addBinding(attrs);
         }
      }
      catch (final Exception e)
      {
         BehavioralAnalysisPlugin.logError(
               "Unexpected exception in parsing tracer input.", e);
      }
   }


   /**
    * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String,
    *      java.lang.String)
    */
   @Override
   public void endElement(final String uri, final String name,
         final String qName)
   {
      if (IStructuralAnnotationsConstants.STRUCTURAL_ANNOTATION_TAG
            .equals(name))
      {
         this.annotations.add(this.currentAnnotation);
         this.currentAnnotation = null;
      }
   }


   /**
    * @see org.xml.sax.ErrorHandler#warning(org.xml.sax.SAXParseException)
    */
   @Override
   public void warning(final SAXParseException exception)
   {
      BehavioralAnalysisPlugin.logError("XML Parse warning in line "
            + exception.getLineNumber() + ":", exception);
   }


   /**
    * @see org.xml.sax.ErrorHandler#error(org.xml.sax.SAXParseException)
    */
   @Override
   public void error(final SAXParseException exception)
   {
      BehavioralAnalysisPlugin.logError("XML Parse Error in line "
            + exception.getLineNumber() + ":", exception);
   }


   /**
    * @see org.xml.sax.ErrorHandler#fatalError(org.xml.sax.SAXParseException)
    */
   @Override
   public void fatalError(final SAXParseException exception)
   {
      BehavioralAnalysisPlugin.logError("Fatal XML Parse Error in line "
            + exception.getLineNumber() + ":", exception);
   }


   private StructuralAnnotation createStructuralAnnotation(
         final Attributes attrs)
   {
      final StructuralAnnotation annotation = new StructuralAnnotation();

      annotation.setType(attrs
            .getValue(IStructuralAnnotationsConstants.NAME_ATTRIBUTE));
      final String posBelief = attrs
            .getValue(IStructuralAnnotationsConstants.FUZZY_BELIEF_ATTRIBUTE);
      if (posBelief != null)
      {
         try
         {
            annotation.setFuzzyBelief(Double.valueOf(posBelief));
         }
         catch (final NumberFormatException e)
         {
            annotation.setFuzzyBelief(0);
         }
      }
      else
      {
         annotation.setFuzzyBelief(0);
      }

      return annotation;
   }


   private void addBinding(final Attributes attrs)
   {
      final String key = attrs
            .getValue(IStructuralAnnotationsConstants.KEY_ATTRIBUTE);
      final String name = attrs
            .getValue(IStructuralAnnotationsConstants.NAME_ATTRIBUTE);

      this.currentAnnotation.addToNodes(key, name);
   }

}
