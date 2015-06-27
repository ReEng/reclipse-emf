package org.reclipse.tracer.symb.ui.util;


import static org.junit.Assert.assertNotNull;

import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.junit.Before;
import org.junit.Test;
import org.reclipse.tracer.model.definition.ConsiderClass;
import org.reclipse.tracer.model.definition.ConsiderMethod;
import org.reclipse.tracer.model.definition.ConsiderTrace;
import org.reclipse.tracer.model.definition.TraceDefinition;
import org.reclipse.tracer.symb.ui.util.example.TestIMethod;
import org.reclipse.tracer.symbolicexecution.ui.util.SymbolicMethodFinder;



public class SymbolicMethodFinderTest
{
   private String sourceString = "public class SimpleExample { "
         + "public static void main(String[] args) { symbolicMethod();}"
         + "private static void symbolicMethod() { anotherMethod1(); anotherMethod2(); anotherMethod3(); }"
         + "private static void anotherMethod3(){} "
         + "private static void anotherMethod2() { methodInTraceDef1(); }"
         + "private static void methodInTraceDef1(){} "
         + "private static void anotherMethod1() { anotherMethod4(); } "
         + "private static void anotherMethod4() { methodInTraceDef2(); } "
         + "private static void methodInTraceDef2() { methodInTraceDef3(); } "
         + "private static void methodInTraceDef3(){}}";

   private TraceDefinition traceDefinition = null;

   private IMethod triggerMethod = null;


   @Before
   public void setUp() throws Exception
   {
      // set up trace definition
      this.traceDefinition = new TraceDefinition();
      ConsiderTrace considerTrace = new ConsiderTrace();
      ConsiderClass considerClass = new ConsiderClass();
      ConsiderMethod method1 = new ConsiderMethod();
      method1.setName("methodInTraceDef1");
      ConsiderMethod method2 = new ConsiderMethod();
      method2.setName("methodInTraceDef2");
      ConsiderMethod method3 = new ConsiderMethod();
      method3.setName("methodInTraceDef3");
      considerClass.addToMethods(method1);
      considerClass.addToMethods(method2);
      considerClass.addToMethods(method3);
      considerTrace.addToClasses(considerClass);
      this.traceDefinition.setConsiderTrace(considerTrace);

      // set up trigger method
      // IWorkspaceRoot root= ResourcesPlugin.getWorkspace().getRoot();
      // // IProject project= root.getProject(projectName);
      // // project.create(null);
      // // project.open(null);
      //
      // ASTParser parser = ASTParser.newParser(AST.JLS3);
      // parser.setKind(ASTParser.K_COMPILATION_UNIT);
      // parser.setSource(sourceString.toCharArray());
      // parser.setResolveBindings(true);
      // parser.setBindingsRecovery(true);
      // CompilationUnit unit = (CompilationUnit) parser.createAST(null);
      //
      // TypeDeclaration typeDecl = (TypeDeclaration) unit.types().get(0);
      // MethodDeclaration methodDecl = (MethodDeclaration) typeDecl
      // .bodyDeclarations().get(5);
      // this.triggerMethod = (IMethod) methodDecl.resolveBinding()
      // .getJavaElement();
      // TODO fix the triggerMethod
      this.triggerMethod = new TestIMethod();
   }


   @Test
   public void testFindSymbolicMethod()
   {
//      JavaProject project = (JavaProject) getSelectedGFRNAnnotation()
//            .getProject();
//      IJavaProject javaProject = project.getIJavaProject();
//      IJavaElement[] elements = { javaProject };
//      IJavaSearchScope scope = SearchEngine.createJavaSearchScope(elements);
//      SymbolicMethodFinder finder = new SymbolicMethodFinder(scope);
//      IMethod symbolicMethod = finder.findSymbolicMethod(this.triggerMethod,
//            this.traceDefinition, scope);
//      assertNotNull(symbolicMethod);
   }

}
