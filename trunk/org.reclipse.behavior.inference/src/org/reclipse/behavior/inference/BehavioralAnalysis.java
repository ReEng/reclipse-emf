package org.reclipse.behavior.inference;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.reclipse.behavior.inference.automaton.AbstractState;
import org.reclipse.behavior.inference.automaton.DFA;
import org.reclipse.behavior.inference.automaton.DFAState;
import org.reclipse.behavior.inference.automaton.Token;
import org.reclipse.behavior.inference.input.BehavioralPatternsCatalogReader;
import org.reclipse.behavior.inference.input.StructuralAnnotationsReader;
import org.reclipse.behavior.inference.output.BehavioralAnalysisResultWriter;
import org.reclipse.tracer.model.tracegraph.TGMethodCall;
import org.reclipse.tracer.model.tracegraph.TGTrace;
import org.reclipse.tracer.model.tracegraph.TGTracePath;


/**
 * This class executes the dynamic analysis. It loads a Behavioral Pattern Catalog and registers its
 * triggers. Furthermore, it loads a list of structural annotations. It is informed when a method
 * from the program under analysis is executed and delegates this event to all triggers and all
 * running automatons.
 * 
 * <h2>Associations</h2>
 * 
 * <pre>
 *                     0..1   catalog    0..1
 * BehavioralAnalysis ------------------------ BehavioralPatternsCatalog
 *                     inference      catalog
 *                    
 *                             activeAutomatons    0..n
 * BehavioralAnalysis ----------------------------------> DFA
 *                                    runningAutomatons
 *                    
 *                    --------        annotations    0..n
 * BehavioralAnalysis | type |---------------------------> Annotation
 *                    --------                annotations
 *                  
 *                     0..1   triggers    0..n
 * BehavioralAnalysis ------------------------- Trigger
 *                     analysis       triggers
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4624 $ $Date: 2011-01-18 10:58:31 +0100 (Di, 18 Jan 2011) $
 */
public class BehavioralAnalysis implements Runnable
{

   public static final int UNDEFINED = 0;

   public static final int INITIALIZED = 1;

   public static final int RUNNING = 2;

   public static final int TERMINATION_REQUESTED = 3;

   public static final int TERMINATING = 4;

   public static final int FINISHED = 5;


   private int state = UNDEFINED;


   private synchronized void setState(final int state)
   {
      this.state = state;
   }


   public synchronized int getState()
   {
      return this.state;
   }


   private String outputFileName;


   public void setOutputFileName(final String outputFileName)
   {
      this.outputFileName = outputFileName;
   }


   private boolean logTraces = true;


   public void setLogTraces(final boolean logTraces)
   {
      this.logTraces = logTraces;
   }


   /**
    * <pre>
    *                     0..1   catalog    0..1 
    * BehavioralAnalysis ------------------------ BehavioralPatternsCatalog
    *                     inference      catalog 
    * </pre>
    */
   private BehavioralPatternsCatalog catalog;


   public BehavioralPatternsCatalog getCatalog()
   {
      return this.catalog;
   }


   public boolean setCatalog(final BehavioralPatternsCatalog value)
   {
      boolean changed = false;
      if (this.catalog != value)
      {
         final BehavioralPatternsCatalog oldValue = this.catalog;
         if (this.catalog != null)
         {
            this.catalog = null;
            oldValue.setAnalysis(null);
         }
         this.catalog = value;
         if (value != null)
         {
            value.setAnalysis(this);
         }
         changed = true;
      }
      return changed;
   }


   /**
    * <pre>
    *                             activeAutomatons    0..n 
    * BehavioralAnalysis ----------------------------------> DFA
    *                                    runningAutomatons 
    * </pre>
    */
   private HashSet<DFA> activeAutomatons;


   public boolean addToActiveAutomatons(final DFA value)
   {
      boolean changed = false;
      if (value != null)
      {
         if (this.activeAutomatons == null)
         {
            this.activeAutomatons = new HashSet<DFA>();
         }
         changed = this.activeAutomatons.add(value);
      }
      return changed;
   }


   public boolean hasInActiveAutomatons(final DFA value)
   {
      return ((this.activeAutomatons != null) && (value != null) && this.activeAutomatons.contains(value));
   }


   public Iterator<DFA> iteratorOfActiveAutomatons()
   {
      return ((this.activeAutomatons == null) ? Collections.<DFA> emptyList().iterator() : this.activeAutomatons
            .iterator());
   }


