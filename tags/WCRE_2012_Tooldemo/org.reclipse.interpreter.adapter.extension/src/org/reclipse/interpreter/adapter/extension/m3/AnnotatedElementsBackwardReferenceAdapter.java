/**
 * 
 */
package org.reclipse.interpreter.adapter.extension.m3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.annotations.AnnotationCollector;
import org.reclipse.structure.inference.annotations.AnnotationsPackage;
import org.storydriven.modeling.interpreter.adapter.SDMainAdapterFactory;
import org.storydriven.modeling.interpreter.adapter.m3.SDEObjectInstanceAdapter;
import org.storydriven.modeling.interpreter.adapter.m3.SDEReferenceAdapter;
import org.storydriven.modeling.interpreter.adapter.m3.SDInstanceAdapter;
import org.storydriven.modeling.interpreter.adapter.m3.SDObjectSetInstanceAdapter;
import org.storydriven.modeling.interpreter.adapter.m3.SDPropertyAdapter;
import org.storydriven.modeling.interpreter.adapter.m3.SDPropertyValueIterator;

import de.mdelab.sdm.interpreter.common.adapters.m3.IClassifierAdapter;
import de.mdelab.sdm.interpreter.common.adapters.m3.IInstanceAdapter;
import de.mdelab.sdm.interpreter.common.adapters.m3.IPropertyAdapter;

