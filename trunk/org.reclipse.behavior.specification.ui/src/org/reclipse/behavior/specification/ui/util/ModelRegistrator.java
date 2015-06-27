/**
 * 
 */
package org.reclipse.behavior.specification.ui.util;

import org.fujaba.commons.utils.Class2EClassRegistry;
import org.reclipse.behavior.specification.BehavioralpatternPackage;
import org.reclipse.behavior.specification.impl.BPAnyObjectImpl;
import org.reclipse.behavior.specification.impl.BPAssignmentImpl;
import org.reclipse.behavior.specification.impl.BPEachFragmentImpl;
import org.reclipse.behavior.specification.impl.BPMessageImpl;
import org.reclipse.behavior.specification.impl.BPObjectImpl;
import org.reclipse.behavior.specification.impl.BPSetObjectImpl;
import org.reclipse.behavior.specification.impl.BehavioralPatternImpl;

import de.uni_paderborn.basicSequenceDiagram.BasicSequenceDiagramPackage;
import de.uni_paderborn.basicSequenceDiagram.impl.AlternativeFragmentImpl;
import de.uni_paderborn.basicSequenceDiagram.impl.LifelineImpl;
import de.uni_paderborn.basicSequenceDiagram.impl.LoopFragmentImpl;
import de.uni_paderborn.basicSequenceDiagram.impl.OptionalFragmentImpl;

/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 *
 */
public abstract class ModelRegistrator
{
   public static void initialize()
   {
      BehavioralpatternPackage bpPack = BehavioralpatternPackage.eINSTANCE;
      Class2EClassRegistry.registerClass(BehavioralPatternImpl.class, bpPack.getBehavioralPattern());
      Class2EClassRegistry.registerClass(BPObjectImpl.class, bpPack.getBPObject());
      Class2EClassRegistry.registerClass(BPAnyObjectImpl.class, bpPack.getBPAnyObject());
      Class2EClassRegistry.registerClass(BPSetObjectImpl.class, bpPack.getBPSetObject());
      Class2EClassRegistry.registerClass(BPMessageImpl.class, bpPack.getBPMessage());
      Class2EClassRegistry.registerClass(BPAssignmentImpl.class, bpPack.getBPAssignment());
      Class2EClassRegistry.registerClass(BPEachFragmentImpl.class, bpPack.getBPEachFragment());
      BasicSequenceDiagramPackage bsPack = BasicSequenceDiagramPackage.eINSTANCE;
      Class2EClassRegistry.registerClass(LifelineImpl.class, bsPack.getLifeline());
      Class2EClassRegistry.registerClass(AlternativeFragmentImpl.class, bsPack.getAlternativeFragment());
      Class2EClassRegistry.registerClass(LoopFragmentImpl.class, bsPack.getLoopFragment());
      Class2EClassRegistry.registerClass(OptionalFragmentImpl.class, bsPack.getOptionalFragment());
   }
}
