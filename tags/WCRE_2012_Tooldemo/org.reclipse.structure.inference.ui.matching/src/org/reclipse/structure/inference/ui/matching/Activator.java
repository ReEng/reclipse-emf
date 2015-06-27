package org.reclipse.structure.inference.ui.matching;

import org.eclipse.core.runtime.Status;
import org.fujaba.commons.AbstractFujabaPlugin;
import org.osgi.framework.BundleContext;
import org.reclipse.structure.inference.ui.matching.views.PatternMatchingView;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractFujabaPlugin {

	public static final String ID = "org.reclipse.structure.inference.matching.ui"; //$NON-NLS-1$

	private static Activator instance;

	/**
	 * Getter of the singleton instance.
	 * 
	 * @return Returns the singleton instance.
	 */
	public static Activator getInstance() {
		return instance;
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		instance = this;

		// cache shared images
		String key = PatternMatchingView.MATCHED_ANNOTATION_KEY;
		addImageToCache(key, key);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		instance = null;
		super.stop(context);
	}

	@Override
	protected void log(int severity, String message, Throwable throwable) {
		getLog().log(new Status(severity, ID, message, throwable));
	}
}