/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class AnnotatedElementsBackwardReferenceAdapter extends SDEReferenceAdapter {

	private IClassifierAdapter classifierAdapter;
	private final static EReference dummyRef = EcoreFactory.eINSTANCE.createEReference();

	private final static String NAME = "[backwardAnnotatedElements]";

	public AnnotatedElementsBackwardReferenceAdapter(SDMainAdapterFactory mainAdapterFactory,
			IClassifierAdapter classifierAdapter) {
		super(mainAdapterFactory, dummyRef);
		if (dummyRef.getName() == null) {
			dummyRef.setName("dummyRefForBackwardDirectionOfAnnotatedElements");
			dummyRef.setEType(AnnotationsPackage.eINSTANCE.getASGAnnotation());
			dummyRef.setUpperBound(-1);
		}

		this.classifierAdapter = classifierAdapter;
	}

	/**
	 * @see org.storydriven.modeling.interpreter.adapter.m3.SDPropertyAdapter#containsValue(org.storydriven.modeling.interpreter.adapter.m3.SDEObjectInstanceAdapter,
	 *      org.storydriven.modeling.interpreter.adapter.m3.SDInstanceAdapter,
	 *      org.storydriven.modeling.interpreter.adapter.m3.SDPropertyAdapter)
	 */
	@Override
	public boolean containsValue(IInstanceAdapter keyInstanceAdapter, IInstanceAdapter instanceAdapter,
			SDInstanceAdapter valueAdapter, SDPropertyAdapter targetPropertyAdapter) {
		if (instanceAdapter instanceof SDEObjectInstanceAdapter) {
			EObject object = ((SDEObjectInstanceAdapter) instanceAdapter).getEObject();
			return singleContains(valueAdapter, object);
		} else {
			SDObjectSetInstanceAdapter adapter = (SDObjectSetInstanceAdapter) instanceAdapter;
			for (EObject object : adapter.getValue()) {
				if (!singleContains(valueAdapter, object)) {
					return false;
				}
			}
			return true;
		}
	}

	private boolean singleContains(SDInstanceAdapter valueAdapter, EObject object) {
		Collection<ASGAnnotation> annos = AnnotationCollector.get().getAnnos(object);
		return annos == null ? false : annos.contains(valueAdapter.getValue());
	}

	/**
	 * @see de.mdelab.sdm.interpreter.common.adapters.m3.IPropertyAdapter#isNavigable()
	 */
	@Override
	public boolean isNavigable() {
		return true;
	}

	/**
	 * @see de.mdelab.sdm.interpreter.common.adapters.m3.IPropertyAdapter#getClassifierAdapter()
	 */
	@Override
	public IClassifierAdapter getClassifierAdapter() {
		return classifierAdapter;
	}

	/**
	 * @see de.mdelab.sdm.interpreter.common.adapters.m3.IPropertyAdapter#getName()
	 */
	@Override
	public String getName() {
		return NAME;
	}

	/**
	 * @see org.storydriven.modeling.interpreter.adapter.m3.SDAssociationEndAdapter#createAssociation(org.storydriven.modeling.interpreter.adapter.m3.SDEObjectInstanceAdapter,
	 *      java.util.Map)
	 */
	@Override
	public void createAssociation(IInstanceAdapter keyInstanceAdapter, SDEObjectInstanceAdapter instanceAdapter,
			Map<IPropertyAdapter, IInstanceAdapter> otherAssociationEnds) {
		throw new UnsupportedOperationException("cannot create eCrossReferences Link");
	}

	/**
	 * @see org.storydriven.modeling.interpreter.adapter.m3.SDAssociationEndAdapter#destroyAssociation(org.storydriven.modeling.interpreter.adapter.m3.SDEObjectInstanceAdapter,
	 *      java.util.Map)
	 */
	@Override
	public void destroyAssociation(IInstanceAdapter keyInstanceAdapter, SDEObjectInstanceAdapter instanceAdapter,
			Map<IPropertyAdapter, IInstanceAdapter> otherAssociationEnds) {
		throw new UnsupportedOperationException("cannot destroy eCrossReferences Link");
	}

	/**
	 * @see org.storydriven.modeling.interpreter.adapter.m3.SDPropertyAdapter#createPropertyTargetIterator(org.storydriven.modeling.interpreter.adapter.m3.SDEObjectInstanceAdapter,
	 *      org.storydriven.modeling.interpreter.adapter.m3.SDPropertyAdapter, java.util.Map)
	 */
	@Override
	public SDPropertyValueIterator<SDEReferenceAdapter> createPropertyTargetIterator(
			IInstanceAdapter keyInstanceAdapter, SDEObjectInstanceAdapter instanceAdapter,
			SDPropertyAdapter targetProperty, Map<IPropertyAdapter, IInstanceAdapter> otherBoundProperties) {
		return new AnnotatedElementsBackwardReferenceIterator(getMainAdapterFactory(), keyInstanceAdapter,
				instanceAdapter, this);
	}

	/**
	 * @see org.storydriven.modeling.interpreter.adapter.m3.SDPropertyAdapter#setValue(org.storydriven.modeling.interpreter.adapter.m3.SDEObjectInstanceAdapter,
	 *      org.storydriven.modeling.interpreter.adapter.m3.SDInstanceAdapter)
	 */
	@Override
	public void setValue(IInstanceAdapter keyInstanceAdapter, SDEObjectInstanceAdapter instanceAdapter,
			SDInstanceAdapter valueAdapter) {
		throw new UnsupportedOperationException("cannot set eCrossReferences Link value");

	}

	@Override
	public int getTraversalCost(EObject instance) {
		return 1000;
	}

	@Override
	public IInstanceAdapter getPropertySetValue(IInstanceAdapter keyInstanceAdapter,
			SDEObjectInstanceAdapter instanceAdapter, SDPropertyAdapter targetProperty) {
		Collection<ASGAnnotation> resultSet = new ArrayList<ASGAnnotation>();

		EObject instance = instanceAdapter.getEObject();
		Collection<ASGAnnotation> list = AnnotationCollector.get().getAnnos(instance);

		if (list != null) {
			if (keyInstanceAdapter == null) {
				resultSet = list;
			} else {
				Object keyValue = keyInstanceAdapter.getValue();

				for (ASGAnnotation anno : list) {
					EList<EObject> values = anno.getAnnotatedElements().get(keyValue);
					if (values != null && values.contains(instance)) // not only annotated by anno, but also qualified
																		// by the keyValue
					{
						resultSet.add(anno);
					}
				}
			}
		}
		return getMainAdapterFactory().getM3AdapterFactory().getInstanceAdapter(
				resultSet,
				getMainAdapterFactory().getM3AdapterFactory().getClassifierAdapter(
						AnnotationsPackage.eINSTANCE.getASGAnnotation()));
	}

}
