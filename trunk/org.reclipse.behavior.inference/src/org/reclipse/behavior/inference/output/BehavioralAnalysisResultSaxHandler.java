package org.reclipse.behavior.inference.output;


import static org.reclipse.behavior.inference.output.IBehavioralAnalysisResultConstants.*;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.reclipse.behavior.inference.Annotation;
import org.reclipse.behavior.inference.BehavioralAnalysisPlugin;
import org.reclipse.behavior.inference.BehavioralAnnotation;
import org.reclipse.behavior.inference.StructuralAnnotation;
import org.reclipse.behavior.inference.Trace;
import org.reclipse.tracer.model.tracegraph.TGArgument;
import org.reclipse.tracer.model.tracegraph.TGFactory;
import org.reclipse.tracer.model.tracegraph.TGMethodCall;
import org.reclipse.tracer.model.tracegraph.TGObject;
import org.reclipse.tracer.model.tracegraph.TGObjectArgument;
import org.reclipse.tracer.model.tracegraph.TGPrimitiveArgument;
import org.reclipse.tracer.model.tracegraph.TGType;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * @author lowende
 * @author Last editor: $Author:$
 * @version $Revision:$ $Date:$
 */
public class BehavioralAnalysisResultSaxHandler extends DefaultHandler
{

   private static final String SYSTEM_ID_RESOURCE = "org/reclipse/patterns/behavior/inference/dtds/BehavioralAnalysisResult.dtd";


   private Set<Annotation> annotations;

   private Annotation currentAnnotation;

   private StructuralAnnotation currentStructuralAnnotation;

   private BehavioralAnnotation currentBehavioralAnnotation;

   private Trace currentTrace;

   private TGMethodCall currentMethodCall;

   private TGFactory factory;


   public Set<Annotation> getAnnotations()
   {
      return this.annotations;
   }


