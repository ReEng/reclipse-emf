package org.reclipse.tracer.model.tracegraph;


import java.util.HashMap;



/**
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 4278 $ $Date: 2010-02-18 13:32:16 +0100 (Do, 18 Feb 2010) $
 */
public class TGFactory
{

   private HashMap<String, TGType> types = new HashMap<String, TGType>();


   public TGType createTGType(String typeName)
   {
      if (this.types.get(typeName) != null)
      {
         throw new IllegalArgumentException("TGType with name " + typeName
               + " already exists!");
      }

      TGType mcgType = new TGType();
      mcgType.setName(typeName);
      this.types.put(typeName, mcgType);

      return mcgType;
   }


   public TGType getTGType(String typeName)
   {
      return this.types.get(typeName);
   }


   private HashMap<String, TGObject> objects = new HashMap<String, TGObject>();


   public TGObject createTGObject(String id)
   {
      if (this.objects.get(id) != null)
      {
         throw new IllegalArgumentException("TGObject with id " + id
               + " already exists!");
      }

      TGObject mcgObject = new TGObject();
      mcgObject.setId(id);
      this.objects.put(id, mcgObject);

      return mcgObject;
   }


   public TGObject getTGObject(String id)
   {
      return this.objects.get(id);
   }


   private HashMap<String, TGThread> threads = new HashMap<String, TGThread>();


   public TGThread createTGThread(String name)
   {
      if (this.threads.get(name) != null)
      {
         throw new IllegalArgumentException("TGThread with name " + name
               + " already exists!");
      }

      TGThread mcgThread = new TGThread();
      mcgThread.setName(name);
      this.threads.put(name, mcgThread);

      return mcgThread;
   }


   public TGThread getTGThread(String name)
   {
      return this.threads.get(name);
   }

}
