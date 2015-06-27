package org.reclipse.behavior.generator;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.reclipse.behavior.inference.BehavioralPatternEntry;
import org.reclipse.behavior.inference.BehavioralPatternsCatalog;
import org.reclipse.behavior.inference.Trigger;
import org.reclipse.behavior.inference.automaton.DFA;
import org.reclipse.behavior.specification.BPObject;
import org.reclipse.behavior.specification.BPSetObject;
import org.reclipse.behavior.specification.BehavioralPattern;

import de.uni_paderborn.basicSequenceDiagram.AbstractMessage;
import de.uni_paderborn.basicSequenceDiagram.AbstractSequenceDiagramObject;
import de.uni_paderborn.basicSequenceDiagram.AlternativeFragment;
import de.uni_paderborn.basicSequenceDiagram.CombinedFragment;
import de.uni_paderborn.basicSequenceDiagram.Fragment;
import de.uni_paderborn.basicSequenceDiagram.InteractionOperand;
import de.uni_paderborn.basicSequenceDiagram.OptionalFragment;
import de.uni_paderborn.basicSequenceDiagram.ReceiveEvent;


/**
 * @author lowende
 * @author $LastChangedBy: lowende $
 * @version $Revision: 3352 $ $Date: 2007-02-08 11:52:11 +0100 (Do, 08 Feb 2007) $
 */
public class CatalogGenerator
{

   public static BehavioralPatternsCatalog generateCatalog(List<BehavioralPattern> diagrams)
   {
      BehavioralPatternsCatalog catalog = new BehavioralPatternsCatalog();

      for (BehavioralPattern behavioralPattern : diagrams)
      {
         catalog.addToEntries(createBehavioralPattern(behavioralPattern));
      }

      return catalog;
   }


   /* package for testing */static BehavioralPatternEntry createBehavioralPattern(BehavioralPattern behavioralPattern)
   {
      BehavioralPatternEntry behavioralPatternEntry = new BehavioralPatternEntry();
      behavioralPatternEntry.setName(behavioralPattern.getName());
      behavioralPatternEntry.setNegative(behavioralPattern.isNegative());

      AutomatonGenerator automatonGenerator = new AutomatonGenerator(behavioralPattern);
      DFA dfa = automatonGenerator.generateAutomaton();
      behavioralPatternEntry.setAutomaton(dfa);

      addTrigger(behavioralPattern, behavioralPatternEntry);

      return behavioralPatternEntry;
   }


   private static void addTrigger(BehavioralPattern behavioralPattern, BehavioralPatternEntry behavioralPatternEntry)
   {
      List<AbstractMessage> messages = collectFirstMessages(behavioralPattern.getRootFragment(), null);
      Iterator<AbstractMessage> iter = messages.iterator();
      while (iter.hasNext())
      {
         AbstractMessage message = iter.next();

         Trigger trigger = new Trigger();
         trigger.setMethodName(message.getName());

         AbstractSequenceDiagramObject sender = message.getSendEvent().getLifeline().getObject();
         String name = sender.getName();
         trigger.setCallerName(name);
         String typeName = "";
         if (sender instanceof BPObject)
         {
            typeName = ((BPObject) sender).getTypeReference().getName();
         }
         else if (sender instanceof BPSetObject)
         {
            typeName = ((BPSetObject) sender).getTypeReference().getName();
         }
         trigger.setCallerTypeName(typeName);

         AbstractSequenceDiagramObject receiver = message.getReceiveEvent().getLifeline().getObject();
         name = receiver.getName();
         trigger.setCalleeName(name);

         if (receiver instanceof BPObject)
         {
            typeName = ((BPObject) receiver).getTypeReference().getName();
         }
         else if (receiver instanceof BPSetObject)
         {
            typeName = ((BPSetObject) receiver).getTypeReference().getName();
         }
         trigger.setCalleeTypeName(typeName);

         behavioralPatternEntry.addToTriggers(trigger);
      }
   }


   /* package for testing */static List<AbstractMessage> collectFirstMessages(CombinedFragment combinedFragment,
         List<AbstractMessage> messages)
   {
      if (messages == null)
      {
         messages = new LinkedList<AbstractMessage>();
      }

      if (combinedFragment instanceof OptionalFragment)
      {
         // collect first message(s) in optional fragment...
         InteractionOperand operand = combinedFragment.getOperands().get(0);
         collectFirstMessages(operand, null, messages);

         // ... and the first message(s) of the second fragment
         InteractionOperand parentOperand = combinedFragment.getParentOperand();
         collectFirstMessages(parentOperand, combinedFragment, messages);
      }
      else if (combinedFragment instanceof AlternativeFragment)
      {
         // collect the first message(s) of each operand
         List<InteractionOperand> operands = combinedFragment.getOperands();
         for (InteractionOperand operand : operands)
         {
            collectFirstMessages(operand, null, messages);
         }
      }
      else
      {
         // just collect the first message(s)
         InteractionOperand operand = combinedFragment.getOperands().get(0);
         collectFirstMessages(operand, null, messages);
      }

      return messages;
   }


   private static List<AbstractMessage> collectFirstMessages(InteractionOperand operand, Fragment lowerBound,
         List<AbstractMessage> messages)
   {
      int index = operand.getFragments().indexOf(lowerBound);
      Iterator<Fragment> fragmentsIter = null;
      if (index >= 0)
      {
         fragmentsIter = operand.getFragments().listIterator(index);
      }
      else
      {
         fragmentsIter = operand.getFragments().listIterator();
      }
      while (fragmentsIter.hasNext())
      {
         Fragment interactionFragment = (Fragment) fragmentsIter.next();
         if (interactionFragment instanceof ReceiveEvent)
         {
            messages.add(((ReceiveEvent) interactionFragment).getMessage());
            break;
         }
         else if (interactionFragment instanceof CombinedFragment)
         {
            collectFirstMessages((CombinedFragment) interactionFragment, messages);
            break;
         }
      }

      return messages;
   }

}
