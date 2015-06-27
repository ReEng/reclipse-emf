package org.reclipse.behavior.inference;


/**
 * <h2>Association</h2>
 * 
 * <pre>
 *                  behavioralAnnotation       1 
 * Annotation -----------------------------------> BehavioralAnnotation
 *                          behavioralAnnotation
 * 
 *                  structuralAnnotation       1 
 * Annotation -----------------------------------> StructuralAnnotation
 *                          structuralAnnotation 
 * </pre>
 * 
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3776 $ $Date: 2007-09-06 21:44:53 +0200 (Do, 06 Sep 2007) $
 */
public class Annotation
{

   private String type;


   public String getType()
   {
      String result = this.type;
      if (result == null)
      {
         if (getStructuralAnnotation() != null)
         {
            result = getStructuralAnnotation().getType();
         }
      }
      return result;
   }


   public void setType(final String value)
   {
      if (this.type != value)
      {
         this.type = value;
      }
   }


   /**
    * <pre>
    *                  behavioralAnnotation       1 
    * Annotation -----------------------------------> BehavioralAnnotation
    *                          behavioralAnnotation 
    * </pre>
    */
   private BehavioralAnnotation behavioralAnnotation;


   public BehavioralAnnotation getBehavioralAnnotation()
   {
      return this.behavioralAnnotation;
   }


   public boolean setBehavioralAnnotation(final BehavioralAnnotation value)
   {
      boolean changed = false;
      if (this.behavioralAnnotation != value)
      {
         this.behavioralAnnotation = value;
         changed = true;
      }
      return changed;
   }


   /**
    * <pre>
    *                  structuralAnnotation       1 
    * Annotation -----------------------------------> StructuralAnnotation
    *                          structuralAnnotation 
    * </pre>
    */
   private StructuralAnnotation structuralAnnotation;


   public StructuralAnnotation getStructuralAnnotation()
   {
      return this.structuralAnnotation;
   }


   public boolean setStructuralAnnotation(final StructuralAnnotation value)
   {
      boolean changed = false;
      if (this.structuralAnnotation != value)
      {
         this.structuralAnnotation = value;
         changed = true;
      }
      return changed;
   }


   /**
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return getStructuralAnnotation().toString();
   }


   public void removeYou()
   {
      final StructuralAnnotation tmpStructuralAnnotation = getStructuralAnnotation();
      if (tmpStructuralAnnotation != null)
      {
         setStructuralAnnotation(null);
      }
      final BehavioralAnnotation tmpBehavioralAnnotation = getBehavioralAnnotation();
      if (tmpBehavioralAnnotation != null)
      {
         setBehavioralAnnotation(null);
      }
   }

}
