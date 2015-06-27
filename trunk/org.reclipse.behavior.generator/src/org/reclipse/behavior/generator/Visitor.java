/*
 * The FUJABA ToolSuite project:
 *
 *   FUJABA is the acronym for 'From Uml to Java And Back Again'
 *   and originally aims to provide an environment for round-trip
 *   engineering using UML as visual programming language. During
 *   the last years, the environment has become a base for several
 *   research activities, e.g. distributed software, database
 *   systems, modelling mechanical and electrical systems and
 *   their simulation. Thus, the environment has become a project,
 *   where this source code is part of. Further details are avail-
 *   able via http://www.fujaba.de
 *
 *      Copyright (C) Fujaba Development Group
 *
 *   This library is free software; you can redistribute it and/or
 *   modify it under the terms of the GNU Lesser General Public
 *   License as published by the Free Software Foundation; either
 *   version 2.1 of the License, or (at your option) any later version.
 *
 *   You should have received a copy of the GNU Lesser General Public
 *   License along with this library; if not, write to the Free
 *   Software Foundation, Inc., 59 Temple Place, Suite 330, Boston,
 *   MA 02111-1307, USA or download the license under
 *   http://www.gnu.org/copyleft/lesser.html
 *
 * WARRANTY:
 *
 *   This library is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *   GNU Lesser General Public License for more details.
 *
 * Contact address:
 *
 *   Fujaba Management Board
 *   Software Engineering Group
 *   University of Paderborn
 *   Warburgerstr. 100
 *   D-33098 Paderborn
 *   Germany
 *
 *   URL  : http://www.fujaba.de
 *   email: info@fujaba.de
 *
 */
package org.reclipse.behavior.generator;


import java.lang.reflect.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


/**
 * Visitor pattern implementation.
 * 
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 315 $ $Date: 2006-10-24 14:19:43 +0200 (Di, 24 Okt 2006) $
 */
public class Visitor
{

   private final static transient Map visitorCache = new HashMap();

   private final transient Map visitMethodCache;


   public Visitor()
   {
      this(true);
   }


   public Visitor(boolean ignoreUnhandled)
   {
      this.ignoreUnhandled = ignoreUnhandled;
      synchronized (visitorCache)
      {
         Map visit = (Map) visitorCache.get(this.getClass());
         if (visit == null)
         {
            visit = new HashMap();
            visitorCache.put(this.getClass(), visit);
         }
         this.visitMethodCache = visit;
      }
   }


   private boolean ignoreUnhandled;


   public boolean isIgnoreUnhandled()
   {
      return this.ignoreUnhandled;
   }


   public void setIgnoreUnhandled(boolean ignoreUnknown)
   {
      this.ignoreUnhandled = ignoreUnknown;
   }


   /**
    * Visits an element.
    * 
    * @param node The object to be visited
    * @return null
    */
   public Object visit(Object node)
   {
      if (node == null)
      {
         throw new IllegalArgumentException("Argument must not be null!");
      }

      Class nodeClass = node.getClass();
      try
      {
         Method method = getMethod(nodeClass, false);
         return method.invoke(this, node);
      }
      catch (NoSuchMethodException e)
      {
         if (!this.ignoreUnhandled)
         {
            UnsupportedOperationException exception = new UnsupportedOperationException(
                  "No visitor method found for " + nodeClass.getName());
            exception.initCause(e);
            throw exception;
         }
      }
      catch (IllegalArgumentException e)
      {
         System.err.println(e.getMessage());
         e.printStackTrace();
      }
      catch (IllegalAccessException e)
      {
         System.err.println(e.getMessage());
         e.printStackTrace();
      }
      catch (InvocationTargetException e)
      {
         System.err.println(e.getMessage());
         e.printStackTrace();
      }

      return null;
   }


   /**
    * Visits an element.
    * 
    * @param node the object to be visited
    * @param arguments a variable argument list
    * @return null
    */
   public Object visit(Object node, Object... arguments)
   {
      Class nodeClass = node.getClass();
      try
      {
         Method method = getMethod(nodeClass, true);
         return method.invoke(this, node, arguments);
      }
      catch (NoSuchMethodException e)
      {
         if (!this.ignoreUnhandled)
         {
            UnsupportedOperationException exception = new UnsupportedOperationException(
                  "No visitor method found for " + nodeClass.getName());
            exception.initCause(e);
            throw exception;
         }
      }
      catch (IllegalArgumentException e)
      {
         System.err.println(e.getMessage());
         e.printStackTrace();
      }
      catch (IllegalAccessException e)
      {
         System.err.println(e.getMessage());
         e.printStackTrace();
      }
      catch (InvocationTargetException e)
      {
         System.err.println(e.getMessage());
         e.printStackTrace();
      }

      return null;
   }


