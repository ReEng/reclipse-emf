package org.reclipse.behavior.specification.ui.commands;


import org.eclipse.draw2d.geometry.Rectangle;
import org.reclipse.behavior.specification.ui.editparts.BehavioralPatternEditPart;
import org.reclipse.behavior.specification.ui.util.ASTUtil;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.PSPatternSpecification;

import de.uni_paderborn.basicSequenceDiagram.AbstractSequenceDiagramObject;
import de.uni_paderborn.basicSequenceDiagram.BasicSequenceDiagramFactory;
import de.uni_paderborn.basicSequenceDiagram.Lifeline;


public abstract class AbstractCreateObjectCommand extends AbstractBPCommand
{
   public AbstractCreateObjectCommand(BehavioralPatternEditPart diagramEditPart)
   {
      super("create object", diagramEditPart);
   }

   protected AbstractSequenceDiagramObject object;

   private Rectangle constraint;


   public Rectangle getConstraint()
   {
      return this.constraint;
   }


   public void setConstraint(Rectangle constraint)
   {
      this.constraint = constraint;
   }


   protected static final String NAME = "newObject";


   @Override
   public void undo()
   {
      AbstractSequenceDiagramObject model = this.getModel();
      model.getLifeline().getFragments().clear();
      model.getLifeline().setObject(null);
      model.setLifeline(null);
      model.getDiagram().getObjects().remove(model);
      super.undo();
   }


   protected AbstractSequenceDiagramObject getModel()
   {
      return (AbstractSequenceDiagramObject) this.object;
   }


   /**
    * Return a class object from the according structural pattern.
    * 
    * @param patternSpecification
    * @return
    */
   protected PSObject getAvailablePSObject(
         PSPatternSpecification patternSpecification)
   {
      if (patternSpecification != null)
      {
         for (PSNode node : patternSpecification.getNodes())
         {
            if (node instanceof PSObject)
            {
               if (ASTUtil.objectIsClass((PSObject) node))
               {
                  return (PSObject) node;
               }
            }
         }
      }
      return null;
   }


   protected void initializeObject()
   {
      this.object.setName(NAME);

      Lifeline lifeline = BasicSequenceDiagramFactory.eINSTANCE
            .createLifeline();
      this.object.setLifeline(lifeline);
   }

}
