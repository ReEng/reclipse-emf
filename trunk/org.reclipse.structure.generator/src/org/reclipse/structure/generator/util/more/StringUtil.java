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
package org.reclipse.structure.generator.util.more;


/**
 * this class provides a lot of functions which could be very usefull
 * 
 * @author $Author: l3_g5 $
 * @version $Revision: 6953 $
 */
public class StringUtil
{
   /**
    * This function returns true if
    * 
    * @param s No description provided
    * @return The nullOrEmpty value
    */
   public static boolean isNullOrEmpty(String s)
   {
      if (s == null)
      {
         return true;
      }

      if (s.length() == 0)
      {
         return true;
      }

      return false;
   }


   /**
    * This function puts '"' around
    * 
    * @param string and returns it, if it's not empty and not null; else it returns the string 'null'.
    * @return No description provided
    */
   public static String quote(String string)
   {
      if (string != null && string.length() != 0)
      {
         return "\"" + string + "\"";
      }
      else
      {
         return "null";
      }
   }


   /**
    * This function turns the first character of
    * 
    * @param strg No description provided
    * @return No description provided
    */
   public static String upFirstChar(String strg)
   {
      if (strg != null && strg.length() != 0)
      {
         StringBuffer newBuf = new StringBuffer(strg);
         newBuf.setCharAt(0, Character.toUpperCase(newBuf.charAt(0)));

         return newBuf.toString();
      }
      else
      {
         return strg;
      }
   } // upFirstChar


   /**
    * This function turns the first character of
    * 
    * @param strg No description provided
    * @return No description provided
    */
   public static String downFirstChar(String strg)
   {
      if (strg.length() != 0)
      {
         StringBuffer newBuf = new StringBuffer(strg);
         newBuf.setCharAt(0, Character.toLowerCase(newBuf.charAt(0)));

         return newBuf.toString();
      }
      else
      {
         return strg;
      }
   } // upFirstChar


   /**
    * This function turns all the uppercase chars at the beginning of the string up to but not including the last one
    * before a lowercase char. The first one is always converted.
    * <p>
    * 
    * For example:
    * <ul>
    * <li> <code>startLowerCase</code> is not altered</li>
    * <li> <code>ALLUPPERCASE</code> becomes <code>alluppercase</code></li>
    * <li> <code>FirstUpperCase</code> becomes <code>firstUpperCase</code></li>
    * <li> <code>SOMEUpperCase</code> becomes <code>someUpperCase</code></li>
    * 
    * @param strg the string to convert
    * @return No description provided
    */
   public static String downStart(String strg)
   {
      int len = strg.length();
      if (len != 0)
      {
         StringBuffer newBuf = new StringBuffer(strg);
         boolean done = false;
         for (int i = 0; i < len && !done; i++)
         {
            if (i > 0 && len != i + 1 && newBuf.charAt(i + 1) == Character.toLowerCase(newBuf.charAt(i + 1)))
            {
               done = true;
            }
            else
            {
               newBuf.setCharAt(i, Character.toLowerCase(newBuf.charAt(i)));
            }
         }
         return newBuf.toString();
      }
      else
      {
         return strg;
      }
   } // upFirstChar


   /**
    * this function shorts the string text to length width
    * 
    * @param text the string which should be shorted
    * @param width max length of the returned string
    * @return a string which has the max. length of width
    */
   public static String textToWidth(String text, int width)
   {

      if (text == null || text.length() == 0)
      {
         return "";
      }

      if (width < 5)
      {
         return "...";
      }

      StringBuffer buf = new StringBuffer(width);
      if (width >= text.length())
      {
         buf.append(text);
      }
      else
      {
         width = (width - 3) / 2;
         buf.append(text.substring(0, width));
         buf.append("...");
         buf.append(text.substring(text.length() - width));
      }
      for (int i = 0; i < buf.length(); i++)
      {
         if (buf.charAt(i) == '\n')
         {
            buf.setCharAt(i, '@');
         }
      }
      return new String(buf);
   }
}

/*
 * $Log$ Revision 1.28 2006/01/05 16:17:36 l3_g5 applied patched for codegen from Fujaba 4; speed up codegen; loading of
 * old project files works
 */
