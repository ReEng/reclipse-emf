package org.reclipse.structure.inference.annotations;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class AnnotationsPlugin extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.reclipse.structure.inference.annotations";
	
	public static final String ANNOTATIONS_MODEL_FILE = "/design/annotations.ecore";

	// The shared instance
	private static AnnotationsPlugin plugin;
	
	/**
	 * The constructor
	 */
	public AnnotationsPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	@Override
   public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
   public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static AnnotationsPlugin getDefault() {
		return plugin;
	}

}
