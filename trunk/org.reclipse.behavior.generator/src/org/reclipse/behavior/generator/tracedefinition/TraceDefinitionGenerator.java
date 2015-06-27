package org.reclipse.behavior.generator.tracedefinition;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.reclipse.behavior.generator.GeneratorPlugin;
import org.reclipse.behavior.specification.BPMessage;
import org.reclipse.behavior.specification.BPObject;
import org.reclipse.behavior.specification.BPSetObject;
import org.reclipse.behavior.specification.BehavioralPattern;
import org.reclipse.metamodel.AbstractElementLabeler;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.specification.util.ModelHelper;
import org.reclipse.tracer.model.definition.ConsiderClass;
import org.reclipse.tracer.model.definition.ConsiderMethod;
import org.reclipse.tracer.model.definition.ConsiderTrace;
import org.reclipse.tracer.model.definition.CriticalTrace;
import org.reclipse.tracer.model.definition.Parameter;
import org.reclipse.tracer.model.definition.TraceDefinition;

import de.fzi.gast.core.Root;
import de.fzi.gast.functions.Constructor;
import de.fzi.gast.functions.Function;
import de.fzi.gast.types.GASTClass;
import de.fzi.gast.types.GASTType;
import de.fzi.gast.variables.FormalParameter;
import de.uni_paderborn.basicSequenceDiagram.AbstractSequenceDiagramObject;
import de.uni_paderborn.basicSequenceDiagram.LifelineFragment;
import de.uni_paderborn.basicSequenceDiagram.ReceiveEvent;


/**
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4636 $ $Date: 2011-02-14 16:43:43 +0100 (Mo, 14. Feb 2011) $
 */
public class TraceDefinitionGenerator
{

   private static final String JAVA_LANG_PACKAGE = "java.lang.";

   protected final List<BehavioralPattern> patterns;

   private final List<ASGAnnotation> annotations;


   public TraceDefinitionGenerator(List<BehavioralPattern> patterns, List<ASGAnnotation> annotations)
   {
      this.patterns = patterns;
      this.annotations = filterAnnotations(annotations);
   }


   private List<ASGAnnotation> filterAnnotations(List<ASGAnnotation> annotations)
   {
      List<ASGAnnotation> filteredAnnotations = new ArrayList<ASGAnnotation>();
      for (ASGAnnotation anno : annotations)
      {
         if (!annoIsFiltered(anno))
         {
            filteredAnnotations.add(anno);
         }
      }
      return filteredAnnotations;
   }


   private boolean annoIsFiltered(ASGAnnotation anno)
   {
      for (BehavioralPattern pattern : this.patterns)
      {
         if (pattern.getName().equals(anno.getPattern().getName()))
         {
            return false;
         }
      }
      return true;
   }


   public TraceDefinition generateTraceDefinition() throws FileNotFoundException
   {
      final TraceDefinition traceDefinition = new TraceDefinition();

      // extract variable names from behavioral pattern specifications
      final BehavioralPatternsCatalog catalog = generateBehavioralPatternsCatalog();

      // add Critical and Consider Traces to TraceDefinition
      final ConsiderTrace considerTrace = new ConsiderTrace();
      traceDefinition.setConsiderTrace(considerTrace);

      final CriticalTrace criticalTrace = new CriticalTrace();
      traceDefinition.setCriticalTrace(criticalTrace);

      // generate trace definition from annotations and extracted variable names
      generateTraceDefinitionFromAnnotations(catalog, considerTrace);

      // remove empty trace definitions
      if (considerTrace.sizeOfClasses() == 0)
      {
         traceDefinition.setConsiderTrace(null);
      }
      if (criticalTrace.sizeOfClasses() == 0)
      {
         traceDefinition.setCriticalTrace(null);
      }

      return traceDefinition;
   }


   protected void generateTraceDefinitionFromAnnotations(final BehavioralPatternsCatalog catalog,
         final ConsiderTrace considerTrace)
   {
      for (ASGAnnotation annotation : this.annotations)
      {
         final BehavioralPatternVariable patternVariable = catalog.getFromPatterns(annotation.getPattern().getName());

         if (patternVariable != null)
         {
            addConsiderClasses(considerTrace, annotation, patternVariable);
         }
      }
   }


