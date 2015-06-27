package org.reclipse.structure.inference.notification;


import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.fujaba.commons.console.ProcessConsoleState;
import org.reclipse.metamodel.AbstractElementLabeler;
import org.reclipse.structure.inference.InferenceEngine;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.specification.util.ModelHelper;


/**
 * @version $Revision$ $Date$
 * @author Last Editor: $Author$
 * @author falke
 */
public class InferenceProgressProvider implements InferenceProgressListener
{

   private final static Map<InferenceEngine, InferenceProgressProvider> INSTANCES = new WeakHashMap<InferenceEngine, InferenceProgressProvider>();


   public static InferenceProgressProvider get(InferenceEngine engine)
   {
      if (engine == null)
      {
         throw new IllegalArgumentException("InferenceEngine must not be null.");
      }

      InferenceProgressProvider provider = INSTANCES.get(engine);

      if (provider == null)
      {
         provider = new InferenceProgressProvider(engine);
         INSTANCES.put(engine, provider);
      }

      return provider;
   }

   private InferenceEngine engine;

   private final Set<InferenceProgressListener> listeners;

   private AbstractElementLabeler labeler;

   private int progress;

   private int maximum;


   private InferenceProgressProvider(InferenceEngine engine)
   {
      this.engine = engine;

      listeners = new HashSet<InferenceProgressListener>();
      progress = 0;
      maximum = 1;

      addListener(this);
   }


   public boolean addListener(InferenceProgressListener listener)
   {
      return engine.getConsole().addListener(listener) && listeners.add(listener);
   }


   public void fireInit()
   {
      for (InferenceProgressListener listener : listeners)
      {
         listener.init();
      }
   }


   public void fireNewAnnotation(ASGAnnotation annotation)
   {
      for (InferenceProgressListener listener : listeners)
      {
         listener.newAnnotation(annotation);
      }
   }


   private void fireNewProgressValues()
   {
      for (InferenceProgressListener listener : listeners)
      {
         listener.newValues(progress, maximum);
      }
   }


   public void increment()
   {
      progress++;
      fireNewProgressValues();
   }


   public void incrementMaximum(int rest)
   {
      this.maximum = progress + rest;
      fireNewProgressValues();
   }


   public boolean removeListener(InferenceProgressListener listener)
   {
      return engine.getConsole().removeListener(listener) && listeners.remove(listener);
   }


   @Override
   public void newState(ProcessConsoleState state)
   {
      // nothing
   }


   @Override
   public void init()
   {
      progress = 0;
      maximum = 1;
   }


   @Override
   public void newValues(int current, int maximum)
   {
      // nothing
   }


   @Override
   public void newAnnotation(ASGAnnotation annotation)
   {
      // at first annotation initialize the labeler
      if (labeler == null)
      {
         labeler = ModelHelper.getMetaModel(annotation.getPattern()).getLabeler();
      }

      engine.info("Found pattern '%1s'.", annotation.getPattern().getName());
   }
}
