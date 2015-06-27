package org.reclipse.structure.inference.ui.views.annotations;


import java.util.Collection;

import org.reclipse.structure.inference.annotations.ASGAnnotation;


public class ConsequentContainer
{

   private ASGAnnotation annotation;


   public ConsequentContainer(ASGAnnotation annotation)
   {
      this.annotation = annotation;
   }


   public boolean hasChildren()
   {
      return !annotation.getConsequentAnnos().isEmpty();
   }


   public Collection<ASGAnnotation> getChildren()
   {
      return annotation.getConsequentAnnos();
   }
}
