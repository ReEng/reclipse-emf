package org.reclipse.behavior.specification.ui.commands;


import java.util.List;

import org.reclipse.behavior.specification.BPSetObject;
import org.reclipse.behavior.specification.BehavioralpatternFactory;
import org.reclipse.behavior.specification.ui.editparts.BehavioralPatternEditPart;

import de.uni_paderborn.basicSequenceDiagram.CombinedFragment;
import de.uni_paderborn.basicSequenceDiagram.Fragment;
import de.uni_paderborn.basicSequenceDiagram.SendEvent;


/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class CreateBPEachFragmentCommand extends
      AbstractCreateCombinedFragmentCommand
{

   public CreateBPEachFragmentCommand(BehavioralPatternEditPart diagramEditPart)
   {
      super(diagramEditPart);
   }


   @Override
   protected CombinedFragment createCombinedFragment()
   {
      CombinedFragment fragment = BehavioralpatternFactory.eINSTANCE
            .createBPEachFragment();
      return fragment;
   }


   @Override
   public boolean canExecute()
   {
      // an each fragment can only be created if it contains only one message and if the sender or
      // the receiver of the message is a BPSetObject
      List<Fragment> children = this.getChildren();
      if (children.size() == 1)
      {
         for (Fragment child : children)
         {
            if (child instanceof SendEvent)
            {
               if (((SendEvent) child).getLifeline().getObject() instanceof BPSetObject)
               {
                  return true;
               }
               else if (((SendEvent) child).getMessage().getReceiveEvent()
                     .getLifeline().getObject() instanceof BPSetObject)
               {
                  return true;
               }
            }
         }
      }
      return false;
   }


}
