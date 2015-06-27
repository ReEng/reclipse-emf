package org.reclipse.behavior.inference.ui.wizard;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.reclipse.behavior.inference.Annotation;
import org.reclipse.behavior.inference.BehavioralAnalysis;
import org.reclipse.behavior.inference.BehavioralPatternsCatalog;
import org.reclipse.behavior.inference.IBehaviorRecognitionProgressListener;
import org.reclipse.behavior.inference.StructuralAnnotation;
import org.reclipse.tracer.model.tracegraph.TGTrace;
import org.reclipse.tracer.model.tracegraph.xml.TraceGraphReader;

/**
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4658 $ $Date: 2011-01-18 10:58:31 +0100 (Di, 18. Jan
 *          2011) $
 */
public class BehaviorRecognitionTask implements IRunnableWithProgress,
		IBehaviorRecognitionProgressListener {

	private IProgressMonitor monitor;

	private final BehavioralAnalysis behavioralAnalysis;

	private boolean initialized;

	private int amountOfTasks;

	private Set<Annotation> annotations;

	private final String traceFileName;

	public BehaviorRecognitionTask(final String annotationsFileName,
			final String traceFileName, final String catalogFileName,
			final String resultFileName, final boolean logTraces) {
		this.traceFileName = traceFileName;
		this.behavioralAnalysis = new BehavioralAnalysis();
		this.behavioralAnalysis.setLogTraces(logTraces);

		this.initialized = this.behavioralAnalysis.initialize(
				annotationsFileName, catalogFileName, resultFileName);
	}

	public BehaviorRecognitionTask(
			Set<StructuralAnnotation> structuralAnnotations,
			final String traceFileName, BehavioralPatternsCatalog catalog,
			final String resultFileName, final boolean logTraces) {
		this.traceFileName = traceFileName;
		this.behavioralAnalysis = new BehavioralAnalysis();
		this.behavioralAnalysis.setLogTraces(logTraces);
		this.behavioralAnalysis.setOutputFileName(resultFileName);
		this.initialized = this.behavioralAnalysis.initialize(
				structuralAnnotations, catalog);
	}

	/**
	 * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void run(final IProgressMonitor monitor)
			throws InvocationTargetException, InterruptedException {
		this.monitor = monitor;

		if (this.initialized) {
			this.monitor.beginTask("Behavioral Patterns Recognition", 3);
			// this.behavioralAnalysis.addProgressListener(this);

			// load trace graph
			this.monitor.setTaskName("Loading Trace...");
			final TGTrace trace = TraceGraphReader.load(this.traceFileName);

			this.monitor.worked(1);

			if (trace != null && trace.sizeOfTracePaths() > 0) {

				// enqueue trace
				this.monitor.setTaskName("Enqueuing Trace...");
				this.behavioralAnalysis.enqueueTrace(trace);
				this.monitor.worked(1);

				// run recognition
				this.monitor
						.setTaskName("Running Behavioral Patterns Recognition...");
				this.behavioralAnalysis.runRecognition();
				this.annotations = this.behavioralAnalysis.getResult();
			}

			this.behavioralAnalysis.removeYou();
			this.monitor.done();
		}
	}

	/**
	 * @see org.reclipse.patterns.behavior.inference.IBehaviorRecognitionProgressListener#setAmountOfTasks(int)
	 */
	public void setAmountOfTasks(final int amount) {
		this.amountOfTasks = amount;
	}

	/**
	 * @see org.reclipse.patterns.behavior.inference.IBehaviorRecognitionProgressListener#taskDone()
	 */
	public void taskDone() {
		this.monitor.worked(1);
	}

	/**
	 * @see org.reclipse.patterns.behavior.inference.IBehaviorRecognitionProgressListener#finished()
	 */
	public void finished() {
		this.monitor.done();
	}

	public Set<Annotation> getAnnotations() {
		return this.annotations;
	}

}
