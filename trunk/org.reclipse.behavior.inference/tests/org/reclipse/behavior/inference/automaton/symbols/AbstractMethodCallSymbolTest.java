package org.reclipse.behavior.inference.automaton.symbols;


import junit.framework.TestCase;

import org.reclipse.behavior.inference.StructuralAnnotation;
import org.reclipse.behavior.inference.automaton.Token;
import org.reclipse.behavior.inference.automaton.symbols.AbstractMethodCallSymbol;
import org.reclipse.behavior.inference.automaton.symbols.MethodCallObject;
import org.reclipse.tracer.model.tracegraph.TGMethodCall;
import org.reclipse.tracer.model.tracegraph.TGObject;
import org.reclipse.tracer.model.tracegraph.TGType;


/**
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4281 $ $Date: 2010-03-10 11:47:02 +0100 (Mi, 10 Mrz 2010) $
 */
public class AbstractMethodCallSymbolTest extends TestCase
{

   /*
    * Test method for
    * 'org.reclipse.patterns.behavior.automaton.symbols.AbstractMethodCallSymbol.accept(Token,
    * TGMethodCall)'
    */
   public void testAccept()
   {
      // create the token
      StructuralAnnotation annotation = new StructuralAnnotation();
      annotation.addToNodes("m", "method()");
      annotation.addToNodes("B", "org.reclipse.test.SuperType");

      Token token = new Token(annotation);

      // create method call event
      TGMethodCall methodCall = new TGMethodCall();
      methodCall.setName("method");

      TGObject calleeObject = new TGObject();
      calleeObject.setId("callee");
      methodCall.setCallee(calleeObject);

      TGType superType = new TGType();
      superType.setName("org.reclipse.test.SuperType");

      TGType subType = new TGType();
      subType.setName("org.reclipse.test.SubType");
      subType.addToSuperTypes(superType);

      // test the abstract class by using a dummy implementation
      AbstractMethodCallSymbol symbol = new MethodCallSymbolImpl();
      symbol.setMethodName("m");

      MethodCallObject methodCallObject = new MethodCallObject("b", "B");
      symbol.setCallee(methodCallObject);

      // test with superType
      calleeObject.setType(superType);
      assertTrue(symbol.accept(methodCall, token));

      // test with subType
      calleeObject.setType(subType);
      assertTrue(symbol.accept(methodCall, token));

      // test with a different type
      TGType differentType = new TGType();
      differentType.setName("org.reclipse.test.DifferentType");
      calleeObject.setType(differentType);
      assertFalse(symbol.accept(methodCall, token));

      // test with different mapping
      annotation.addToNodes("m", "differentMethod");
      calleeObject.setType(superType);
      assertFalse(symbol.accept(methodCall, token));
   }


   /**
    * Just for instantiating and testing the AbstractMethodCallSymbol class.
    */
   static class MethodCallSymbolImpl extends AbstractMethodCallSymbol
   {
      @Override
      public String getSymbolText()
      {
         return null;
      }
   }

}
