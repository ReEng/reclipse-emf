package org.reclipse.behavior.inference.automaton.symbols;


import java.util.Iterator;

import junit.framework.TestCase;

import org.reclipse.behavior.inference.StructuralAnnotation;
import org.reclipse.behavior.inference.automaton.Token;
import org.reclipse.behavior.inference.automaton.symbols.MethodCallObject;
import org.reclipse.behavior.inference.automaton.symbols.ProhibitedCallerSymbol;
import org.reclipse.tracer.model.tracegraph.TGMethodCall;
import org.reclipse.tracer.model.tracegraph.TGObject;
import org.reclipse.tracer.model.tracegraph.TGType;


/**
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4281 $ $Date: 2010-03-10 11:47:02 +0100 (Mi, 10 Mrz 2010) $
 */
public class ProhibitedCallerSymbolTest extends TestCase
{
   private StructuralAnnotation annotation;

   private Token token;

   private TGMethodCall methodCall;

   private TGObject caller1_1;

   private TGObject caller1_2;

   private TGObject caller1_3;

   private TGObject caller2;

   private TGObject caller3;

   private TGObject callee;

   private ProhibitedCallerSymbol symbol;


   /**
    * @see junit.framework.TestCase#setUp()
    */
   @Override
   protected void setUp() throws Exception
   {
      super.setUp();

      // create the annotation and the token
      this.annotation = new StructuralAnnotation();
      this.annotation.addToNodes("m", "method()");
      this.annotation.addToNodes("A1", "org.reclipse.test.CallerType1");
      this.annotation.addToNodes("A2", "org.reclipse.test.CallerType2");
      this.annotation.addToNodes("B", "org.reclipse.test.CalleeType");
      this.token = new Token(this.annotation);

      // create method call
      this.methodCall = new TGMethodCall();
      this.methodCall.setName("method");

      // create three caller objects
      TGType callerType1 = new TGType();
      callerType1.setName("org.reclipse.test.CallerType1");

      this.caller1_1 = new TGObject();
      this.caller1_1.setId("caller1_1");
      this.caller1_1.setType(callerType1);

      TGType callerSubType1 = new TGType();
      callerSubType1.setName("org.reclipse.test.CallerSubType1");
      callerType1.addToSubTypes(callerSubType1);

      this.caller1_2 = new TGObject();
      this.caller1_2.setId("caller1_2");
      this.caller1_2.setType(callerSubType1);

      this.caller1_3 = new TGObject();
      this.caller1_3.setId("caller1_3");
      this.caller1_3.setType(callerType1);

      TGType callerType2 = new TGType();
      callerType2.setName("org.reclipse.test.CallerType2");

      this.caller2 = new TGObject();
      this.caller2.setId("caller2");
      this.caller2.setType(callerType2);

      TGType callerType3 = new TGType();
      callerType3.setName("org.reclipse.test.CallerType3");

      this.caller3 = new TGObject();
      this.caller3.setId("caller3");
      this.caller3.setType(callerType3);

      // create callee object
      TGType calleeType = new TGType();
      calleeType.setName("org.reclipse.test.CalleeType");

      this.callee = new TGObject();
      this.callee.setId("callee");
      this.callee.setType(calleeType);

      // set callee, it will be the same for all tests
      this.methodCall.setCallee(callee);

      // create the symbol
      this.symbol = new ProhibitedCallerSymbol();
      this.symbol.setMethodName("m");

      MethodCallObject firstCallObject = new MethodCallObject("a1_1", "A1");
      this.symbol.addToPermittedCallers(firstCallObject);

      MethodCallObject secondMethodCallObject = new MethodCallObject("a1_2",
            "A1");
      this.symbol.addToPermittedCallers(secondMethodCallObject);

      MethodCallObject thirdMethodCallObject = new MethodCallObject("a2", "A2");
      this.symbol.addToPermittedCallers(thirdMethodCallObject);

      MethodCallObject calleeObject = new MethodCallObject("b", "B");
      this.symbol.setCallee(calleeObject);
   }


   /**
    * @see junit.framework.TestCase#tearDown()
    */
   @Override
   protected void tearDown() throws Exception
   {
      super.tearDown();

      this.annotation = null;
      this.token = null;
      this.methodCall = null;
      this.caller1_1 = null;
      this.caller2 = null;
      this.caller3 = null;
      this.callee = null;
      this.symbol = null;
   }


   /**
    * Test method for
    * 'org.reclipse.patterns.behavior.automaton.symbols.ProhibitedCallersSymbol.accept(Token,
    * TGMethodCall)'
    * 
    * Setup: The caller's type does not fit the types of 'a1_1', 'a1_2', and 'a2', 'b' unbound,
    * caller3 calls the method
    * 
    * Result: symbol rejects, possible bindings: b bound to callee
    */
   public void testAccept1()
   {
      this.methodCall.setCaller(this.caller3);

      assertFalse(this.symbol.accept(this.methodCall, this.token));

      Iterator iter = this.token.iteratorOfPossibleBindings("b");
      assertTrue(iter.hasNext());
      assertEquals(this.callee, iter.next());
      assertFalse(iter.hasNext());
   }