   /**
    * @see org.xml.sax.EntityResolver#resolveEntity(java.lang.String, java.lang.String)
    */
   @Override
   public InputSource resolveEntity(final String publicId, final String systemId)
         throws SAXException,
            IOException
   {
      if (SYSTEM_ID.equals(systemId))
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
               BehavioralAnalysisPlugin.logError("Could not resolve SYSTEM or "
                     + "PUBLIC reference for DTD.", e);
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
   public void startDocument()
   {
      this.annotations = new HashSet<Annotation>();
      this.factory = new TGFactory();
   }


   /**
    * @see org.xml.sax.ContentHandler#endDocument()
    */
   @Override
   public void endDocument()
   {
      this.factory = null;
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
         if (ANNOTATION_TAG.equals(name))
         {
            startAnnotationTag(attrs);
         }
         else if (STRUCTURAL_ANNOTATION_TAG.equals(name))
         {
            startStructuralAnnotationTag(attrs);
         }
         else if (BEHAVIORAL_ANNOTATION_TAG.equals(name))
         {
            startBehavioralAnnotationTag(attrs);
         }
         else if (BOUND_OBJECT_TAG.equals(name))
         {
            startBoundObjectTag(attrs);
         }
         else if (TRACE_TAG.equals(name))
         {
            startTraceTag(attrs);
         }
         else if (METHOD_CALL_TAG.equals(name))
         {
            startMethodCallTag(attrs);
         }
         else if (CALLER_TAG.equals(name))
         {
            startCallerTag(attrs);
         }
         else if (CALLEE_TAG.equals(name))
         {
            startCalleeTag(attrs);
         }
         else if (ARGUMENT_TAG.equals(name))
         {
            startArgumentTag(attrs);
         }
      }
      catch (final Exception e)
      {
         e.printStackTrace();
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
      if (ANNOTATION_TAG.equals(name))
      {
         this.currentAnnotation = null;
      }
      else if (STRUCTURAL_ANNOTATION_TAG.equals(name))
      {
         this.currentStructuralAnnotation = null;
      }
      else if (BEHAVIORAL_ANNOTATION_TAG.equals(name))
      {
         this.currentBehavioralAnnotation = null;
      }
      else if (TRACE_TAG.equals(name))
      {
         this.currentTrace = null;
      }
      else if (METHOD_CALL_TAG.equals(name))
      {
         this.currentMethodCall = null;
      }
   }


   /**
    * @see org.xml.sax.ErrorHandler#warning(org.xml.sax.SAXParseException)
    */
   @Override
   public void warning(final SAXParseException exception)
   {
      BehavioralAnalysisPlugin.logError("XML Parse warning in line "
            + exception.getLineNumber() + ": ", exception);
   }


   /**
    * @see org.xml.sax.ErrorHandler#error(org.xml.sax.SAXParseException)
    */
   @Override
   public void error(final SAXParseException exception)
   {
      BehavioralAnalysisPlugin.logError("XML Parse Error in line "
            + exception.getLineNumber() + ": ", exception);
   }


   /**
    * @see org.xml.sax.ErrorHandler#fatalError(org.xml.sax.SAXParseException)
    */
   @Override
   public void fatalError(final SAXParseException exception)
   {
      BehavioralAnalysisPlugin.logError("Fatal XML Parse Error in line "
            + exception.getLineNumber() + ": ", exception);
   }


   private void startAnnotationTag(final Attributes attrs)
   {
      this.currentAnnotation = new Annotation();
      this.currentAnnotation.setType(attrs.getValue(TYPE_ATTRIBUTE));

      this.annotations.add(this.currentAnnotation);
   }


   private void startStructuralAnnotationTag(final Attributes attrs)
   {
      this.currentStructuralAnnotation = new StructuralAnnotation();
      this.currentStructuralAnnotation
            .setType(this.currentAnnotation.getType());
      this.currentStructuralAnnotation.setFuzzyBelief(Double.valueOf(attrs
            .getValue(FUZZY_BELIEF_ATTRIBUTE)));

      this.currentAnnotation
            .setStructuralAnnotation(this.currentStructuralAnnotation);
   }


   private void startBehavioralAnnotationTag(final Attributes attrs)
   {
      this.currentBehavioralAnnotation = new BehavioralAnnotation();
      this.currentBehavioralAnnotation.setAcceptedTraces(Integer.valueOf(attrs
            .getValue(ACCEPTED_TRACES_ATTRIBUTE)));
      this.currentBehavioralAnnotation.setNotAcceptedTraces(Integer
            .valueOf(attrs.getValue(NOT_ACCEPTED_TRACES_ATTRIBUTE)));
      this.currentBehavioralAnnotation.setRejectedTraces(Integer.valueOf(attrs
            .getValue(REJECTED_TRACES_ATTRIBUTE)));
      this.currentBehavioralAnnotation.setPassedAcceptingStateTraces(Integer
            .valueOf(attrs.getValue(PASSED_ACCEPTING_STATE_TRACES_ATTRIBUTE)));
      this.currentBehavioralAnnotation.setAvgLengthAcceptedTraces(Float
            .valueOf(attrs.getValue(AVG_LENGTH_ACCEPTED_TRACES_ATTRIBUTE)));
      this.currentBehavioralAnnotation.setSymbolicExecution(Boolean
            .valueOf(attrs.getValue(SYMBOLIC_EXECUTION_ATTRIBUTE)));


      this.currentAnnotation
            .setBehavioralAnnotation(this.currentBehavioralAnnotation);
   }


   private void startBoundObjectTag(final Attributes attrs)
   {
      final String key = attrs.getValue(KEY_ATTRIBUTE);
      final String name = attrs.getValue(NAME_ATTRIBUTE);

      if (this.currentStructuralAnnotation != null)
      {
         this.currentStructuralAnnotation.addToNodes(key, name);
      }
      else
      {
         TGObject object = this.factory.getTGObject(name);
         if (object == null)
         {
            object = this.factory.createTGObject(name);
         }

         this.currentTrace.addToBindings(key, object);
      }
   }


   private void startTraceTag(final Attributes attrs)
   {
      this.currentTrace = new Trace();
      this.currentTrace.setId(Integer.valueOf(attrs.getValue(ID_ATTRIBUTE)));
      this.currentTrace.setResult(Integer.valueOf(attrs
            .getValue(RESULT_ATTRIBUTE)));
      this.currentTrace.setPassedAcceptingState(Integer.valueOf(attrs
            .getValue(PASSED_ACCEPTING_STATE_ATTRIBUTE)));
      this.currentTrace.setLengthOfAcceptedTrace(Integer.valueOf(attrs
            .getValue(LENGTH_OF_ACCEPTED_TRACE_ATTRIBUTE)));

      this.currentBehavioralAnnotation.addToTraces(this.currentTrace);
   }


   private void startMethodCallTag(final Attributes atts)
   {
      this.currentMethodCall = new TGMethodCall();
      this.currentMethodCall.setId(atts.getValue(ID_ATTRIBUTE));
      this.currentMethodCall.setName(atts.getValue(NAME_ATTRIBUTE));

      this.currentTrace.addToMatchedCalls(this.currentMethodCall);
   }


   private void startCallerTag(final Attributes attrs)
   {
      final String callerId = attrs.getValue(ID_ATTRIBUTE);
      TGObject caller = this.factory.getTGObject(callerId);

      if (caller == null)
      {
         caller = this.factory.createTGObject(callerId);
      }

      if (caller.getType() == null)
      {
         // set type
         final String typeName = attrs.getValue(TYPE_NAME_ATTRIBUTE);
         TGType type = this.factory.getTGType(typeName);
         if (type == null)
         {
            type = this.factory.createTGType(typeName);
         }
         caller.setType(type);
      }

      this.currentMethodCall.setCaller(caller);
   }


   private void startCalleeTag(final Attributes attrs)
   {
      final String calleeId = attrs.getValue(ID_ATTRIBUTE);
      TGObject callee = this.factory.getTGObject(calleeId);
      if (callee == null)
      {
         callee = this.factory.createTGObject(calleeId);
      }

      if (callee.getType() == null)
      {
         // set type
         final String typeName = attrs.getValue(TYPE_NAME_ATTRIBUTE);
         final TGType type = this.factory.getTGType(typeName);
         callee.setType(type);
      }

      this.currentMethodCall.setCallee(callee);
   }


   private void startArgumentTag(final Attributes attrs)
   {
      TGArgument tgArgument;

      // get type
      final String typeName = attrs.getValue(TYPE_NAME_ATTRIBUTE);
      TGType type = this.factory.getTGType(typeName);
      if (type == null)
      {
         type = this.factory.createTGType(typeName);
      }

      if (attrs.getValue(ID_ATTRIBUTE) != null)
      {
         final TGObjectArgument tgObjectArgument = new TGObjectArgument();

         final String objectId = attrs.getValue(ID_ATTRIBUTE);
         TGObject object = this.factory.getTGObject(objectId);
         if (object == null)
         {
            object = this.factory.createTGObject(objectId);
            object.setType(type);
         }
         tgObjectArgument.setObject(object);

         tgArgument = tgObjectArgument;
      }
      else
      {
         final TGPrimitiveArgument tgPrimitiveObject = new TGPrimitiveArgument();
         final String value = attrs.getValue(VALUE_ATTRIBUTE);
         if (value != null)
         {
            tgPrimitiveObject.setValue(value);
         }
         tgArgument = tgPrimitiveObject;
      }

      tgArgument.setType(type);
      this.currentMethodCall.addToArguments(tgArgument);
   }

}
