package org.reclipse.structure.inference;


import org.reclipse.structure.inference.annotations.ASGAnnotation;


/**
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author harka
 */
public interface IAnnotationEvaluator
{
   
   //TODO; find out if we still need the catalog, if yes, add the parameter for it 
   public double evaluate(ASGAnnotation annotation);
}
