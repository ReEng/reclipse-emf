package org.reclipse.structure.inference.ui.views.annotations;


import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.reclipse.structure.inference.annotations.ASGAnnotation;


public class AnnotationsViewerFilter extends ViewerFilter
{
   private Collection<ASGAnnotation> hiding;

   private boolean showAntecedents;

   private boolean showConsequents;

   private boolean showAnnotated;


   public AnnotationsViewerFilter()
   {
      hiding = new ArrayList<ASGAnnotation>();

      showAntecedents = true;
      showConsequents = true;
      showAnnotated = true;
   }


   public void showOnlyAntecendents()
   {
      showAntecedents = true;
      showConsequents = false;
      showAnnotated = false;
   }


   public void showOnlyConsequents()
   {
      showAntecedents = false;
      showConsequents = true;
      showAnnotated = false;
   }


   public void showAll()
   {
      showAntecedents = true;
      showConsequents = true;
      showAnnotated = true;
   }


   public void hideAnnotations(Collection<ASGAnnotation> annotations)
   {
      hiding = annotations;
   }


   @Override
   public boolean select(Viewer viewer, Object parent, Object element)
   {
      if (element instanceof ASGAnnotation)
      {
         return !hiding.contains(element);
      }

      if (element instanceof AntecedentContainer)
      {
         return showAntecedents;
      }

      if (element instanceof ConsequentContainer)
      {
         return showConsequents;
      }

      if (element instanceof AnnotatedContainer)
      {
         return showAnnotated;
      }

      return true;
   }
}
