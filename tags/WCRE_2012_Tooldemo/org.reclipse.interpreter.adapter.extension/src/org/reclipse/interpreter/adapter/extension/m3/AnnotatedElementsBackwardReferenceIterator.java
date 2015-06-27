/**
 * 
 */
package org.reclipse.interpreter.adapter.extension.m3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.annotations.AnnotationCollector;
import org.storydriven.modeling.interpreter.adapter.SDMainAdapterFactory;
import org.storydriven.modeling.interpreter.adapter.m3.SDEObjectInstanceAdapter;
import org.storydriven.modeling.interpreter.adapter.m3.SDEReferenceAdapter;
import org.storydriven.modeling.interpreter.adapter.m3.SDPropertyValueIterator;

import de.mdelab.sdm.interpreter.common.adapters.m3.IInstanceAdapter;

/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class AnnotatedElementsBackwardReferenceIterator extends SDPropertyValueIterator<SDEReferenceAdapter> {
	private Iterator<ASGAnnotation> iterator;
	private IInstanceAdapter keyInstanceAdapter;

	public AnnotatedElementsBackwardReferenceIterator(SDMainAdapterFactory mainAdapterFactory,
			IInstanceAdapter keyInstanceAdapter, SDEObjectInstanceAdapter instanceAdapter,
			SDEReferenceAdapter propertyAdapter) {
		super(mainAdapterFactory, instanceAdapter, propertyAdapter);
		this.keyInstanceAdapter = keyInstanceAdapter;
	}

	@Override
	public IInstanceAdapter getNext() {
		if (iterator == null) {
			EObject value = getInstanceAdapter().getEObject();
			Collection<ASGAnnotation> annos = AnnotationCollector.get().getAnnos(value);
			if (annos != null) {
				ArrayList<ASGAnnotation> filteredAnnos = new ArrayList<ASGAnnotation>();
				Object key = keyInstanceAdapter.getValue();
				for (ASGAnnotation anno : annos) {
					if (anno.getAnnotatedElements().get(key) != null
							&& anno.getAnnotatedElements().get(key).contains(value)) {
						filteredAnnos.add(anno);
					}
				}
				iterator = filteredAnnos.iterator();
			}
		}

		if (iterator != null && iterator.hasNext()) {
			EObject object = iterator.next();
			return getMainAdapterFactory().getM3AdapterFactory().getInstanceAdapter(object,
					getMainAdapterFactory().getM3AdapterFactory().getClassifierAdapter(object.eClass()));
		}
		return null;
	}

}