   public int sizeOfActiveAutomatons()
   {
      return ((this.activeAutomatons == null) ? 0 : this.activeAutomatons.size());
   }


   public boolean removeFromActiveAutomatons(final DFA value)
   {
      boolean changed = false;
      if ((this.activeAutomatons != null) && (value != null))
      {
         changed = this.activeAutomatons.remove(value);
      }
      return changed;
   }


   public void removeAllFromActiveAutomatons()
   {
      final Iterator<DFA> iter = iteratorOfActiveAutomatons();
      while (iter.hasNext())
      {
         final DFA tmpValue = iter.next();
         removeFromActiveAutomatons(tmpValue);
      }
   }


   /**
    * <pre>
    *                    --------        annotations    0..n 
    * BehavioralAnalysis | type |---------------------------> Annotation
    *                    --------                annotations 
    * </pre>
    */
   private HashMap<String, HashSet<Annotation>> annotations;


   public boolean addToAnnotations(final Annotation value)
   {
      final String key = getKeyForAnnotations(value);
      boolean changed = false;
      if (key != null)
      {
         if (this.annotations == null)
         {
            this.annotations = new HashMap<String, HashSet<Annotation>>();
         }
         if (this.annotations.get(key) == null)
         {
            this.annotations.put(key, new HashSet<Annotation>());
         }
         if (!this.annotations.get(key).contains(value))
         {
            this.annotations.get(key).add(value);
            changed = true;
         }
      }
      return changed;
   }


   private String getKeyForAnnotations(final Annotation value)
   {
      return (value == null ? null : value.getType());
   }


   public boolean hasInAnnotations(final Annotation value)
   {
      final String key = getKeyForAnnotations(value);
      return (((this.annotations != null) && (key != null) && this.annotations.get(key).contains(value)));
   }


   public Iterator<Annotation> iteratorOfAnnotations()
   {
      if (this.annotations == null)
      {
         return Collections.<Annotation> emptyList().iterator();
      }
      else
      {
         HashSet<Annotation> allAnnotations = new HashSet<Annotation>();
         for (HashSet<Annotation> annotationSet : this.annotations.values())
         {
            allAnnotations.addAll(annotationSet);
         }
         return allAnnotations.iterator();
      }
   }


   public Iterator<Annotation> iteratorOfAnnotations(final String key)
   {
      return ((this.annotations == null) ? Collections.<Annotation> emptyList().iterator() : this.annotations.get(key)
            .iterator());
   }


   public int sizeOfAnnotations()
   {
      if (this.annotations == null)
      {
         return 0;
      }
      else
      {
         int size = 0;
         for (HashSet<Annotation> annotationSet : this.annotations.values())
         {
            size += annotationSet.size();
         }
         return size;
      }
   }


   public int sizeOfAnnotations(final String key)
   {
      return ((this.annotations == null) ? 0 : this.annotations.get(key).size());
   }


   public boolean removeFromAnnotations(final Annotation value)
   {
      final String key = getKeyForAnnotations(value);
      boolean changed = false;
      if ((this.annotations != null) && (key != null) && (value != null || this.annotations.get(key).contains(value)))
      {
         changed = this.annotations.get(key).remove(value);
      }
      return changed;
   }


   public void removeAllFromAnnotations()
   {
      if (this.annotations != null && this.annotations.size() > 0)
      {
         this.annotations.clear();
      }
   }


   /**
    * <pre>
    *                     0..1   triggers   0..n 
    * BehavioralAnalysis ------------------------ Trigger
    *                     analysis      triggers 
    * </pre>
    */
   private HashSet<Trigger> triggers;


   public boolean addToTriggers(final Trigger value)
   {
      boolean changed = false;
      if (value != null)
      {
         if (this.triggers == null)
         {
            this.triggers = new HashSet<Trigger>();
         }
         changed = this.triggers.add(value);
         if (changed)
         {
            value.setAnalysis(this);
         }
      }
      return changed;
   }


   public boolean hasInTriggers(final Trigger value)
   {
      return ((this.triggers != null) && (value != null) && this.triggers.contains(value));
   }


   public Iterator<Trigger> iteratorOfTriggers()
   {
      return ((this.triggers == null) ? Collections.<Trigger> emptyList().iterator() : this.triggers.iterator());
   }


