package org.reclipse.metamodel;


import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;


/**
 * This class will be used to process additional modifications on an AST, it is called before the
 * start of an inference to prepare the abstract syntax tree.
 * 
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author harka
 */
public interface IASTPreparer
{
   /**
    * This method is called before the start of an inference.
    * 
    * @param root The AST root element.
    * @param monitor The process monitor to show the current state.
    * @return Should return the same root element after the process.
    */
   EObject modifyModel(EObject root, IProgressMonitor monitor);
}
