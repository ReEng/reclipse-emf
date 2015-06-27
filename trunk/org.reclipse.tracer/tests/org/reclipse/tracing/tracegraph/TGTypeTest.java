package org.reclipse.tracing.tracegraph;


import org.reclipse.tracer.model.tracegraph.TGType;

import junit.framework.TestCase;


/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4126 $
 */
public class TGTypeTest extends TestCase
{
   /*
    * Test method for 'org.reclipse.tracing.tracegraph.TGType.isCompatibleTo(String)'
    */
   public void testIsCompatibleTo()
   {
      TGType superType = new TGType();
      superType.setName("org.reclipse.SuperType");

      TGType subType = new TGType();
      subType.setName("org.reclipse.SubType");

      subType.addToSuperTypes(superType);

      assertTrue(superType.isCompatibleTo("org.reclipse.SuperType"));
      assertFalse(superType.isCompatibleTo("org.reclipse.SubType"));
      assertTrue(subType.isCompatibleTo("org.reclipse.SuperType"));
   }

}