   public BehavioralPatternsCatalog generateBehavioralPatternsCatalog()
   {
      final BehavioralPatternsCatalog catalog = new BehavioralPatternsCatalog();

      for (BehavioralPattern pattern : this.patterns)
      {
         addPatternVariableForBPDiagram(catalog, pattern);
      }

      return catalog;
   }


   private void addPatternVariableForBPDiagram(final BehavioralPatternsCatalog catalog,
         final BehavioralPattern behavioralPattern)
   {
      // iterate through all BPObjects
      for (AbstractSequenceDiagramObject object : behavioralPattern.getObjects())
      {
         if (object instanceof BPObject)
         {
            final BPObject bpObject = (BPObject) object;
            BehavioralPatternVariable patternVariable = createPatternVariable(catalog, behavioralPattern);
            addClassVariableForBPObject(patternVariable, bpObject);
         }
         else if (object instanceof BPSetObject)
         {
            final BPSetObject bpObject = (BPSetObject) object;
            BehavioralPatternVariable patternVariable = createPatternVariable(catalog, behavioralPattern);
            addClassVariableForBPSetObject(patternVariable, bpObject);
         }
      }
   }


   private BehavioralPatternVariable createPatternVariable(final BehavioralPatternsCatalog catalog,
         final BehavioralPattern behavioralPattern)
   {
      BehavioralPatternVariable patternVariable = catalog.getFromPatterns(behavioralPattern.getName());
      if (patternVariable == null)
      {
         patternVariable = new BehavioralPatternVariable();
         patternVariable.setName(behavioralPattern.getName());
         catalog.addToPatterns(patternVariable);
      }
      return patternVariable;
   }


   private void addClassVariableForBPObject(final BehavioralPatternVariable patternVariable, final BPObject bpObject)
   {
      final String typeName = bpObject.getTypeReference().getName();
      ClassVariable classVariable = createClassVariable(patternVariable, typeName);

      handleMessages(bpObject, classVariable);
   }


   private void handleMessages(final AbstractSequenceDiagramObject object, ClassVariable classVariable)
   {
      List<LifelineFragment> fragments = object.getLifeline().getFragments();
      for (LifelineFragment lifelineFragment : fragments)
      {
         if (lifelineFragment instanceof ReceiveEvent)
         {
            BPMessage message = (BPMessage) ((ReceiveEvent) lifelineFragment).getMessage();
            addMethodVariableForBPMessages(classVariable, message);
         }
      }
   }


   private ClassVariable createClassVariable(final BehavioralPatternVariable patternVariable, final String typeName)
   {
      ClassVariable classVariable = patternVariable.getFromConsideredClasses(typeName);
      if (classVariable == null)
      {
         classVariable = new ClassVariable();
         classVariable.setName(typeName);
         patternVariable.addToConsideredClasses(classVariable);
      }
      return classVariable;
   }


   private void addClassVariableForBPSetObject(final BehavioralPatternVariable patternVariable,
         final BPSetObject bpObject)
   {
      final String typeName = bpObject.getTypeReference().getName();
      ClassVariable classVariable = createClassVariable(patternVariable, typeName);

      handleMessages(bpObject, classVariable);
   }


   private void addMethodVariableForBPMessages(final ClassVariable classVariable, BPMessage message)
   {
      MethodVariable methodVariable = classVariable.getFromMethodVariables(message.getName());
      if (methodVariable == null)
      {
         // add method to considered class
         methodVariable = new MethodVariable();
         methodVariable.setName(message.getName());
         classVariable.addToMethodVariables(methodVariable);
      }
   }


