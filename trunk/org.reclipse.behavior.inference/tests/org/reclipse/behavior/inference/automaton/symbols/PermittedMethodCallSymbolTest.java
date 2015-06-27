package org.reclipse.behavior.inference.automaton.symbols;


import junit.framework.TestCase;

import org.reclipse.behavior.inference.StructuralAnnotation;
import org.reclipse.behavior.inference.automaton.Token;
import org.reclipse.behavior.inference.automaton.symbols.MethodCallObject;
import org.reclipse.behavior.inference.automaton.symbols.PermittedMethodCallSymbol;
import org.reclipse.tracer.model.tracegraph.TGMethodCall;
import org.reclipse.tracer.model.tracegraph.TGObject;
import org.reclipse.tracer.model.tracegraph.TGType;


/**
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4281 $ $Date: 2010-03-10 11:47:02 +0100 (Mi, 10 Mrz 2010) $
 */
public class PermittedMethodCallSymbolTest extends TestCase
{

   /*
    * Test method for
    * 'org.reclipse.patterns.behavior.automaton.symbols.PermittedMethodCallSymbol.accept(Token,
    * TGMethodCall)'
    */
   public void testAccept()
   {
      // create the token
      StructuralAnnotation annotation = new StructuralAnnotation();
      annotation.addToNodes("m", "method()");
      annotation.addToNodes("A", "org.reclipse.test.SuperType");
      annotation.addToNodes("B", "org.reclipse.test.CalleeType");

      Token token = new Token(annotation);

      // create method call event
      TGMethodCall methodCall = new TGMethodCall();
      methodCall.setName("method");

      TGObject callerObject = new TGObject();
      callerObject.setId("caller");
      methodCall.setCaller(callerObject);

      TGType superType = new TGType();
      superType.setName("org.reclipse.test.SuperType");

      TGType subType = new TGType();
      subType.setName("org.reclipse.test.SubType");
      subType.addToSuperTypes(superType);

      TGObject calleeObject = new TGObject();
      calleeObject.setId("callee");
      methodCall.setCallee(calleeObject);

      TGType calleeType = new TGType();
      calleeType.setName("org.reclipse.test.CalleeType");
      calleeObject.setType(calleeType);

      // create second method call event
      TGMethodCall secondMethodCall = new TGMethodCall();
      secondMethodCall.setName("method");

      TGObject secondCallerObject = new TGObject();
      secondCallerObject.setId("caller2");
      secondCallerObject.setType(superType);

      TGObject secondCalleeObject = new TGObject();
      secondCalleeObject.setId("callee2");
      secondCalleeObject.setType(calleeType);

      TGType differentType = new TGType();
      differentType.setName("org.reclipse.test.DifferentType");

      // create the symbol
      PermittedMethodCallSymbol symbol = new PermittedMethodCallSymbol();
      symbol.setMethodName("m");

      MethodCallObject methodCallObject = new MethodCallObject("a", "A");
      symbol.setCaller(methodCallObject);

      methodCallObject = new MethodCallObject("b", "B");
      symbol.setCallee(methodCallObject);

      // test with super type, 'a' and 'b' unbound
      callerObject.setType(superType);
      assertTrue(symbol.accept(methodCall, token));
      assertEquals(callerObject, token.getFromBindings("a"));
      assertEquals(calleeObject, token.getFromBindings("b"));

      // test with sub type, 'a' bound, 'b' unbound
      callerObject.setType(subType);
      token.removeFromBindings("b");
      assertEquals(null, token.getFromBindings("b"));
      assertTrue(symbol.accept(methodCall, token));
      assertEquals(callerObject, token.getFromBindings("a"));
      assertEquals(calleeObject, token.getFromBindings("b"));

      // test with 'a' unbound, 'b' bound
      token.removeFromBindings("a");
      assertEquals(null, token.getFromBindings("a"));
      assertTrue(symbol.accept(methodCall, token));
      assertEquals(callerObject, token.getFromBindings("a"));
      assertEquals(calleeObject, token.getFromBindings("b"));

      // test with 'a' and 'b' bound
      assertTrue(symbol.accept(methodCall, token));
      assertEquals(callerObject, token.getFromBindings("a"));
      assertEquals(calleeObject, token.getFromBindings("b"));

      // test with different caller object, 'a' and 'b' bound
      secondMethodCall.setCaller(secondCallerObject);
      secondMethodCall.setCallee(calleeObject);
      assertFalse(symbol.accept(secondMethodCall, token));
      assertEquals(callerObject, token.getFromBindings("a"));
      assertEquals(calleeObject, token.getFromBindings("b"));

      // test with different callee object, 'a' and 'b' bound
      secondMethodCall.setCaller(callerObject);
      secondMethodCall.setCallee(secondCalleeObject);
      assertFalse(symbol.accept(secondMethodCall, token));
      assertEquals(callerObject, token.getFromBindings("a"));
      assertEquals(calleeObject, token.getFromBindings("b"));

      // test with different caller type
      secondCallerObject.setType(differentType);
      secondMethodCall.setCaller(secondCallerObject);
      secondMethodCall.setCallee(calleeObject);
      assertFalse(symbol.accept(secondMethodCall, token));
      assertEquals(callerObject, token.getFromBindings("a"));
      assertEquals(calleeObject, token.getFromBindings("b"));
   }

}
