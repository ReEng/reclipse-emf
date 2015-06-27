/*
 * The FUJABA ToolSuite for Eclipse project:
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
package org.reclipse.behavior.specification.ui.editpolicies;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gef.requests.SelectionRequest;


/**
 * @author Dietrich Travkin
 * @author Last Editor: $Author: lowende $
 * @version $Revision: 361 $ $Date: 2006-10-06 17:38:18 +0200 (Fr, 06 Okt 2006) $
 */
public class SelectAnotherFigureFromPrimaryLayerEditPolicy extends SelectionEditPolicy
{

   private List excludeEditPartTypes = null;


   public SelectAnotherFigureFromPrimaryLayerEditPolicy()
   {
   }


   public SelectAnotherFigureFromPrimaryLayerEditPolicy(List excludeEditPartTypes)
   {
      this.excludeEditPartTypes = excludeEditPartTypes;
   }


   /**
    * @see org.eclipse.gef.editpolicies.SelectionEditPolicy#hideSelection()
    */
   @Override
   protected void hideSelection()
   {
   }


   /**
    * @see org.eclipse.gef.editpolicies.SelectionEditPolicy#showSelection()
    */
   @Override
   protected void showSelection()
   {
   }


   /**
    * Instead of selecting the host <code>EditPart</code> of this policy, an other
    * <code>EditPart</code> from the primary layer is selected.
    * 
    * @see org.eclipse.gef.EditPolicy#getTargetEditPart(org.eclipse.gef.Request)
    */
   @Override
   public EditPart getTargetEditPart(Request request)
   {
      if (this.getHost() instanceof GraphicalEditPart && RequestConstants.REQ_SELECTION.equals(request.getType())
            && request instanceof SelectionRequest)
      {
         ArrayList<IFigure> list = new ArrayList<IFigure>(1);
         IFigure exclude = ((GraphicalEditPart) this.getHost()).getFigure();
         list.add(exclude);
         IFigure layer = this.getLayer(LayerConstants.PRIMARY_LAYER);
         Point location = ((SelectionRequest) request).getLocation();
         IFigure figure = null;
         if (location != null)
         {
            figure = layer.findFigureAtExcluding(location.x, location.y, list);
         }
         EditPart result = null;
         while (figure != null && result == null)
         {
            result = (EditPart) this.getHost().getViewer().getVisualPartMap().get(figure);

            if (result != null && this.excludeEditPartTypes != null && this.excludeEditPartTypes.size() > 0)
            {
               Iterator iter = this.excludeEditPartTypes.iterator();
               while (iter.hasNext())
               {
                  Class editPartClass = (Class) iter.next();
                  if (editPartClass.isInstance(result))
                  {
                     result = null;
                     break;
                  }
               }
            }

            if (result == null)
            {
               list.add(figure);
               figure = layer.findFigureAtExcluding(location.x, location.y, list);
            }
         }
         return result;
      }
      return null;
   }
}
