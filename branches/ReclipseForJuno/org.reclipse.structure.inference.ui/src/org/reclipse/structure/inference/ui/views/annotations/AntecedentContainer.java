package org.reclipse.structure.inference.ui.views.annotations;


import java.util.Collection;

import org.reclipse.structure.inference.annotations.ASGAnnotation;


public class AntecedentContainer
{

   private ASGAnnotation annotation;


   public AntecedentContainer(ASGAnnotation annotation)
   {
      this.annotation = annotation;
   }


   public boolean hasChildren()
   {
      return !annotation.getAntecedentAnnos().isEmpty();
   }


   public Collection<ASGAnnotation> getChildren()
   {
      return annotation.getAntecedentAnnos();
   }
}