   public int sizeOfTriggers()
   {
      return ((this.triggers == null) ? 0 : this.triggers.size());
   }


   public boolean removeFromTriggers(final Trigger value)
   {
      boolean changed = false;
      if ((this.triggers != null) && (value != null))
      {
         changed = this.triggers.remove(value);
         if (changed)
         {
            value.setAnalysis(null);
         }
      }
      return changed;
   }


   public void removeAllFromTriggers()
   {
      this.triggers.clear();
//      final Iterator<Trigger> iter = iteratorOfTriggers();
//      while (iter.hasNext())
//      {
//         final Trigger tmpValue = iter.next();
//         removeFromTriggers(tmpValue);
//      }
   }


   private Set<IBehaviorRecognitionProgressListener> progressListeners = new HashSet<IBehaviorRecognitionProgressListener>();


   public void addProgressListener(final IBehaviorRecognitionProgressListener listener)
   {
      this.progressListeners.add(listener);
   }


   public void removeProgressListener(final IBehaviorRecognitionProgressListener listener)
   {
      this.progressListeners.remove(listener);
   }


   private void fireAmountOfTasks(final int amount)
   {
      for (final IBehaviorRecognitionProgressListener listener : this.progressListeners)
      {
         try
         {
            listener.setAmountOfTasks(amount);
         }
         catch (final Exception exception)
         {
         }
      }
   }


   private void fireTaskDone()
   {
      for (final IBehaviorRecognitionProgressListener listener : this.progressListeners)
      {
         try
         {
            listener.taskDone();
         }
         catch (final Exception exception)
         {
         }
      }
   }


   private void fireFinished()
   {
      for (final IBehaviorRecognitionProgressListener listener : this.progressListeners)
      {
         try
         {
            listener.finished();
         }
         catch (final Exception exception)
         {
         }
      }
   }

   /**
    * Analyzed trace created through symbolic execution
    */
   private boolean symbolicExecution = false;


   private LinkedList<TGMethodCall> queue = new LinkedList<TGMethodCall>();


   public LinkedList<TGMethodCall> getQueue()
   {
      return queue;
   }


   public void enqueue(final TGMethodCall methodCall)
   {
      synchronized (this.queue)
      {
         this.queue.add(methodCall);
         fireAmountOfTasks(this.queue.size());
      }
   }


   public void enqueueTrace(final TGTrace tgTrace)
   {
      this.symbolicExecution = tgTrace.getSymbolicExecution();
      final Iterator<TGTracePath> tracePathIter = tgTrace.iteratorOfTracePaths();
      while (tracePathIter.hasNext())
      {
         final TGTracePath tracePath = tracePathIter.next();
         this.tracePaths.add(tracePath);
      }
   }


   private TGMethodCall dequeue()
   {
      synchronized (this.queue)
      {
         return (TGMethodCall) this.queue.removeFirst();
      }
   }


   private boolean queueIsEmpty()
   {
      synchronized (this.queue)
      {
         return this.queue.isEmpty();
      }
   }


   private void clearQueue()
   {
      synchronized (this.queue)
      {
         this.queue.clear();
      }
   }

   private Queue<TGTracePath> tracePaths = new ConcurrentLinkedQueue<TGTracePath>();


   /**
    * Fill the queue with methodcalls from the tracepath
    * 
    * @param tracePath
    */
   private void enqueueTracePath(final TGTracePath tracePath)
   {
      final Iterator<TGMethodCall> methodsIter = tracePath.iteratorOfMethodCalls();
      while (methodsIter.hasNext())
      {
         final TGMethodCall methodCall = methodsIter.next();
         this.enqueue(methodCall);
      }
   }


