/**
 * 
 */
package org.reclipse.interpreter.adapter.extension.m3;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.storydriven.modeling.interpreter.adapter.SDMainAdapterFactory;
import org.storydriven.modeling.interpreter.adapter.m3.SDM3AdapterFactory;
import org.storydriven.modeling.interpreter.adapter.m3.SDPropertyAdapter;

import de.mdelab.sdm.interpreter.common.adapters.m3.IClassifierAdapter;

/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class ExtendedM3AdapterFactory extends SDM3AdapterFactory {
	private Map<SDPropertyAdapter, AnnotatedElementsBackwardReferenceAdapter> eCrossRefAdapters;

	public ExtendedM3AdapterFactory(SDMainAdapterFactory mainAdapterFactory) {
		super(mainAdapterFactory);

		eCrossRefAdapters = new ConcurrentHashMap<SDPropertyAdapter, AnnotatedElementsBackwardReferenceAdapter>();
	}

	public AnnotatedElementsBackwardReferenceAdapter getECrossReferenceAdapter(IClassifierAdapter classifierAdapter,
			SDPropertyAdapter propertyAdapter) {
		synchronized (eCrossRefAdapters) {
			AnnotatedElementsBackwardReferenceAdapter eCrossReferenceAdapter = eCrossRefAdapters.get(propertyAdapter);

			if (eCrossReferenceAdapter == null) {
				eCrossReferenceAdapter = new AnnotatedElementsBackwardReferenceAdapter(getMainAdapterFactory(),
						classifierAdapter);

				eCrossRefAdapters.put(propertyAdapter, eCrossReferenceAdapter);
			}

			return eCrossReferenceAdapter;
		}
	}
}
