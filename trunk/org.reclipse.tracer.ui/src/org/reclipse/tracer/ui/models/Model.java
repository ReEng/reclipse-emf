package org.reclipse.tracer.ui.models;

import java.util.HashSet;
import java.util.Iterator;


/**
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4624 $ $Date: 2011-01-18 10:58:31 +0100 (Di, 18 Jan 2011) $
 */
public abstract class Model
{
   public HashSet collectionListeners = new HashSet();


   public void addCollectionListener(CollectionListener listener)
   {
      this.collectionListeners.add(listener);
   }


   public void removeCollectionListener(CollectionListener listener)
   {
      this.collectionListeners.remove(listener);
   }


   protected void fireElementAddedEvent(String collectionName, Object newValue)
   {
      Iterator iter = this.collectionListeners.iterator();
      while (iter.hasNext())
      {
         CollectionListener listener = (CollectionListener) iter.next();
         listener.elementAdded(collectionName, newValue);
      }
   }
   
   
   protected void fireAllElementsRemovedEvent(String collectionName)
   {
      Iterator iter = this.collectionListeners.iterator();
      while (iter.hasNext())
      {
         CollectionListener listener = (CollectionListener) iter.next();
         listener.allElementsRemoved(collectionName);
      }
   }
   
   
   public void removeYou()
   {
      this.collectionListeners.clear();
   }
   
}
