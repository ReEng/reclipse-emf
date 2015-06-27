package org.reclipse.tracer.symb.ui.util.example;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.jdt.core.IAnnotation;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaModel;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.ILocalVariable;
import org.eclipse.jdt.core.IMemberValuePair;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IOpenable;
import org.eclipse.jdt.core.ISourceRange;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeParameter;
import org.eclipse.jdt.core.ITypeRoot;
import org.eclipse.jdt.core.JavaModelException;

public class TestIMethod implements IMethod
{

   public String[] getCategories() throws JavaModelException
   {
      // TODO Auto-generated method stub
      return null;
   }


   public IClassFile getClassFile()
   {
      // TODO Auto-generated method stub
      return null;
   }


   public ICompilationUnit getCompilationUnit()
   {
      // TODO Auto-generated method stub
      return null;
   }


   public IType getDeclaringType()
   {
      // TODO Auto-generated method stub
      return null;
   }


   public int getFlags() throws JavaModelException
   {
      // TODO Auto-generated method stub
      return 0;
   }


   public ISourceRange getJavadocRange() throws JavaModelException
   {
      // TODO Auto-generated method stub
      return null;
   }


   public ISourceRange getNameRange() throws JavaModelException
   {
      // TODO Auto-generated method stub
      return null;
   }


   public int getOccurrenceCount()
   {
      // TODO Auto-generated method stub
      return 0;
   }


   public ITypeRoot getTypeRoot()
   {
      // TODO Auto-generated method stub
      return null;
   }


   public IType getType(String name, int occurrenceCount)
   {
      // TODO Auto-generated method stub
      return null;
   }


   public boolean isBinary()
   {
      // TODO Auto-generated method stub
      return false;
   }


   public boolean exists()
   {
      // TODO Auto-generated method stub
      return false;
   }


   public IJavaElement getAncestor(int ancestorType)
   {
      // TODO Auto-generated method stub
      return null;
   }


   public String getAttachedJavadoc(IProgressMonitor monitor)
         throws JavaModelException
   {
      // TODO Auto-generated method stub
      return null;
   }


   public IResource getCorrespondingResource() throws JavaModelException
   {
      // TODO Auto-generated method stub
      return null;
   }


   public int getElementType()
   {
      return IJavaElement.METHOD;
   }


   public String getHandleIdentifier()
   {
      // TODO Auto-generated method stub
      return null;
   }


   public IJavaModel getJavaModel()
   {
      // TODO Auto-generated method stub
      return null;
   }


   public IJavaProject getJavaProject()
   {
      // TODO Auto-generated method stub
      return null;
   }


   public IOpenable getOpenable()
   {
      // TODO Auto-generated method stub
      return null;
   }


   public IJavaElement getParent()
   {
      // TODO Auto-generated method stub
      return null;
   }


   public IPath getPath()
   {
      // TODO Auto-generated method stub
      return null;
   }


   public IJavaElement getPrimaryElement()
   {
      // TODO Auto-generated method stub
      return null;
   }


   public IResource getResource()
   {
      // TODO Auto-generated method stub
      return null;
   }


   public ISchedulingRule getSchedulingRule()
   {
      // TODO Auto-generated method stub
      return null;
   }


   public IResource getUnderlyingResource() throws JavaModelException
   {
      // TODO Auto-generated method stub
      return null;
   }


   public boolean isReadOnly()
   {
      // TODO Auto-generated method stub
      return false;
   }


   public boolean isStructureKnown() throws JavaModelException
   {
      // TODO Auto-generated method stub
      return false;
   }


   public Object getAdapter(Class adapter)
   {
      // TODO Auto-generated method stub
      return null;
   }


   public String getSource() throws JavaModelException
   {
      // TODO Auto-generated method stub
      return null;
   }


   public ISourceRange getSourceRange() throws JavaModelException
   {
      // TODO Auto-generated method stub
      return null;
   }


   public void copy(IJavaElement container, IJavaElement sibling,
         String rename, boolean replace, IProgressMonitor monitor)
         throws JavaModelException
   {
      // TODO Auto-generated method stub

   }


   public void delete(boolean force, IProgressMonitor monitor)
         throws JavaModelException
   {
      // TODO Auto-generated method stub

   }


   public void move(IJavaElement container, IJavaElement sibling,
         String rename, boolean replace, IProgressMonitor monitor)
         throws JavaModelException
   {
      // TODO Auto-generated method stub

   }


   public void rename(String name, boolean replace, IProgressMonitor monitor)
         throws JavaModelException
   {
      // TODO Auto-generated method stub

   }


   public IJavaElement[] getChildren() throws JavaModelException
   {
      // TODO Auto-generated method stub
      return null;
   }


   public boolean hasChildren() throws JavaModelException
   {
      // TODO Auto-generated method stub
      return false;
   }


   public IAnnotation getAnnotation(String name)
   {
      // TODO Auto-generated method stub
      return null;
   }


   public IAnnotation[] getAnnotations() throws JavaModelException
   {
      // TODO Auto-generated method stub
      return null;
   }


   public IMemberValuePair getDefaultValue() throws JavaModelException
   {
      // TODO Auto-generated method stub
      return null;
   }


   public String getElementName()
   {
      return "testTriggerMethod";
   }


   public String[] getExceptionTypes() throws JavaModelException
   {
      // TODO Auto-generated method stub
      return null;
   }


   public String[] getTypeParameterSignatures() throws JavaModelException
   {
      // TODO Auto-generated method stub
      return null;
   }


   public ITypeParameter[] getTypeParameters() throws JavaModelException
   {
      // TODO Auto-generated method stub
      return null;
   }


   public int getNumberOfParameters()
   {
      // TODO Auto-generated method stub
      return 0;
   }


   public String getKey()
   {
      // TODO Auto-generated method stub
      return null;
   }


   public String[] getParameterNames() throws JavaModelException
   {
      // TODO Auto-generated method stub
      return null;
   }


   public String[] getParameterTypes()
   {
      // TODO Auto-generated method stub
      return null;
   }


   public String[] getRawParameterNames() throws JavaModelException
   {
      // TODO Auto-generated method stub
      return null;
   }


   public String getReturnType() throws JavaModelException
   {
      // TODO Auto-generated method stub
      return null;
   }


   public String getSignature() throws JavaModelException
   {
      // TODO Auto-generated method stub
      return null;
   }


   public ITypeParameter getTypeParameter(String name)
   {
      // TODO Auto-generated method stub
      return null;
   }


   public boolean isConstructor() throws JavaModelException
   {
      return false;
   }


   public boolean isMainMethod() throws JavaModelException
   {
      // TODO Auto-generated method stub
      return false;
   }


   public boolean isResolved()
   {
      // TODO Auto-generated method stub
      return false;
   }


   public boolean isSimilar(IMethod method)
   {
      // TODO Auto-generated method stub
      return false;
   }


@Override
public ILocalVariable[] getParameters() throws JavaModelException {
	// TODO Auto-generated method stub
	return null;
}

}
