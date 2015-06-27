package org.reclipse.behavior.specification.ui.editparts;


import java.util.Iterator;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.EditPolicy;
import org.reclipse.behavior.specification.BPArgument;
import org.reclipse.behavior.specification.BPMessage;
import org.reclipse.behavior.specification.BPSetObject;
import org.reclipse.behavior.specification.ui.editpolicies.DeleteMessageEditPolicy;

import de.uni_paderborn.basicSequenceDiagram.AbstractMessage;
import de.uni_paderborn.basicSequenceDiagram.AbstractSequenceDiagramObject;
import de.uni_paderborn.sequencediagram.viewer.editparts.MessageEditPart;


public class BPMessageEditPart extends MessageEditPart
{

   private String getSignature(BPMessage message)
   {
      StringBuffer signature = new StringBuffer();
      signature.append(message.getName());
      signature.append("(");
      signature.append(getArgList(message));
      signature.append(")");
      return signature.toString();
   }


   @Override
   protected String getArgList(AbstractMessage message)
   {
      BPMessage bpMessage = (BPMessage) message;
      StringBuffer argString = new StringBuffer();
      Iterator<BPArgument> arguments = bpMessage.getArguments().iterator();
      while (arguments.hasNext())
      {
         BPArgument bpArgument = (BPArgument) arguments.next();
         argString.append(bpArgument.getName());
         argString.append(":" + bpArgument.getType().getName());
         if (arguments.hasNext())
         {
            argString.append(", ");
         }
      }

      return argString.toString();
   }


   @Override
   protected IFigure createFigure()
   {
      BPMessage message = (BPMessage) getModel();

      PolylineConnection figure = new PolylineConnection();
      updateDecoration(figure);
      updateLabel(figure, getSignature(message));

      return figure;
   }


   @Override
   protected void createEditPolicies()
   {
      super.createEditPolicies();
      installEditPolicy(EditPolicy.CONNECTION_ROLE, new DeleteMessageEditPolicy());
   }


   @Override
   protected void updateMessageLabel(AbstractMessage message, PolylineConnection connection,
         AbstractSequenceDiagramObject receiver, AbstractSequenceDiagramObject sender)
   {
      BPMessage bpMessage = (BPMessage) message;
      if ((receiver.equals(sender) && sender instanceof BPSetObject))
      {
         if (bpMessage.isSelfCall())
         {
            updateLabel(connection, getSignature(bpMessage) + "\n self");
         }
         else
         {
            updateLabel(connection, getSignature(bpMessage) + "\n other");
         }
      }
      else
      {
         updateLabel(connection, getSignature(bpMessage));
      }
   }
}