   protected void addConsiderClasses(final ConsiderTrace considerTrace, final ASGAnnotation annotation,
         final BehavioralPatternVariable patternVariable)
   {
      final Iterator classVariablesIter = patternVariable.iteratorOfConsideredClasses();
      while (classVariablesIter.hasNext())
      {
         final ClassVariable classVariable = (ClassVariable) classVariablesIter.next();

         for (Entry<String, EList<EObject>> boundObjectEntry : annotation.getBoundObjects())
         {
            if (boundObjectEntry.getKey().equals(classVariable.getName()))
            {
               ConsiderClass considerClass = createConsiderClass(considerTrace, annotation, boundObjectEntry.getValue()
                     .get(0));
               if (considerClass != null)
               {
                  addConsiderMethods(considerClass, annotation, classVariable);
               }
            }
         }
      }
   }


   protected ConsiderClass createConsiderClass(final ConsiderTrace considerTrace, final ASGAnnotation annotation,
         final EObject boundObject)
   {
      ConsiderClass considerClass = null;

      if (boundObject instanceof GASTClass)
      {
         AbstractElementLabeler labeler = ModelHelper.getMetaModel(annotation.getPattern()).getLabeler();
         String className = labeler.getText(boundObject);

         considerClass = considerTrace.getFromClasses(className);
         if (considerClass == null)
         {
            considerClass = new ConsiderClass();
            considerClass.setName(className);
            considerTrace.addToClasses(considerClass);
         }
      }
      return considerClass;
   }


   private void addConsiderMethods(final ConsiderClass considerClass, final ASGAnnotation annotation,
         final ClassVariable classVariable)
   {
      final Iterator methodVariablesIter = classVariable.iteratorOfMethodVariables();
      while (methodVariablesIter.hasNext())
      {
         final MethodVariable methodVariable = (MethodVariable) methodVariablesIter.next();

         String methodName = methodVariable.getName();
         if (!methodName.contains(" = "))
         {
            for (Entry<String, EList<EObject>> boundObjectEntry : annotation.getBoundObjects())
            {
               if (boundObjectEntry.getKey().equals(methodName))
               {
                  AbstractElementLabeler labeler = ModelHelper.getMetaModel(annotation.getPattern()).getLabeler();
                  createConsideredMethod(considerClass, boundObjectEntry.getValue().get(0), labeler);
               }
            }
         }
      }
   }


   protected void createConsideredMethod(final ConsiderClass considerClass, final EObject boundObject,
         AbstractElementLabeler labeler)
   {
      if (boundObject instanceof Function)
      {
         final Function method = (Function) boundObject;

         String methodName = labeler.getText(boundObject);
         ConsiderMethod considerMethod = considerClass.getFromMethods(methodName);
         if (considerMethod == null)
         {
            considerMethod = new ConsiderMethod();
            considerMethod.setName(methodName);
            considerClass.addToMethods(considerMethod);

            considerMethod.setConstructor(method instanceof Constructor);

            addParameters(considerMethod, method);
         }
      }
   }


   private void addParameters(final ConsiderMethod considerMethod, final Function method)
   {
      for (FormalParameter param : method.getFormalParameters())
      {
         final Parameter parameter = new Parameter();
         GASTType gastClass = param.getType();
         if (gastClass.getSimpleName().equals("Throwable"))
         {
            parameter.setType(JAVA_LANG_PACKAGE + gastClass.getQualifiedName());
         }
         parameter.setType(gastClass.getSimpleName());
         if (gastClass.eContainer().eContainer() instanceof Root)
         {
            GeneratorPlugin.logWarning("TraceDefinitionGenerator.addParameters: class '" + gastClass.getQualifiedName()
                  + "' is contained in root package.");
         }
         considerMethod.addToParameters(parameter);
      }
   }


   /**
    * Returns the signature for the given method.
    */
   private String getSignature(final Function method)
   {
      final StringBuffer buffer = new StringBuffer();
      buffer.append(method.getSimpleName());
      buffer.append("(");

      final Iterator<FormalParameter> paramsIter = method.getFormalParameters().iterator();
      while (paramsIter.hasNext())
      {
         buffer.append(paramsIter.next().getSimpleName());

         if (paramsIter.hasNext())
         {
            buffer.append(", ");
         }
      }

      buffer.append(")");

      return buffer.toString();
   }


}