   /**
    * Test method for
    * 'org.reclipse.patterns.behavior.automaton.symbols.ProhibitedCallersSymbol.accept(Token,
    * TGMethodCall)'
    * 
    * Setup: The caller's type does not fit the types of 'a1_1', 'a1_2', and 'a2', 'b' bound to
    * callee, caller3 calls the method
    * 
    * Result: symbol accepts, possible bindings: none
    */
   public void testAccept2()
   {
      this.token.addToBindings("b", this.callee);
      this.methodCall.setCaller(this.caller3);

      assertTrue(this.symbol.accept(this.methodCall, this.token));

      Iterator iter = this.token.iteratorOfPossibleBindings("b");
      assertFalse(iter.hasNext());
   }


   /**
    * Test method for
    * 'org.reclipse.patterns.behavior.automaton.symbols.ProhibitedCallersSymbol.accept(Token,
    * TGMethodCall)'
    * 
    * Setup: The caller's type fits the type of 'a2', 'a2' unbound, caller2 calls the method
    * 
    * Result: symbol rejects, possible bindings: none
    */
   public void testAccept3()
   {
      this.token.addToBindings("b", this.callee);
      this.methodCall.setCaller(this.caller2);

      assertFalse(this.symbol.accept(this.methodCall, this.token));

      Iterator iter = this.token.iteratorOfPossibleBindings("b");
      assertFalse(iter.hasNext());
   }


   /**
    * Test method for
    * 'org.reclipse.patterns.behavior.automaton.symbols.ProhibitedCallersSymbol.accept(Token,
    * TGMethodCall)'
    * 
    * Setup: The caller's type fits the type of 'a2', 'a2' bound to caller2, caller2 calls the
    * method
    * 
    * Result: symbol rejects, possible bindings: none
    */
   public void testAccept4()
   {
      this.token.addToBindings("a2", this.caller2);
      this.token.addToBindings("b", this.callee);
      this.methodCall.setCaller(this.caller2);

      assertFalse(this.symbol.accept(this.methodCall, this.token));

      Iterator iter = this.token.iteratorOfPossibleBindings("b");
      assertFalse(iter.hasNext());
   }


   /**
    * Test method for
    * 'org.reclipse.patterns.behavior.automaton.symbols.ProhibitedCallersSymbol.accept(Token,
    * TGMethodCall)'
    * 
    * Setup: The caller's type fits the type of 'a1_1' and 'a1_2', 'a1_1' bound to caller1_1, 'a1_2'
    * bound to caller1_2, b unbound, caller1_3 calls the method
    * 
    * Result: symbol rejects, possible bindings: b bound to callee
    */
   public void testAccept5()
   {
      this.token.addToBindings("a1_1", this.caller1_1);
      this.token.addToBindings("a1_2", this.caller1_2);
      this.methodCall.setCaller(this.caller1_3);

      assertFalse(this.symbol.accept(this.methodCall, this.token));

      Iterator iter = this.token.iteratorOfPossibleBindings("b");
      assertTrue(iter.hasNext());
      assertEquals(this.callee, iter.next());
      assertFalse(iter.hasNext());
   }


   /**
    * Test method for
    * 'org.reclipse.patterns.behavior.automaton.symbols.ProhibitedCallersSymbol.accept(Token,
    * TGMethodCall)'
    * 
    * Setup: The caller's type fits the type of 'a1_1' and 'a1_2', 'a1_1' bound to caller1_1, 'a1_2'
    * bound to caller1_2, b bound to callee, caller1_3 calls the method
    * 
    * Result: symbol accepts, possible bindings: none
    */
   public void testAccept6()
   {
      this.token.addToBindings("a1_1", this.caller1_1);
      this.token.addToBindings("a1_2", this.caller1_2);
      this.token.addToBindings("b", this.callee);
      this.methodCall.setCaller(this.caller1_3);

      assertTrue(this.symbol.accept(this.methodCall, this.token));

      Iterator iter = this.token.iteratorOfPossibleBindings("b");
      assertFalse(iter.hasNext());
   }


   /**
    * Test method for
    * 'org.reclipse.patterns.behavior.automaton.symbols.ProhibitedCallersSymbol.accept(Token,
    * TGMethodCall)'
    * 
    * Setup: The caller's type fits the type of 'a1_1' and 'a1_2', 'a1_1' bound to caller1_1, 'a1_2'
    * unbound, b bound to callee, caller1_2 calls the method
    * 
    * Result: symbol rejects, possible bindings: none
    */
   public void testAccept7()
   {
      this.token.addToBindings("a1_1", this.caller1_1);
      this.token.addToBindings("b", this.callee);
      this.methodCall.setCaller(this.caller1_2);

      assertFalse(this.symbol.accept(this.methodCall, this.token));

      Iterator iter = this.token.iteratorOfPossibleBindings("b");
      assertFalse(iter.hasNext());
   }


   /**
    * Test method for
    * 'org.reclipse.patterns.behavior.automaton.symbols.ProhibitedCallersSymbol.accept(Token,
    * TGMethodCall)'
    * 
    * Setup: The caller's type fits the type of 'a1_1' and 'a1_2', 'a1_1' bound to caller1_1, 'a1_2'
    * bound to caller1_2, b bound to callee, caller1_2 calls the method
    * 
    * Result: symbol rejects, possible bindings: none
    */
   public void testAccept8()
   {
      this.token.addToBindings("a1_1", this.caller1_1);
      this.token.addToBindings("a1_2", this.caller1_2);
      this.token.addToBindings("b", this.callee);
      this.methodCall.setCaller(this.caller1_2);

      assertFalse(this.symbol.accept(this.methodCall, this.token));

      Iterator iter = this.token.iteratorOfPossibleBindings("b");
      assertFalse(iter.hasNext());
   }

}
