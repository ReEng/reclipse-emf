package org.reclipse.tracing.tracegraph;


import org.reclipse.tracer.model.tracegraph.TGFactory;
import org.reclipse.tracer.model.tracegraph.TGObject;
import org.reclipse.tracer.model.tracegraph.TGThread;
import org.reclipse.tracer.model.tracegraph.TGType;

import junit.framework.TestCase;


/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4278 $ $Date: 2010-02-18 13:32:16 +0100 (Do, 18 Feb 2010) $
 */
public class TGFactoryTest extends TestCase
{

   private TGFactory factory;


   /**
    * @see junit.framework.TestCase#setUp()
    */
   @Override
   public void setUp()
   {
      this.factory = new TGFactory();
   }


   /**
    * @see junit.framework.TestCase#tearDown()
    */
   @Override
   public void tearDown()
   {
      this.factory = null;
   }


   /**
    * Test method for 'org.reclipse.tracing.tracegraph.TGFactory.getTGType(String)'
    */
   public void testGetTGType()
   {
      String typeName = "org.reclipse.Test";

      assertNull(this.factory.getTGType(typeName));

      TGType type = this.factory.createTGType(typeName);
      assertNotNull(type);
      assertEquals(typeName, type.getName());

      try
      {
         type = this.factory.createTGType(typeName);
         fail("Previous statement should have thrown IllegalArgumentException!");
      }
      catch (IllegalArgumentException e)
      {
         // Exception should be raised here. Do nothing.
      }
   }


   /**
    * Test method for 'org.reclipse.tracing.tracegraph.TGFactory.getTGObject(String)'
    */
   public void testGetTGObject()
   {
      String id = "42";

      assertNull(this.factory.getTGObject(id));

      TGObject object = this.factory.createTGObject(id);
      assertNotNull(object);
      assertEquals(id, object.getId());

      try
      {
         object = this.factory.createTGObject(id);
         fail("Previous statement should have thrown IllegalArgumentException!");
      }
      catch (IllegalArgumentException e)
      {
         // Exception should be raised here. Do nothing.
      }
   }


   /**
    * Test method for 'org.reclipse.tracing.tracegraph.TGFactory.getTGThread(String)'
    */
   public void testGetTGThread()
   {
      String threadName = "name";

      assertNull(this.factory.getTGThread(threadName));

      TGThread thread = this.factory.createTGThread(threadName);
      assertNotNull(thread);
      assertEquals(threadName, thread.getName());

      try
      {
         thread = this.factory.createTGThread(threadName);
         fail("Previous statement should have thrown IllegalArgumentException!");
      }
      catch (IllegalArgumentException e)
      {
         // Exception should be raised here. Do nothing.
      }
   }

}
