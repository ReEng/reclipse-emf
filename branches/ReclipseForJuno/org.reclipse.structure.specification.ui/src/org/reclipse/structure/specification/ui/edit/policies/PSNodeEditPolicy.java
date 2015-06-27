package org.reclipse.structure.specification.ui.edit.policies;


import java.util.Iterator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.fujaba.commons.edit.parts.AbstractDiagramEditPart;
import org.fujaba.commons.edit.parts.AbstractNodeViewEditPart;
import org.fujaba.commons.notation.Node;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.PSPath;
import org.reclipse.structure.specification.ui.edit.commands.CreatePSLinkCommand;
import org.reclipse.structure.specification.ui.edit.commands.CreatePSPathCommand;
import org.reclipse.structure.specification.util.ModelHelper;



/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSNodeEditPolicy extends GraphicalNodeEditPolicy
{

   @Override
   protected Command getConnectionCompleteCommand(
         CreateConnectionRequest request)
   {
      // validate PSLink
      if (request.getStartCommand() instanceof CreatePSLinkCommand)
      {
         CreatePSLinkCommand link = (CreatePSLinkCommand) request
               .getStartCommand();
         EObject source = link.getSource().getModel();
         EObject target = ((AbstractNodeViewEditPart) getHost()).getRealModel();

         // don't allow self-references
         if (source == target)
         {
            return null;
         }

         // check valid link between two PSObject
         if (source instanceof PSObject && target instanceof PSObject)
         {
            Iterator<EClass> i = ModelHelper.getPossibleTypes((PSObject) source).iterator();
            EClass targetType = ((PSObject)target).getInstanceOf();
            if (targetType != null)
            {
               while (i.hasNext())
               {
                  EClass possibleType = i.next();
                  if (targetType.equals(possibleType)
                        || ModelHelper.isAssignableFrom(possibleType,
                              targetType))
                  {
                     link.setTarget((Node) getHost().getModel());
                     return link;
                  }
               }
         }
            return null;
         }

         // check valid link between PSAnnotation and PSObject
         if (source instanceof PSAnnotation && target instanceof PSObject)
         {
            if(ModelHelper.isCreate((PSAnnotation) source))
            {
               link.setTarget((Node) getHost().getModel());
               return link;
            }
            
            EClass targetType = ((PSObject) target).getInstanceOf();
            Iterator<EClass> i = ModelHelper.getPossibleTypes((PSAnnotation) source).iterator();
            while(i.hasNext())
            {
               EClass possibleType = i.next();
               if(targetType.equals(possibleType)
                     || ModelHelper.isAssignableFrom(possibleType, targetType))
               {
                  link.setTarget((Node) getHost().getModel());
                  return link;
               }
            }
            return null;
         }
      }

      // validate PSPath
      if (request.getStartCommand() instanceof CreatePSPathCommand)
      {
         CreatePSPathCommand path = (CreatePSPathCommand) request
               .getStartCommand();
         EObject source = path.getSource().getModel();
         EObject target = ((AbstractNodeViewEditPart) getHost()).getRealModel();

         // don't allow self-references
         if (source == target)
         {
            return null;
         }

         // only allow paths between PSObjects
         if (source instanceof PSObject && target instanceof PSObject)
         {
            path.setTarget((Node) getHost().getModel());
            return path;
         }
         else
         {
            return null;
         }
      }

      return null;
   }


   @Override
   protected Command getConnectionCreateCommand(CreateConnectionRequest request)
   {
      Command command = null;

      if (request.getNewObject() instanceof PSLink)
      {
         Node sourceNode = (Node) getHost().getModel();
         command = new CreatePSLinkCommand(sourceNode,
               (AbstractDiagramEditPart) getHost());
      }

      if (request.getNewObject() instanceof PSPath)
      {
         Node sourceNode = (Node) getHost().getModel();
         command = new CreatePSPathCommand(sourceNode);
      }


      if (command != null)
      {
         request.setStartCommand(command);
      }
      return command;
   }


   @Override
   protected Command getReconnectSourceCommand(ReconnectRequest request)
   {
      // TODO Auto-generated getReconnectSourceCommand
      return null;
   }


   @Override
   protected Command getReconnectTargetCommand(ReconnectRequest request)
   {
      // TODO Auto-generated getReconnectTargetCommand
      return null;
   }
}
