/**
 * 
 */
package org.reclipse.interpreter.adapter.extension.storypattern;

import org.storydriven.modeling.interpreter.adapter.SDMainAdapterFactory;
import org.storydriven.modeling.interpreter.adapter.storypattern.SDStoryPatternAdapterFactory;

/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class ExtendedSDSPAdapterFactory extends SDStoryPatternAdapterFactory {

	public ExtendedSDSPAdapterFactory(SDMainAdapterFactory mainAdapterFactory) {
		super(mainAdapterFactory);

		// another one here
		internalStoryPatternAdapterFactory = new ExtendedSDInternalSPAdapterFactory(mainAdapterFactory);

	}
}
