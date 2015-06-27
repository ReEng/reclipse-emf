package org.reclipse.tracer.symbolicexecution.util;


import java.util.List;

import org.reclipse.behavior.generator.tracedefinition.BehavioralPatternVariable;
import org.reclipse.behavior.generator.tracedefinition.BehavioralPatternsCatalog;
import org.reclipse.behavior.generator.tracedefinition.TraceDefinitionGenerator;
import org.reclipse.behavior.specification.BehavioralPattern;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.tracer.model.definition.ConsiderTrace;


public class RunSymbolicExecutionForCandidateTraceDefinitionGenerator extends
      TraceDefinitionGenerator
{

   private ASGAnnotation annotation = null;


   public RunSymbolicExecutionForCandidateTraceDefinitionGenerator(
         List<BehavioralPattern> patterns, List<ASGAnnotation> annotations)
   {
      super(patterns, annotations);
   }



   @Override
   public BehavioralPatternsCatalog generateBehavioralPatternsCatalog()
   {
      BehavioralPatternsCatalog catalog = super
            .generateBehavioralPatternsCatalog();

      for (BehavioralPattern pattern : this.patterns)
      {
         if (!pattern.getPsPatternSpecification().getName()
               .equals(this.annotation.getPattern().getName()))
         {
            catalog.removeKeyFromPatterns(pattern.getPsPatternSpecification()
                  .getName());
         }
      }

      // Now only the behavioral pattern according to the selected candidate should be in the
      // catalog. This may reduce the number of methods in the trace definition and thereby
      // improve the performance of the algorithm that determines the symbolic method.

      return catalog;
   }


   public void setASGAnnotation(ASGAnnotation annotation)
   {
      this.annotation = annotation;
   }


   protected void generateTraceDefinitionFromAnnotations(
         final BehavioralPatternsCatalog catalog,
         final ConsiderTrace considerTrace)
   {
      final BehavioralPatternVariable patternVariable = catalog
            .getFromPatterns(this.annotation.getPattern().getName());
      if (patternVariable != null)
      {
         addConsiderClasses(considerTrace, this.annotation, patternVariable);
      }
   }

}
