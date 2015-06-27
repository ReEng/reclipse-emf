package org.reclipse.tracer.model.tracegraph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author avolk
 * @author Last editor: $Author: avolk $
 * @version $Revision: 4543 $ $Date: 2010-09-22 18:27:10 +0200 (Mi, 22 Sep 2010) $
 *
 */
public class TGTrace
{
   public TGTrace()
   {      
   }
   
   private String date;
   
   public String getDate()
   {
      return this.date;
   }
   
   public void setDate(String date)
   {
      this.date = date;
   }
   
   private String mainClass;
   
   public String getMainClass()
   {
      return this.mainClass;
   }
   
   public void setMainClass(String mainClass)
   {
      this.mainClass = mainClass;
   }
   
   private boolean symbolicExececution = false;
   
   public boolean getSymbolicExecution()
   {
      return this.symbolicExececution;
   }
   
   public void setSymbolicExecution(boolean symbExec)
   {
      this.symbolicExececution = symbExec;
   }
   
   private List<TGTracePath> tracePaths = new LinkedList<TGTracePath>();
   
   public boolean addToTracePaths(TGTracePath tracePath)
   {
      boolean changed = false;
      if(tracePath != null)
      {
         this.tracePaths.add(tracePath);
         //TODO
         if(changed)
          tracePath.setTrace(this);
      }
      return changed;
   }
   
   public boolean hasInTracePaths(TGTracePath tracePath)
   {
      return (tracePath != null && this.tracePaths.contains(tracePath));
   }
   
   public Iterator<TGTracePath> iteratorOfTracePaths()
   {
      return this.tracePaths.iterator();
   }
   
   public void removeAllFromTracePaths()
   {
      for(Iterator<TGTracePath> tracePathIt = this.iteratorOfTracePaths(); tracePathIt.hasNext();)
         this.removeFromTracePath(tracePathIt.next());
   }
   
   public boolean removeFromTracePath(TGTracePath tracePath)
   {
      boolean changed = false;
      if(tracePath != null)
      {
         changed = this.tracePaths.remove(tracePath);
         if(changed)
            tracePath.setTrace(null);
      }
      return changed;
   }
   
   public int sizeOfTracePaths()
   {
      return this.tracePaths.size();
   }
   
   
}
