package org.reclipse.tracer.extensionpoints;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3250 $ $Date: 2006-11-15 17:51:33 +0100 (Mi, 15 Nov 2006) $
 */
public interface IMethodCallListener
{

   public boolean initialize();


   public void terminate();


   public void setProperty(String key, String value);


   public void methodCalled(long methodCallId, Object caller,
         Class staticCallerType, Object callee, Class staticCalleeType,
         String methodName, String... argumentTypes);

}
