package org.reclipse.behavior.specification.ui.util;


import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.impl.EReferenceImpl;
import org.reclipse.structure.specification.PSConnection;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSObject;


/**
 * This class contains a collection of strings and getters that are used to find PSObjects and links
 * that are instances of a specific type of the current AST metamodel.
 * 
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class ASTUtil
{

   public static String CLASS_TYPE = "GASTClass";

   public static String TYPE_TYPE = "GASTType";

   public static String METHOD_TYPE = "Method";

   // public static String CLASS_TO_METHOD_LINK = "methods";
   //
   // public static String METHOD_TO_OWNINGCLASS_LINK = "surroundingClass";
   //
   // public static String METHOD_TO_RETURN_TYPE_LINK = "returnTypeDeclaration";
   //
   // public static String RETURN_TYPE_DECLARATION_TYPE = "DeclarationTypeAccess";
   //
   // public static String RETURN_TYPE_TO_TYPE_LINK = "targetType";
   //
   public static String PARAM_TYPE = "FormalParameter";

   public static String METHOD_TO_PARAM_LINK = "formalParameters";

   public static String PARAM_TO_TYPE_LINK = "type";


   public static boolean objectIsClass(PSObject object)
   {
      return determineType(object, CLASS_TYPE);
   }


   public static boolean objectIsType(PSObject object)
   {
      return determineType(object, TYPE_TYPE)
            || determineType(object, CLASS_TYPE);
   }


   public static boolean objectIsMethod(PSObject object)
   {
      return determineType(object, METHOD_TYPE);
   }


   public static boolean objectIsParam(PSObject object)
   {
      return determineType(object, PARAM_TYPE);
   }


   private static boolean determineType(PSObject object, String type)
   {
      String objectName = getEClassName(object.getInstanceOf());
      return objectName.equals(type);
   }


   private static String getEClassName(EClass clazz)
   {
      if (clazz.getName() != null)
      {
         return clazz.getName();
      }
      else
      {
         URI proxyURI = ((EClassImpl) clazz).eProxyURI();
         String[] segments = proxyURI.fragment().split("/");
         return segments[segments.length - 1];
      }
   }


   private static String getEReferenceName(EReference reference)
   {
      if (reference.getName() != null)
      {
         return reference.getName();
      }
      else
      {
         URI proxyURI = ((EReferenceImpl) reference).eProxyURI();
         String[] segments = proxyURI.fragment().split("/");
         return segments[segments.length - 1];
      }
   }


   public static boolean connectionIsMethodToParamLink(PSConnection psConnection)
   {
      if (psConnection instanceof PSLink)
      {
         return determineLink((PSLink) psConnection, METHOD_TO_PARAM_LINK);
      }
      return false;
   }


   public static boolean connectionIsParamToTypeLink(PSConnection psConnection)
   {
      if (psConnection instanceof PSLink)
      {
         return determineLink((PSLink) psConnection, PARAM_TO_TYPE_LINK);
      }
      return false;
   }


   private static boolean determineLink(PSLink link, String type)
   {
      String referenceName = getEReferenceName(link.getInstanceOf());
      return referenceName.equals(type);
   }


   public static PSObject getParamType(PSObject param)
   {
      List<PSConnection> outgoingConnections = param.getOutgoing();
      for (PSConnection psConnection : outgoingConnections)
      {
         if (ASTUtil.connectionIsParamToTypeLink(psConnection))
         {
            PSNode targetNode = ((PSLink) psConnection).getTarget();
            if (ASTUtil.objectIsType((PSObject) targetNode))
            {
               return (PSObject) targetNode;
            }
         }
      }
      return null;
   }

}
