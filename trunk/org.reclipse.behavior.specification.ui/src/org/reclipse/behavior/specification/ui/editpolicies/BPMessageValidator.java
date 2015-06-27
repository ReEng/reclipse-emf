package org.reclipse.behavior.specification.ui.editpolicies;


import org.eclipse.emf.ecore.EObject;
import org.reclipse.behavior.specification.BPMessage;
import org.reclipse.behavior.specification.BPObject;
import org.reclipse.behavior.specification.BPSetObject;

import de.uni_paderborn.basicSequenceDiagram.AbstractSequenceDiagramObject;


/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class BPMessageValidator
{

   private static BPMessageValidator instance;


   private BPMessageValidator()
   {
   }


   public static BPMessageValidator get()
   {
      if (instance == null)
      {
         instance = new BPMessageValidator();
      }

      return instance;
   }


   public boolean isValidSource(Class connectionType, EObject source)
   {
      if (BPMessage.class.isAssignableFrom(connectionType))
      {
         if (source instanceof AbstractSequenceDiagramObject)
         {
            return true;
         }
      }
      return false;
   }


   public boolean isValidTarget(Class connectionType, EObject target)
   {
      if (BPMessage.class.isAssignableFrom(connectionType))
      {
         if (target instanceof BPObject || target instanceof BPSetObject)
         {
            return true;
         }
      }
      return false;
   }


   public boolean isValidConnection(Class connectionType, EObject source,
         EObject target)
   {
      if (isValidSource(connectionType, source))
      {
         if (isValidTarget(connectionType, target))
         {
            return true;
         }
      }
      return false;
   }

}