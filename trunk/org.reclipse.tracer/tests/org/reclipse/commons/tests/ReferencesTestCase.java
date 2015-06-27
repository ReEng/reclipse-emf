/**
 * 
 */
package org.reclipse.commons.tests;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

import junit.framework.TestCase;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 1349 $
 */
public abstract class ReferencesTestCase extends TestCase
{
   private Class targetClass;

   private Class propertyClass;

   private String propertyName;

   private Object targetObject;


   protected void setTargetClass(Class targetClass)
   {
      this.targetClass = targetClass;
   }


   protected void setTargetObject(Object targetObject)
   {
      this.targetObject = targetObject;
   }


   protected void setPropertyClass(Class propertyClass)
   {
      this.propertyClass = propertyClass;
   }


   protected void setPropertyName(String propertyName)
   {
      this.propertyName = propertyName;
   }


   protected void testToOneReference(Object firstPropertyValue, Object secondPropertyValue)
   {
      assertCorrectSetup();

      assertNotNull(firstPropertyValue);
      assertNotNull(secondPropertyValue);
      assertNotSame(firstPropertyValue, secondPropertyValue);

      try
      {
         Method setMethod = this.targetClass.getDeclaredMethod(createMethodName("set"),
               new Class[] { this.propertyClass });
         assertNotNull(setMethod);

         Method getMethod = this.targetClass.getDeclaredMethod(createMethodName("get"),
               new Class[] {});
         assertNotNull(getMethod);

         // setProperty(firstPropertyValue)
         setMethod.invoke(targetObject, new Object[] { firstPropertyValue });

         // getProperty() = firstPropertyValue
         Object result = getMethod.invoke(targetObject, new Object[] {});
         assertEquals(firstPropertyValue, result);

         // setProperty(null)
         setMethod.invoke(targetObject, new Object[] { null });
         
         // getProperty() = null
         result = getMethod.invoke(targetObject, new Object[] {});
         assertNull(result);

         // setProperty(firstPropertyValue)
         setMethod.invoke(targetObject, new Object[] { firstPropertyValue });

         // setProperty(secondPropertyValue)
         setMethod.invoke(targetObject, new Object[] { secondPropertyValue });

         // getProperty() = secondPropertyValue
         result = getMethod.invoke(targetObject, new Object[] {});
         assertEquals(secondPropertyValue, result);
      }
      catch (SecurityException e)
      {
         fail("Exception: " + e.getMessage());
      }
      catch (NoSuchMethodException e)
      {
         fail("Exception: " + e.getMessage());
      }
      catch (IllegalArgumentException e)
      {
         fail("Exception: " + e.getMessage());
      }
      catch (IllegalAccessException e)
      {
         fail("Exception: " + e.getMessage());
      }
      catch (InvocationTargetException e)
      {
         fail("Exception: " + e.getMessage());
      }
   }


