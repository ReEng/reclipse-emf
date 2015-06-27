package org.reclipse.behavior.inference.automaton.symbols;


import java.util.Iterator;

import junit.framework.TestCase;

import org.reclipse.behavior.inference.StructuralAnnotation;
import org.reclipse.behavior.inference.automaton.Token;
import org.reclipse.behavior.inference.automaton.symbols.MethodCallObject;
import org.reclipse.behavior.inference.automaton.symbols.ProhibitedMethodCallSymbol;
import org.reclipse.tracer.model.tracegraph.TGMethodCall;
import org.reclipse.tracer.model.tracegraph.TGObject;
import org.reclipse.tracer.model.tracegraph.TGType;


/**
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4281 $ $Date: 2010-03-10 11:47:02 +0100 (Mi, 10 Mrz 2010) $
 */
public class ProhibitedMethodCallSymbolTest extends TestCase
{

   /*
    * Test method for
    * 'org.reclipse.patterns.behavior.automaton.symbols.ProhibitedMethodCallSymbol.accept(Token,
    * TGMethodCall)'
    */
   public void testAccept()
   {
      // create the token
      StructuralAnnotation annotation = new StructuralAnnotation();
      annotation.addToNodes("m", "method()");
      annotation.addToNodes("B", "org.reclipse.test.CalleeType");

      Token token = new Token(annotation);

      // create method call event
      TGMethodCall methodCall = new TGMethodCall();
      methodCall.setName("method");

      TGObject calleeObject = new TGObject();
      calleeObject.setId("callee");
      methodCall.setCallee(calleeObject);

      TGType calleeType = new TGType();
      calleeType.setName("org.reclipse.test.CalleeType");
      calleeObject.setType(calleeType);

      // create the symbol
      ProhibitedMethodCallSymbol symbol = new ProhibitedMethodCallSymbol();
      symbol.setMethodName("m");

      MethodCallObject methodCallObject = new MethodCallObject("b", "B");
      symbol.setCallee(methodCallObject);

      // test with 'b' bound
      token.addToBindings("b", calleeObject);
      assertTrue(symbol.accept(methodCall, token));
      assertEquals(calleeObject, token.getFromBindings("b"));
      Iterator iter = token.iteratorOfPossibleBindings("b");
      assertFalse(iter.hasNext());

      // test with 'b' unbound
      token.removeFromBindings("b");
      assertFalse(symbol.accept(methodCall, token));
      iter = token.iteratorOfPossibleBindings("b");
      assertTrue(iter.hasNext());
      assertEquals(calleeObject, iter.next());
      assertFalse(iter.hasNext());
   }

}