   /**
    * Initializes the behavior recognition with a set of structural annotations from the static
    * analysis and with behavioral patterns (DFA's). Wait to start the detection until state is
    * INITIALIZED.
    * 
    * @param annotations a set of structural annotations
    * @param catalog a catalog of behavioral patterns
    * @return true, if the behavior recognition was initialized properly.
    */
   public boolean initialize(final Set<StructuralAnnotation> structuralAnnotations,
         final BehavioralPatternsCatalog catalog)
   {
      boolean initialized = false;

      try
      {
         for (final StructuralAnnotation structuralAnnotation : structuralAnnotations)
         {
            final Annotation annotation = new Annotation();
            annotation.setStructuralAnnotation(structuralAnnotation);
            annotation.setType(structuralAnnotation.getType());
            addToAnnotations(annotation);
         }

         setCatalog(catalog);

         final Iterator<BehavioralPatternEntry> iter = catalog.iteratorOfEntries();
         while (iter.hasNext())
         {
            final BehavioralPatternEntry pattern = iter.next();

            final Iterator<Trigger> triggersIter = pattern.iteratorOfTriggers();
            while (triggersIter.hasNext())
            {
               addToTriggers(triggersIter.next());
            }
         }

         setState(INITIALIZED);
         initialized = true;
      }
      catch (final Exception exception)
      {
         setState(UNDEFINED);
         BehavioralAnalysisPlugin.logError("Exception during initializing behavior detection.", exception);
      }

      return initialized;
   }


   /**
    * Initializes the dynamic inference with annotations and behavioral patterns read from the given
    * files. Wait to start the inference until state is INITIALIZED.
    * 
    * @see #initialize(Set, BehavioralPatternsCatalog)
    * @param annotationsFileName the file that contains the annotations
    * @param catalogFileName the file that contains the behavioral patterns
    * @param outputFileName the file to write the results into
    * @return true, if the dynamic inference was initialized properly
    */
   public boolean initialize(final String annotationsFileName, final String catalogFileName, final String outputFileName)
   {
      if (annotationsFileName == null || catalogFileName == null || outputFileName == null)
      {
         return false;
      }

      boolean initialized = false;

      try
      {
         if (BehavioralAnalysisPlugin.LOG_INFO)
         {
            BehavioralAnalysisPlugin.logInfo("Loading structural annotations from file '" + annotationsFileName + "'");
         }

         final Set<StructuralAnnotation> annotations = StructuralAnnotationsReader.load(annotationsFileName);

         if (BehavioralAnalysisPlugin.LOG_INFO)
         {
            BehavioralAnalysisPlugin.logInfo(annotations.size() + " structural annotations loaded.");
            BehavioralAnalysisPlugin.logInfo("Loading behavioral patterns catalog from file '" + catalogFileName + "'");
         }

         final BehavioralPatternsCatalog catalog = BehavioralPatternsCatalogReader.load(catalogFileName);

         setOutputFileName(outputFileName);

         initialized = initialize(annotations, catalog);
         clearQueue();
      }
      catch (final FileNotFoundException exception)
      {
         BehavioralAnalysisPlugin.logError("Exception during initializing behavior detection.", exception);
      }

      return initialized;
   }


   /**
    * Processes all method calls given in the queue until the thread is stopped by calling stop().
    * 
    * @see java.lang.Runnable#run()
    */
   public void run()
   {
      if (getState() != INITIALIZED)
      {
         throw new IllegalStateException("Behavior detection has not been initialized yet or is still running.");
      }

      if (BehavioralAnalysisPlugin.LOG_INFO)
      {
         BehavioralAnalysisPlugin.logInfo("Behavior detection started.");
      }

      setState(RUNNING);

      while (getState() != TERMINATING)
      {
         if (!queueIsEmpty())
         {
            methodCalled(dequeue());
            fireTaskDone();
         }
         else if (getState() == TERMINATION_REQUESTED)
         {
            setState(TERMINATING);
         }
         else
         {
            try
            {
               Thread.sleep(200);
            }
            catch (final InterruptedException e)
            {
            }
         }
      }

      evaluateResults();
      writeResultsToFile();

      if (BehavioralAnalysisPlugin.LOG_INFO)
      {
         BehavioralAnalysisPlugin.logInfo("Behavior detection finished.");
      }

      setState(FINISHED);
      fireFinished();
   }

   private Map<DFAState, List<Token>> results = new HashMap<DFAState, List<Token>>();


