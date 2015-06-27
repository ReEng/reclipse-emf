package org.reclipse.tracer.symbolicexecution.ui.util;


import gov.nasa.jpf.jvm.ClassInfo;
import gov.nasa.jpf.jvm.MethodInfo;

import java.util.Calendar;

import org.reclipse.tracer.ui.models.MonitoredClass;
import org.reclipse.tracer.ui.models.MonitoredMethod;


/**
 * Creates classes from the org.reclipse.tracer.ui.models package
 * 
 * @author avolk
 * @author Last editor: $Author: avolk $
 * @version $Revision: 174 $ $Date: 2010-10-05 21:50:14 +0200 (Di, 05 Okt 2010) $
 * 
 */
public class TracerUIModelCreator
{
   /**
    * Creates a MonitoredMethod from a given MethodInfo
    * 
    * @param mi
    * @return MonitoredMethod
    */
   public MonitoredMethod createMonitoredMethod(MethodInfo mi)
   {
      MonitoredMethod result = new MonitoredMethod();
      result.setSignature(TracerUIModelCreator.createSignature(mi));
      result.setMessage("[" + Calendar.getInstance().getTime() + "] Method '"
            + mi.getClassName() + "." + result.getSignature()
            + "' is monitored.");

      if (mi.isPrivate())
         result.setVisibility(MonitoredMethod.PRIVATE);
      else if (mi.isProtected())
         result.setVisibility(MonitoredMethod.PROTECTED);
      else if (mi.isPublic())
         result.setVisibility(MonitoredMethod.PUBLIC);
      else
         result.setVisibility(MonitoredMethod.PACKAGE);
      return result;
   }


   /**
    * Creates a MonitoredClass from a given ClassInfo
    * 
    * @param ci
    * @return MonitoredClass
    */
   public MonitoredClass createMonitoredClass(ClassInfo ci)
   {
      MonitoredClass result = new MonitoredClass();
      result.setName(ci.getName());
      result.setMessage("[" + Calendar.getInstance().getTime() + "] Class '"
            + result.getName() + "' loaded.");

      if (ci.isAbstract())
         result.setModifier(MonitoredClass.ABSTRACT);

      return result;
   }


   /**
    * @see org.reclipse.tracer.model.definition.ConsiderMethod#getSignature()
    */
   public static String createSignature(MethodInfo mi)
   {
      StringBuilder sb = new StringBuilder();
      sb.append(mi.getName());
      sb.append("(");
      String argumentTypes[] = mi.getArgumentTypeNames();
      for (int i = 0; i < argumentTypes.length; i++)
      {
         String argumentType = argumentTypes[i];
         String[] tempArr = argumentType.split("\\.");
         if (tempArr.length > 0)
         {
            argumentType = tempArr[tempArr.length - 1];
         }
         sb.append(argumentType);
         if (i < argumentTypes.length - 1)
            sb.append(", ");
      }
      sb.append(")");
      return sb.toString();
   }
}
