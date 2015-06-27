/**
 * 
 */
package org.reclipse.interpreter.adapter.extension;

import org.reclipse.interpreter.adapter.extension.m3.ExtendedM3AdapterFactory;
import org.reclipse.interpreter.adapter.extension.storypattern.ExtendedSDSPAdapterFactory;
import org.storydriven.modeling.interpreter.adapter.SDMainAdapterFactory;
import org.storydriven.modeling.interpreter.adapter.activity.SDActivityAdapterFactory;
import org.storydriven.modeling.interpreter.adapter.expressions.SDExpressionsAdapterFactory;

/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class ExtendedSDMainAdapterFactory extends SDMainAdapterFactory {

	public ExtendedSDMainAdapterFactory() {
		this.storyPatternAdapterFactory = new ExtendedSDSPAdapterFactory(this);
		this.activityAdapterFactory = new SDActivityAdapterFactory(this);
		this.expressionsAdapterFactory = new SDExpressionsAdapterFactory(this);
		this.m3AdapterFactory = new ExtendedM3AdapterFactory(this);
	}

}