   /**
    * Find a suitable visitor method.
    * <p>
    * 
    * A suitable visitor method is of the form <code>visit&lt;<i>classname</i> &gt;</code> where
    * <i>classname</i> is the name of the class on which the visitor is used, one of its
    * superclasses or one of its implemented interfaces.
    * <p>
    * 
    * This implementation uses the following search order:
    * <ol>
    * <li> Use the name of the class itself</li>
    * <li> Use the names of the interfaces implemented by the class in order of their appearance in
    * the <code>implements</code> clause of the class</li>
    * <li> Use the names of the super-interfaces of the implemented interfaces in breadth-first
    * order</li>
    * <li> Recursively use the name of the classes superclass as delivered by
    * {@link java.lang.Class#getSuperclass} and start with 1. again</li>
    * </ol>
    * 
    * 
    * @param clazz The class for which a suitable visitor method is needed
    * @param arguments true, if a visit method with a variable argument list should be returned
    * @return The visitor method if one is found or null
    * @see #findMethod
    * @see java.lang.Class#getSuperclass
    * @throws NoSuchMethodException Exception description not provided
    */
   protected Method getMethod(Class clazz, boolean arguments)
         throws NoSuchMethodException
   {
      synchronized (this.visitMethodCache)
      {
         Method method = (Method) this.visitMethodCache.get(clazz);

         if (method == null)
         {
            Class tmpClass = clazz;
            if (!this.visitMethodCache.containsKey(clazz))
            {
               LinkedList classes = new LinkedList();
               StringBuffer buffer = new StringBuffer();

               while (method == null && tmpClass != null)
               {
                  classes.clear();
                  classes.add(tmpClass);
                  while (method == null && classes.size() > 0)
                  {
                     method = findMethod(classes, buffer, arguments);
                  }
                  if (method == null)
                  {
                     tmpClass = tmpClass.getSuperclass();
                  }
               }

               this.visitMethodCache.put(clazz, method);
            }
            if (method == null)
            {
               throw new NoSuchMethodException("No visitor method found for "
                     + clazz.getName());
            }
         }
         return method;
      }
   }


   /**
    * Find a suitable visitor method.
    * <p>
    * 
    * A suitable visitor method is of the form <code>visit&lt;<i>classname</i> &gt;</code> where
    * <i>classname</i> is the name of the class on which the visitor is used, one of its
    * superclasses or one of its implemented interfaces.
    * <p>
    * 
    * This method implements (together with getMethod) a breadth-first search beginning on the first
    * element of the provided list of classes. That class is checked first and if no suitable method
    * is found, all implemented interfaces of the class are enqueued in the provided class list,
    * which can be used for subsequent calls by getMethod for the breadth-first search.
    * 
    * 
    * @param classList List of classes to search. Calling the method will dequeue the first class in
    *           the List and if no suitable method is found, enqueue all interfaces implemented by
    *           that class.
    * @param buffer StringBuffer to use for efficiently building the method name
    * @param arguments true, if a visit method with a variable argument list should be returned
    * @return a suitable visitor method or null if none was found
    * @see #getMethod
    */
   protected Method findMethod(LinkedList classList, StringBuffer buffer,
         boolean arguments)
   {
      Class clazz = (Class) classList.removeFirst();
      Method method = null;
      createMethodName(clazz, buffer);
      try
      {
         if (arguments)
         {
            method = getClass().getMethod(buffer.toString(), clazz,
                  Object[].class);
         }
         else
         {
            method = getClass().getMethod(buffer.toString(), clazz);
         }
      }
      catch (NoSuchMethodException e)
      {
         Class[] interfaces = clazz.getInterfaces();
         for (int i = 0; i < interfaces.length; i++)
         {
            classList.add(interfaces[i]);
         }
      }

      return method;
   }


   /**
    * Creates a method name from the prefix 'visit' and the class name.
    */
   protected void createMethodName(Class clazz, StringBuffer buffer)
   {
      String methodName = clazz.getName();

      buffer.delete(0, buffer.length());
      buffer.append(methodName);
      int baseIndex = buffer.lastIndexOf(".") + 1;
      if (baseIndex > 0)
      {
         buffer.delete(0, baseIndex);
      }
      baseIndex = buffer.lastIndexOf("$") + 1;
      if (baseIndex > 0)
      {
         buffer.delete(0, baseIndex);
      }
      buffer.insert(0, "visit");
   }

}
