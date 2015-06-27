package org.reclipse.structure.generator.util;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.fujaba.commons.console.IReportListener;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.storydriven.storydiagrams.activities.Activity;


/**
 * This interface defines the general operations used during a generation process.
 * 
 * @author Aljoscha Hark <harka@mail.uni-paderborn.de>
 */
public interface IGenerator extends IReportListener
{
	/**
	 * Creates a new activity for the given pattern and adds it to the given operation.
	 * 
	 * @param container The operation on which to add.
	 * @param pattern The pattern to which the activity will belong to.
	 * @return Returns an empty activity which is contained in the operation.
	 */
	Activity createActivity(EOperation container, PSPatternSpecification pattern);


	/**
	 * Getter of the annotation class for the given pattern.
	 * 
	 * @param pattern The pattern for which to get the annotation class.
	 * @return Returns the annotation class for the pattern.
	 */
	EClass getAnnotationClass(PSPatternSpecification pattern);


	/**
	 * Getter of the activity with the given name for the pattern.
	 * 
	 * @param pattern The pattern for which to get the activity.
	 * @param name The name of the activity.
	 * @return Returns the activity when existing, otherwise <code>null</code>.
	 */
	Activity getActivity(PSPatternSpecification pattern, String name);


	/**
	 * Getter of the engine class for the given pattern.
	 * 
	 * @param pattern The pattern for which to get the engine class.
	 * @return Returns the engine class for the pattern.
	 */
	EClass getEngineClass(PSPatternSpecification pattern);


	/**
	 * Getter of the trigger node for the pattern.
	 * 
	 * @param pattern The pattern for which to get the trigger node.
	 * @return Returns the trigger node.
	 */
	PSNode getTrigger(PSPatternSpecification pattern);
}
