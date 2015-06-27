package org.reclipse.structure.inference;


import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.MissingFormatArgumentException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.fujaba.commons.console.IControllableReportListener;
import org.fujaba.commons.console.IProcessConsole;
import org.fujaba.commons.console.IReportListener;
import org.reclipse.structure.generator.GeneratorConstants;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.annotations.AnnotationCollector;
import org.reclipse.structure.inference.annotations.AnnotationEngine;
import org.reclipse.structure.inference.annotations.AnnotationSet;
import org.reclipse.structure.inference.annotations.AnnotationsFactory;
import org.reclipse.structure.inference.notification.InferenceProgressProvider;
import org.reclipse.structure.inference.strategy.BottomUpStrategy;
import org.reclipse.structure.inference.strategy.ContextPatternPair;
import org.reclipse.structure.specification.PSCatalog;
import org.reclipse.structure.specification.PSPatternSpecification;


public abstract class InferenceEngine implements IReportListener
{
   protected final IControllableReportListener reporter;


   protected Collection<EObject> host;

   protected PSCatalog catalog;

   protected final Map<PSPatternSpecification, AnnotationEngine> engines;


   protected InferenceStrategy strategy;

   protected IAnnotationEvaluator annotationEvaluator;

   protected boolean searchForAdditionalElements;


   protected InferenceProgressProvider progressProvider;

   protected Map<PSPatternSpecification, Collection<ASGAnnotation>> results;


   private boolean isDebug;


   public InferenceEngine(IControllableReportListener reporter, boolean isDebug)
   {
      this.reporter = reporter;
      this.isDebug = isDebug;

      progressProvider = InferenceProgressProvider.get(this);
      results = new HashMap<PSPatternSpecification, Collection<ASGAnnotation>>();
      engines = new HashMap<PSPatternSpecification, AnnotationEngine>();
   }


   protected boolean isDebug()
   {
      return isDebug;
   }


   public boolean isAborted()
   {
      return reporter.isAborted();
   }


   public boolean isPaused()
   {
      return reporter.isPaused();
   }


   public void setCatalog(PSCatalog catalog)
   {
      this.catalog = catalog;
   }


   public void setHost(Collection<EObject> host)
   {
      this.host = host;
   }


   public Collection<EObject> getHost()
   {
      return host;
   }


   public void setAnnotationEvaluator(IAnnotationEvaluator annotationEvaluator)
   {
      this.annotationEvaluator = annotationEvaluator;
   }


   public void setSearchForAdditionalElements(boolean searchForAdditionalElements)
   {
      this.searchForAdditionalElements = searchForAdditionalElements;
   }


   public InferenceProgressProvider getInferenceProgressProvider()
   {
      return progressProvider;
   }


   public IStatus start(IProgressMonitor monitor)
   {
      progressProvider.fireInit();

      strategy = new BottomUpStrategy(this, catalog);

      strategy.startInference(monitor);

      AnnotationCollector.get().clear();

      // clear caches
      Resource resource = catalog.eResource();
      resource.eNotify(new NotificationImpl(Notification.REMOVE, false, false));

      return Status.OK_STATUS;
   }


   /**
    * Calls {@link #applyPatternSearch(PSPatternSpecification, EObject)} within. Reports listeners about new annotation
    * and evaluates them.
    * 
    * @param contextPatternPair
    * @return A list of ASGAnnotations
    */
   public Collection<ASGAnnotation> annotate(ContextPatternPair pair)
   {
      Collection<ASGAnnotation> result = applyPatternSearch(pair.getPattern(), pair.getContext());

      Collection<ASGAnnotation> annotationSet = results.get(pair.getPattern());
      if (annotationSet == null)
      {
         annotationSet = new HashSet<ASGAnnotation>();
         results.put(pair.getPattern(), annotationSet);
      }

      for (ASGAnnotation annotation : result)
      {
         annotation.setPattern(pair.getPattern());
         annotation.setAnnotationRanking(annotationEvaluator.evaluate(annotation));
         if (!annotationSet.contains(annotation))
         {
            annotationSet.add(annotation);
            progressProvider.fireNewAnnotation(annotation);
         }
      }

      return result;
   }


   protected abstract Collection<ASGAnnotation> applyPatternSearch(PSPatternSpecification pattern, EObject context);


   public void setEnginePackage(EPackage enginesPack)
   {
      EFactory factory = enginesPack.getEFactoryInstance();

      for (EClassifier classifier : enginesPack.getEClassifiers())
      {
         AnnotationEngine engine = (AnnotationEngine) factory.create((EClass) classifier);
         AnnotationSet resultSet = AnnotationsFactory.eINSTANCE.createAnnotationSet();
         engine.setFoundAnnotations(resultSet);
         PSPatternSpecification pattern = getPattern(classifier);

         engines.put(pattern, (AnnotationEngine) engine);
      }
   }


   private PSPatternSpecification getPattern(EClassifier classifier)
   {
      for (PSPatternSpecification pattern : catalog.getPatternSpecifications())
      {
         if ((pattern.getName() + GeneratorConstants.CLASS_SUFFIX).equals(classifier.getName()))
         {
            return pattern;
         }
      }

      return null;
   }


   public IProcessConsole getConsole()
   {
      return reporter.getConsole();
   }


   @Override
   public IStatus error(String message, Object... args)
   {
      return reporter.error(message, args);
   }


   @Override
   public void warn(String message, Object... args)
   {
      reporter.warn(message, args);
   }


   @Override
   public void append(String message, Object... args)
   {
      reporter.append(message, args);
   }


   @Override
   public void task(String message, Object... args)
   {
      reporter.task(message, args);
   }


   @Override
   public void info(String message, Object... args)
   {
      reporter.info(message, args);
   }


   @Override
   public void debug(String message, Object... args)
   {
      try
      {
         reporter.debug(message, args);
      }
      catch (MissingFormatArgumentException e)
      {
         e.printStackTrace();
      }
   }
   
   public Map<PSPatternSpecification, Collection<ASGAnnotation>> getResults(){
	   return results; 
   }
}
