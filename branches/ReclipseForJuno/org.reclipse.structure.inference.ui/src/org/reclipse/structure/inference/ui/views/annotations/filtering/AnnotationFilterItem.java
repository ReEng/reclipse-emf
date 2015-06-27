package org.reclipse.structure.inference.ui.views.annotations.filtering;


public class AnnotationFilterItem implements Comparable<AnnotationFilterItem>
{

   private boolean available;

   private boolean hidden;

   private String name;

   private double threshold;


   /**
    * Constructor for annotations that are available in the current results.
    * 
    * @param name The name of the annotation.
    */
   public AnnotationFilterItem(String name)
   {
      this.name = name;
      this.hidden = false;
      this.threshold = 0;
      this.available = true;
   }


   /**
    * Constructor for annotation loaded from settings.
    * 
    * @param name The name of the annotation.
    * @param hide Whether the annotation should be hidden.
    * @param threshold The threshold to show the annotation.
    */
   public AnnotationFilterItem(String name, boolean hide, double threshold)
   {
      this.name = name;
      this.hidden = hide;
      this.threshold = threshold;
      this.available = false;
   }


   @Override
   public int compareTo(AnnotationFilterItem o)
   {
      return getName().compareToIgnoreCase(o.getName());
   }


   public boolean isHidden()
   {
      return hidden;
   }


   public boolean isAvailable()
   {
      return available;
   }


   public String getName()
   {
      return name;
   }


   public double getThreshold()
   {
      return threshold;
   }


   public void setHidden(boolean hide)
   {
      this.hidden = hide;
   }


   public void setThreshold(double threshold)
   {
      this.threshold = threshold;
   }


   public void setAvailable(boolean available)
   {
      this.available = available;
   }
}
