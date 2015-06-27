package org.reclipse.behavior.inference.input;


import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.reclipse.behavior.inference.StructuralAnnotation;
import org.reclipse.metamodel.AbstractElementLabeler;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.specification.util.ModelHelper;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3805 $ $Date: 2007-09-13 19:57:44 +0200 (Do, 13 Sep 2007) $
 */
public class StructuralAnnotationsReader
{

   public static Set<StructuralAnnotation> load(final String fileName) throws FileNotFoundException
   {
      URI annotationsFileUri = URI.createPlatformResourceURI(fileName, true);
      ResourceSet annoRess = new ResourceSetImpl();
      URI normalized = annoRess.getURIConverter().normalize(annotationsFileUri);
      Resource annoRes = annoRess.getResource(normalized, true);

      return load(annoRes);
   }


   /**
    * Loads structural pattern annotations from a given Resource.
    * 
    * @param a resource structural pattern annotations
    * @return A set of structural pattern annotations, if loading was successful, otherwise null.
    */
   public static Set<StructuralAnnotation> load(Resource annoRes)
   {
      EList<EObject> annotations = annoRes.getContents();
      Set<StructuralAnnotation> structuralAnnotations = new HashSet<StructuralAnnotation>();
      for (EObject obj : annotations)
      {
         StructuralAnnotation structuralAnnotation = createStructuralAnnotationFromASGAnnotation(obj);
         structuralAnnotations.add(structuralAnnotation);
      }
      return structuralAnnotations;
   }


   /**
    * Loads structural pattern annotations from a given Resource.
    * 
    * @param a resource structural pattern annotations
    * @return A set of structural pattern annotations, if loading was successful, otherwise null.
    */
   public static Set<StructuralAnnotation> load(ASGAnnotation annotation)
   {
      Set<StructuralAnnotation> structuralAnnotations = new HashSet<StructuralAnnotation>();
      StructuralAnnotation structuralAnnotation = createStructuralAnnotationFromASGAnnotation(annotation);
      structuralAnnotations.add(structuralAnnotation);
      return structuralAnnotations;
   }


   private static StructuralAnnotation createStructuralAnnotationFromASGAnnotation(EObject obj)
   {
      ASGAnnotation asgAnnotation = (ASGAnnotation) obj;
      StructuralAnnotation structuralAnnotation = new StructuralAnnotation();
      structuralAnnotation.setFuzzyBelief(asgAnnotation.getAnnotationRanking());
      structuralAnnotation.setType(asgAnnotation.getPattern().getName());
      for (String boundObjectKey : asgAnnotation.getBoundObjects().keySet())
      {
         EObject boundObject = asgAnnotation.getBoundObjects().get(boundObjectKey).get(0);
         if (!(boundObject instanceof ASGAnnotation))
         {
            AbstractElementLabeler labeler = ModelHelper.getMetaModel(asgAnnotation.getPattern()).getLabeler();
            String boundObjectName = labeler.getText(boundObject);
            structuralAnnotation.addToNodes(boundObjectKey, boundObjectName);
         }
      }
      return structuralAnnotation;
   }


}