   /**
    * Processes all method calls given in the queue.
    */
   public void runRecognition()
   {
      if (getState() != INITIALIZED)
      {
         throw new IllegalStateException("Behavior detection has not been initialized yet or is still running.");
      }

      if (BehavioralAnalysisPlugin.LOG_INFO)
      {
         BehavioralAnalysisPlugin.logInfo("Behavior detection started.");
      }

      setState(RUNNING);
      fireAmountOfTasks(this.queue.size());

      while (!this.tracePaths.isEmpty())
      {
         BehavioralAnalysisPlugin.logInfo("Processing tracepath " + this.tracePaths.peek().getName());
         // Enqueue next Tracepath
         final TGTracePath tracePath = this.tracePaths.poll();
         this.enqueueTracePath(tracePath);

         // process methods
         while (!queueIsEmpty())
         {
            methodCalled(dequeue());
            fireTaskDone();
         }

         // reset automaton, save tokens
         Iterator<DFA> dfaIt = this.iteratorOfActiveAutomatons();
         while (dfaIt.hasNext())
         {
            DFA dfa = dfaIt.next();
            Iterator<AbstractState> stateIt = dfa.iteratorOfStates();
            while (stateIt.hasNext())
            {
               DFAState state = (DFAState) stateIt.next();

               if (!this.results.containsKey(state))
                  this.results.put(state, new LinkedList<Token>());

               // Save tokens
               Iterator<Token> tokenIt = state.iteratorOfTokens();
               while (tokenIt.hasNext())
               {
                  Token token = tokenIt.next();
                  this.results.get(state).add(token);
                  state.removeFromTokens(token);
               }
            }
         }
         this.activeAutomatons = null;
      }

      // write back tokens
      for (Entry<DFAState, List<Token>> entry : this.results.entrySet())
         for (Token token : entry.getValue())
            entry.getKey().addToTokens(token);


      this.evaluateResults();
      writeResultsToFile();

      if (BehavioralAnalysisPlugin.LOG_INFO)
      {
         BehavioralAnalysisPlugin.logInfo("Behavior detection finished.");
      }

      setState(FINISHED);
      fireFinished();
   }


   /**
    * Call this method to terminate the dynamic inference thread. Wait for dynamic inference until
    * state is FINISHED.
    */
   public synchronized void terminate()
   {
      setState(TERMINATION_REQUESTED);
   }


   public Set<Annotation> getResult()
   {
      final Set<Annotation> annotationsSet = new HashSet<Annotation>();

      final Iterator<Annotation> annotationsIter = iteratorOfAnnotations();
      while (annotationsIter.hasNext())
      {
         final Annotation annotation = annotationsIter.next();
         final BehavioralAnnotation behavioralAnnotation = annotation.getBehavioralAnnotation();
         if (behavioralAnnotation != null)
         {
            annotationsSet.add(annotation);
         }
      }

      return annotationsSet;
   }


   /**
    * Starts the processing of the given method call. First, the method call is delegated to all
    * triggers to create new state tokens and add DFA's to the running DFA's. Then, the method call
    * is delegated to all running DFA's.
    * 
    * @param methodCall
    */
   private void methodCalled(final TGMethodCall methodCall)
   {
      // first delegate call to all triggers
      // to register new automatons and tokens
      Iterator<Trigger> triggerIter = iteratorOfTriggers();
      while (triggerIter.hasNext())
      {
         final Trigger trigger = triggerIter.next();
         trigger.methodCalled(methodCall);
      }

      // now delegate call to all active automatons
      Iterator<DFA> dfaIter = iteratorOfActiveAutomatons();
      while (dfaIter.hasNext())
      {
         final DFA dfa = dfaIter.next();
         dfa.methodCalled(methodCall);
      }
   }


   private void evaluateResults()
   {
      final Iterator<Annotation> annotationsIter = iteratorOfAnnotations();
      while (annotationsIter.hasNext())
      {
         final Annotation annotation = annotationsIter.next();
         final BehavioralAnnotation behavioralAnnotation = annotation.getBehavioralAnnotation();
         if (behavioralAnnotation != null)
         {
            behavioralAnnotation.setSymbolicExecution(this.symbolicExecution);
            behavioralAnnotation.evaluate();
         }
      }
   }


   private void writeResultsToFile()
   {
      final File file = new File(this.outputFileName);

      if (BehavioralAnalysisPlugin.LOG_INFO)
      {
         BehavioralAnalysisPlugin.logInfo("Writing results to file '" + file.getAbsolutePath() + "'.");
      }

      final BehavioralAnalysisResultWriter writer = new BehavioralAnalysisResultWriter(this, this.logTraces);
      writer.save(file);
   }


   public void removeYou()
   {
      this.progressListeners.clear();

      removeAllFromAnnotations();
      removeAllFromActiveAutomatons();
      removeAllFromTriggers();

      final BehavioralPatternsCatalog tmpCatalog = getCatalog();
      if (tmpCatalog != null)
      {
         tmpCatalog.removeYou();
      }
   }

}
