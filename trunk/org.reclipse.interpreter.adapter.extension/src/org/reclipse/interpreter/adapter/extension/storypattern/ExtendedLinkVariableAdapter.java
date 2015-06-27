/**
 * 
 */
package org.reclipse.interpreter.adapter.extension.storypattern;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EReference;
import org.reclipse.interpreter.adapter.extension.m3.ExtendedM3AdapterFactory;
import org.reclipse.structure.inference.annotations.AnnotationsPackage;
import org.storydriven.modeling.interpreter.adapter.SDMainAdapterFactory;
import org.storydriven.modeling.interpreter.adapter.m3.SDPropertyAdapter;
import org.storydriven.modeling.interpreter.adapter.storypattern.SDLinkVariableAdapter;
import org.storydriven.modeling.interpreter.adapter.storypattern.SDStoryPatternAdapterFactory;
import org.storydriven.modeling.interpreter.adapter.storypattern.SDStoryPatternLinkEndAdapter;

import de.mdelab.sdm.interpreter.common.adapters.m3.IClassifierAdapter;
import de.mdelab.sdm.interpreter.common.adapters.storypattern.IStoryPatternLinkEndAdapter;
import de.mdelab.sdm.interpreter.common.adapters.storypattern.IStoryPatternObjectAdapter;

/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class ExtendedLinkVariableAdapter extends SDLinkVariableAdapter {

	public ExtendedLinkVariableAdapter(SDMainAdapterFactory mainAdapterFactory) {
		super(mainAdapterFactory);
	}

	@Override
	public List<IStoryPatternLinkEndAdapter> getLinkEndAdapters() {
		if (linkEndAdapters == null) {
			createLinkEndAdapters();
		}

		return linkEndAdapters;
	}

	@Override
	protected void createLinkEndAdapters() {
		linkEndAdapters = new ArrayList<IStoryPatternLinkEndAdapter>();

		SDStoryPatternAdapterFactory storyPatternAdapterFactory = getMainAdapterFactory()
				.getSDStoryPatternAdapterFactory();
		ExtendedM3AdapterFactory sdM3AdapterFactory = (ExtendedM3AdapterFactory) getMainAdapterFactory()
				.getSDM3AdapterFactory();

		/*
		 * add source link end adapter
		 */
		IStoryPatternObjectAdapter sourceSPOAdapter = storyPatternAdapterFactory
				.getStoryPatternObjectAdapter(getElement().getSource());

		SDPropertyAdapter sourcePropertyAdapter = (SDPropertyAdapter) sdM3AdapterFactory
				.getEStructuralFeaturePropertyAdapter(getElement().getTargetEnd());

		SDStoryPatternLinkEndAdapter sourceLinkEndAdapter = storyPatternAdapterFactory.getStoryPatternLinkEndAdapter(
				getElement(), sourceSPOAdapter, sourcePropertyAdapter);

		linkEndAdapters.add(sourceLinkEndAdapter);

		/*
		 * add target link end adapter
		 */
		IStoryPatternObjectAdapter targetSPOAdapter = storyPatternAdapterFactory
				.getStoryPatternObjectAdapter(getElement().getTarget());

		SDPropertyAdapter targetPropertyAdapter;

		if (getElement().getSourceEnd() instanceof EReference) {

			/*
			 * This is a bidirectional reference, create a property adapter for the opposite direction
			 */

			targetPropertyAdapter = (SDPropertyAdapter) sdM3AdapterFactory
					.getEStructuralFeaturePropertyAdapter(getElement().getSourceEnd());
		} else {
			IClassifierAdapter targetClassifierAdapter = sdM3AdapterFactory.getClassifierAdapter(getElement()
					.getTargetEnd().getEType());
			if (getElement().getTargetEnd() == AnnotationsPackage.eINSTANCE.getASGAnnotation_AnnotatedElements()) {
				targetPropertyAdapter = sdM3AdapterFactory.getECrossReferenceAdapter(targetClassifierAdapter,
						sourcePropertyAdapter);
			} else {
				/*
				 * There is no opposite reference, create a non-navigable property adapter
				 */
				targetPropertyAdapter = sdM3AdapterFactory.getBackwardNavigablePropertyAdapter(targetClassifierAdapter,
						sourcePropertyAdapter);
			}
		}

		SDStoryPatternLinkEndAdapter targetLinkEndAdapter = storyPatternAdapterFactory.getStoryPatternLinkEndAdapter(
				getElement(), targetSPOAdapter, targetPropertyAdapter);

		linkEndAdapters.add(targetLinkEndAdapter);

		/*
		 * Set cross references between adapters
		 */
		List<SDPropertyAdapter> oppositePropertyAdapters = new ArrayList<SDPropertyAdapter>(1);
		oppositePropertyAdapters.add(targetPropertyAdapter);

		sourcePropertyAdapter.setOppositePropertyAdapters(oppositePropertyAdapters);

		oppositePropertyAdapters = new ArrayList<SDPropertyAdapter>(1);
		oppositePropertyAdapters.add(sourcePropertyAdapter);

		targetPropertyAdapter.setOppositePropertyAdapters(oppositePropertyAdapters);
	}
}