   protected void testToManyReference(Object firstPropertyValue, Object secondPropertyValue)
   {
      assertCorrectSetup();

      assertNotNull(firstPropertyValue);
      assertNotNull(secondPropertyValue);
      assertNotSame(firstPropertyValue, secondPropertyValue);

      try
      {
         Method addToMethod = this.targetClass.getDeclaredMethod(createMethodName("addTo"),
               new Class[] { this.propertyClass });
         assertNotNull(addToMethod);

         Method hasInMethod = this.targetClass.getDeclaredMethod(createMethodName("hasIn"),
               new Class[] { this.propertyClass });
         assertNotNull(hasInMethod);

         Method sizeOfMethod = this.targetClass.getDeclaredMethod(createMethodName("sizeOf"),
               new Class[] {});
         assertNotNull(sizeOfMethod);

         Method iteratorOfMethod = this.targetClass.getDeclaredMethod(
               createMethodName("iteratorOf"), new Class[] {});
         assertNotNull(iteratorOfMethod);

         Method removeFromMethod = this.targetClass.getDeclaredMethod(
               createMethodName("removeFrom"), new Class[] { this.propertyClass });
         assertNotNull(removeFromMethod);

         Method removeAllFromMethod = this.targetClass.getDeclaredMethod(
               createMethodName("removeAllFrom"), new Class[] {});
         assertNotNull(removeAllFromMethod);

         // sizeOfProperty() = 0
         Integer intResult = (Integer) sizeOfMethod.invoke(targetObject, new Object[] {});
         assertEquals(0, intResult.intValue());

         // iteratorOfProperty() = empty iterator
         Iterator iteratorResult = (Iterator) iteratorOfMethod
               .invoke(targetObject, new Object[] {});
         assertNotNull(iteratorResult);
         assertEquals(false, iteratorResult.hasNext());

         // hasInProperty(firstPropertyValue) = false
         hasInMethod.invoke(targetObject, new Object[] { firstPropertyValue });
         Boolean booleanResult = (Boolean) hasInMethod.invoke(targetObject,
               new Object[] { firstPropertyValue });
         assertEquals(false, booleanResult.booleanValue());

         // addToProperty(firstPropertyValue)
         addToMethod.invoke(targetObject, new Object[] { firstPropertyValue });

         // sizeOfProperty() = 1
         intResult = (Integer) sizeOfMethod.invoke(targetObject, new Object[] {});
         assertEquals(1, intResult.intValue());

         // iteratorOfProperty() = iterator with firstPropertyValue only
         iteratorResult = (Iterator) iteratorOfMethod.invoke(targetObject, new Object[] {});
         assertNotNull(iteratorResult);
         assertEquals(true, iteratorResult.hasNext());
         assertEquals(firstPropertyValue, iteratorResult.next());
         assertEquals(false, iteratorResult.hasNext());

         // hasInProperty(firstPropertyValue) = true
         hasInMethod.invoke(targetObject, new Object[] { firstPropertyValue });
         booleanResult = (Boolean) hasInMethod.invoke(targetObject,
               new Object[] { firstPropertyValue });
         assertEquals(true, booleanResult.booleanValue());

         // second addToProperty(firstPropertyValue)
         addToMethod.invoke(targetObject, new Object[] { firstPropertyValue });

         // sizeOfProperty() = 1
         intResult = (Integer) sizeOfMethod.invoke(targetObject, new Object[] {});
         assertEquals(1, intResult.intValue());

         // removeFromProperty(firstPropertyValue)
         removeFromMethod.invoke(targetObject, new Object[] { firstPropertyValue });
         booleanResult = (Boolean) hasInMethod.invoke(targetObject,
               new Object[] { firstPropertyValue });
         assertEquals(false, booleanResult.booleanValue());

         // sizeOfProperty() = 0
         intResult = (Integer) sizeOfMethod.invoke(targetObject, new Object[] {});
         assertEquals(0, intResult.intValue());

         // add two values
         // addToProperty(firstPropertyValue)
         // addToProperty(secondPropertyValue)
         addToMethod.invoke(targetObject, new Object[] { firstPropertyValue });
         addToMethod.invoke(targetObject, new Object[] { secondPropertyValue });

         // sizeOfProperty() = 2
         intResult = (Integer) sizeOfMethod.invoke(targetObject, new Object[] {});
         assertEquals(2, intResult.intValue());

         // removeAllFromProperty
         removeAllFromMethod.invoke(targetObject, new Object[] {});

         // sizeOfProperty() = 0
         intResult = (Integer) sizeOfMethod.invoke(targetObject, new Object[] {});
         assertEquals(0, intResult.intValue());
      }
      catch (SecurityException e)
      {
         fail("Exception: " + e.getMessage());
      }
      catch (NoSuchMethodException e)
      {
         fail("Exception: " + e.getMessage());
      }
      catch (IllegalArgumentException e)
      {
         fail("Exception: " + e.getMessage());
      }
      catch (IllegalAccessException e)
      {
         fail("Exception: " + e.getMessage());
      }
      catch (InvocationTargetException e)
      {
         fail("Exception: " + e.getMessage());
      }
   }


   private void assertCorrectSetup()
   {
      assertNotNull(this.targetClass);
      assertNotNull(this.targetObject);
      assertNotNull(this.propertyClass);
      assertNotNull(this.propertyName);
   }


   private String createMethodName(String methodNamePrefix)
   {
      StringBuffer methodName = new StringBuffer();

      methodName.append(methodNamePrefix);
      methodName.append(Character.toUpperCase(this.propertyName.charAt(0)));
      methodName.append(this.propertyName.substring(1));

      return methodName.toString();
   }
}

/*
 * $Log$
 * Revision 1.2  2005/10/18 01:28:05  lowende
 * ReferencesTestCase set to abstract
 *
 * Revision 1.1  2005/10/16 04:31:08  lowende
 * Class renamed. New reference test methods.
 * Revision 1.1 2005/10/14 02:31:03 lowende Generic test for access methods added.
 * 
 */
