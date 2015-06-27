/**
 * 
 */
package org.reclipse.interpreter.adapter.extension.storypattern;

import org.eclipse.emf.common.notify.Adapter;
import org.storydriven.modeling.interpreter.adapter.SDMainAdapterFactory;
import org.storydriven.modeling.interpreter.adapter.storypattern.SDInternalStoryPatternAdapterFactory;

/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class ExtendedSDInternalSPAdapterFactory extends SDInternalStoryPatternAdapterFactory {

	public ExtendedSDInternalSPAdapterFactory(SDMainAdapterFactory mainAdapterFactory) {
		super(mainAdapterFactory);
	}

	@Override
	public Adapter createLinkVariableAdapter() {
		return new ExtendedLinkVariableAdapter(mainAdapterFactory);
	}

}
