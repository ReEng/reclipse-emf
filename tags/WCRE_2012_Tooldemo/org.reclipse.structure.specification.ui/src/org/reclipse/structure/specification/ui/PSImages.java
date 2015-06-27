/*
 * The FUJABA ToolSuite RE project:
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
package org.reclipse.structure.specification.ui;


import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;


/**
 * @author $Author: lowende $
 * @version $Revision: 3357 $ $Date: 2007-02-08 15:18:46 +0100 (Do, 08 Feb 2007) $
 */
public abstract class PSImages
{
   public final static String IMAGE_ETOOL_EDIT_ANNOTATION_16 = "icons/etool/editPSAnnotation16.gif";

   public final static String IMAGE_ETOOL_EDIT_ANNOTATION_24 = "icons/etool/editPSAnnotation24.gif";

   public final static String IMAGE_ETOOL_EDIT_ATTREXPRPAIR_16 = "icons/etool/editPSAttrExprPair16";

   public final static String IMAGE_ETOOL_EDIT_ATTREXPRPAIR_24 = "icons/etool/editPSAttrExprPair24.gif";

   public final static String IMAGE_ETOOL_EDIT_CONSTRAINT_16 = "icons/etool/editPSConstraint16.gif";

   public final static String IMAGE_ETOOL_EDIT_CONSTRAINT_24 = "icons/etool/editPSConstraint24.gif";

   public final static String IMAGE_ETOOL_EDIT_GENERALIZATION_16 = "icons/etool/editPSGeneralization16.gif";

   public final static String IMAGE_ETOOL_EDIT_GENERALIZATION_24 = "icons/etool/editPSGeneralization24.gif";

   public final static String IMAGE_ETOOL_EDIT_LINK_16 = "icons/etool/editPSLink16.gif";

   public final static String IMAGE_ETOOL_EDIT_LINK_24 = "icons/etool/editPSLink24.gif";

   public final static String IMAGE_ETOOL_EDIT_OBJECT_16 = "icons/etool/editPSObject16.gif";

   public final static String IMAGE_ETOOL_EDIT_OBJECT_24 = "icons/etool/editPSObject24.gif";

   public final static String IMAGE_ETOOL_EDIT_OPT_FRAGMENT_16 = "icons/etool/editPSCombinedFragment16.gif";

   public final static String IMAGE_ETOOL_EDIT_PATH_16 = "icons/etool/editPSPath16.gif";

   public final static String IMAGE_ETOOL_EDIT_PATH_24 = "icons/etool/editPSPath24.gif";

   public final static String IMAGE_OBJ_PATTERN_RULE_16 = "icons/full/obj16/PSPatternSpecification.gif";

   public static final String IMAGE_HELP = "icons/help.gif";

   // TODO: move to math package?
   public final static String IMAGE_FUNCTION_LINEAR = "icons/functions/linear-function.png";

   public final static String IMAGE_FUNCTION_LIM0 = "icons/functions/e-lim0-function.png";

   public final static String IMAGE_FUNCTION_LIM1 = "icons/functions/e-lim1-function.png";


   public static Image getImage(String symbolicName)
   {
      return PSPlugin.getDefault().getImageRegistry().get(symbolicName);
   }


   public static ImageDescriptor getDescriptor(String symbolicName)
   {
      return PSPlugin.getDefault().getImageRegistry()
            .getDescriptor(symbolicName);
   }

}
