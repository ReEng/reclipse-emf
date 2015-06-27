package org.reclipse.metamodel;


import java.util.Collection;

import org.eclipse.emf.ecore.EClass;


/**
 * This interface will be used during a pattern detection to select the potentially most efficient
 * trigger element.
 * 
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author harka
 */
public interface ITriggerChooser
{
   /**
    * This method is used during the pattern detection to select a trigger element for axiom nodes.
    * It should return the element with the potentially fewest occurrences in a source.
    * 
    * @param possibilities The collection of types from which the matching of a pattern could start.
    * @return Returns the most efficient type from the collection.
    */
   EClass getTrigger(Collection<EClass> possibilities);
}
