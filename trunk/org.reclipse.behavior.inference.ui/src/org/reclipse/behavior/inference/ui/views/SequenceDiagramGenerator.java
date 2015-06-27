package org.reclipse.behavior.inference.ui.views;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;
import org.reclipse.behavior.inference.Trace;
import org.reclipse.tracer.model.tracegraph.TGMethodCall;
import org.reclipse.tracer.model.tracegraph.TGObject;

import de.uni_paderborn.basicSequenceDiagram.AbstractSequenceDiagramObject;
import de.uni_paderborn.basicSequenceDiagram.BasicSequenceDiagramFactory;
import de.uni_paderborn.basicSequenceDiagram.InteractionOperand;
import de.uni_paderborn.basicSequenceDiagram.Lifeline;
import de.uni_paderborn.basicSequenceDiagram.ReceiveEvent;
import de.uni_paderborn.basicSequenceDiagram.RootFragment;
import de.uni_paderborn.basicSequenceDiagram.SendEvent;
import de.uni_paderborn.basicSequenceDiagram.SequenceDiagram;
import de.uni_paderborn.sequencediagram.Message;
import de.uni_paderborn.sequencediagram.SequenceDiagramObject;
import de.uni_paderborn.sequencediagram.SequencediagramFactory;


/**
 * @author Lothar
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4624 $ $Date: 2011-01-18 10:58:31 +0100 (Di, 18. Jan 2011) $
 */
public class SequenceDiagramGenerator
{

   private static Map<Trace, SequenceDiagram> sequenceDiagramsCache;

   private static Map<SequenceDiagram, Map<TGObject, AbstractSequenceDiagramObject>> objectCache;


   static
   {
      sequenceDiagramsCache = new WeakHashMap<Trace, SequenceDiagram>();
      objectCache = new WeakHashMap<SequenceDiagram, Map<TGObject, AbstractSequenceDiagramObject>>();
   }


   public static SequenceDiagram getSequenceDiagram(Trace trace)
   {
      SequenceDiagram sequenceDiagram = sequenceDiagramsCache.get(trace);

      if (sequenceDiagram == null)
      {
         sequenceDiagram = createSequenceDiagram(trace);
         sequenceDiagramsCache.put(trace, sequenceDiagram);
      }

      return sequenceDiagram;
   }


   public static void dispose()
   {
      sequenceDiagramsCache.clear();
      objectCache.clear();
   }


   private static SequenceDiagram createSequenceDiagram(Trace trace)
   {
      SequenceDiagram sequenceDiagram = BasicSequenceDiagramFactory.eINSTANCE.createSequenceDiagram();
      RootFragment rootFragment = BasicSequenceDiagramFactory.eINSTANCE.createRootFragment();
      rootFragment.setDiagram(sequenceDiagram);
      InteractionOperand rootOperand = BasicSequenceDiagramFactory.eINSTANCE.createInteractionOperand();
      rootOperand.setParentFragment(rootFragment);
      rootFragment.getOperands().add(rootOperand);
      sequenceDiagram.setRootFragment(rootFragment);
      sequenceDiagram.setName("Method call sequence " + trace.getId());

      Iterator<TGMethodCall> matchedCallsIter = trace.iteratorOfMatchedCalls();
      while (matchedCallsIter.hasNext())
      {
         TGMethodCall methodCall = (TGMethodCall) matchedCallsIter.next();
         createMessage(sequenceDiagram, rootOperand, methodCall);
      }

      return sequenceDiagram;
   }


   private static Message createMessage(SequenceDiagram sequenceDiagram, InteractionOperand operand,
         TGMethodCall methodCall)
   {
      Message message = SequencediagramFactory.eINSTANCE.createMessage();
      message.setDiagram(sequenceDiagram);

      // createArguments(); TODO

      // insert into list of fragments
      SendEvent sendEvent = BasicSequenceDiagramFactory.eINSTANCE.createSendEvent();
      sequenceDiagram.getFragments().add(sendEvent);
      operand.getFragments().add(sendEvent);

      AbstractSequenceDiagramObject source = getSequenceDiagramObject(sequenceDiagram, methodCall.getCaller());
      sendEvent.setLifeline(source.getLifeline());
      ReceiveEvent receiveEvent = BasicSequenceDiagramFactory.eINSTANCE.createReceiveEvent();
      sequenceDiagram.getFragments().add(receiveEvent);
      operand.getFragments().add(receiveEvent);

      // insert into list of events
      sequenceDiagram.getEvents().add(sendEvent);
      sequenceDiagram.getEvents().add(receiveEvent);

      message.setSendEvent(sendEvent);
      message.setReceiveEvent(receiveEvent);
      // message.setMethodReference(psObject);
      message.setName(methodCall.getSignature());
      AbstractSequenceDiagramObject target = getSequenceDiagramObject(sequenceDiagram, methodCall.getCallee());
      receiveEvent.setLifeline(target.getLifeline());

      return message;
   }


   private static AbstractSequenceDiagramObject getSequenceDiagramObject(SequenceDiagram sequenceDiagram,
         TGObject tgObject)
   {
      AbstractSequenceDiagramObject sequenceDiagramObject = null;

      Map<TGObject, AbstractSequenceDiagramObject> objects = objectCache.get(sequenceDiagram);
      if (objects == null)
      {
         objects = new HashMap<TGObject, AbstractSequenceDiagramObject>();
         sequenceDiagramObject = createSequenceDiagramObject(sequenceDiagram, tgObject);
         objects.put(tgObject, sequenceDiagramObject);
         objectCache.put(sequenceDiagram, objects);
      }
      else
      {
         sequenceDiagramObject = objects.get(tgObject);
         if (sequenceDiagramObject == null)
         {
            sequenceDiagramObject = createSequenceDiagramObject(sequenceDiagram, tgObject);
            objects.put(tgObject, sequenceDiagramObject);
         }
      }

      return sequenceDiagramObject;
   }


   private static AbstractSequenceDiagramObject createSequenceDiagramObject(SequenceDiagram sequenceDiagram,
         TGObject tgObject)
   {
      SequenceDiagramObject sequenceDiagramObject = SequencediagramFactory.eINSTANCE.createSequenceDiagramObject();
      sequenceDiagramObject.setDiagram(sequenceDiagram);

      EClass eClass = EcoreFactory.eINSTANCE.createEClass();
      eClass.setName(tgObject.getType().getName());
      sequenceDiagramObject.setType(eClass);
      sequenceDiagramObject.setName(tgObject.getId());

      Lifeline lifeline = BasicSequenceDiagramFactory.eINSTANCE.createLifeline();
      sequenceDiagramObject.setLifeline(lifeline);

      return sequenceDiagramObject;
   }
}
