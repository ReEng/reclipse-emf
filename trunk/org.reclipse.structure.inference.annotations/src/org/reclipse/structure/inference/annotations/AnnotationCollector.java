/**
 * 
 */
package org.reclipse.structure.inference.annotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.emf.ecore.EObject;

/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 *
 */
public class AnnotationCollector
{
   Map<EObject, Collection<ASGAnnotation>> eObject2ASGAnnotationMap;
   
   private static AnnotationCollector instance;
   
   private AnnotationCollector()
   {
      eObject2ASGAnnotationMap = new ConcurrentHashMap<EObject, Collection<ASGAnnotation>>();
   }
   
   public static AnnotationCollector get()
   {
      if(instance == null)
      {
         instance = new AnnotationCollector();
      }
      return instance;
   }
   
   public Collection<ASGAnnotation> getAnnos(EObject o)
   {
      synchronized (eObject2ASGAnnotationMap)
      {
         return eObject2ASGAnnotationMap.get(o);
      }
   }
   
   public void put(EObject o, ASGAnnotation anno)
   {
      synchronized (eObject2ASGAnnotationMap)
      {
         Collection<ASGAnnotation> list = eObject2ASGAnnotationMap.get(o);
         if(list == null)
         {
            list = new ArrayList<ASGAnnotation>();
            eObject2ASGAnnotationMap.put(o, list);
         }
         if(!list.contains(anno))
         {
            list.add(anno);
         }
      }
   }
   
   public void clear()
   {
      synchronized (eObject2ASGAnnotationMap)
      {
        eObject2ASGAnnotationMap.clear();  
      }
   }
}
