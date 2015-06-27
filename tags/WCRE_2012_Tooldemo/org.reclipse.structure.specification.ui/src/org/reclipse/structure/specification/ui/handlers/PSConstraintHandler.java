package org.reclipse.structure.specification.ui.handlers;


import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Font;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.fujaba.commons.Commons4EclipseFonts;
import org.fujaba.commons.edit.commands.SetConstraintCommand;
import org.fujaba.commons.edit.parts.AbstractDiagramEditPart;
import org.reclipse.metrics.extensionpoints.MetricUtil;
import org.reclipse.structure.specification.OperatorType;
import org.reclipse.structure.specification.PSFuzzyConstraint;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.ui.edit.commands.CreatePSFuzzyConstraintCommand;
import org.reclipse.structure.specification.ui.edit.commands.CreatePSNodeConstraintCommand;
import org.reclipse.structure.specification.ui.edit.commands.EditPSFuzzyExpressionCommand;
import org.reclipse.structure.specification.ui.edit.parts.AbstractPSNodeConstraintEditPart;
import org.reclipse.structure.specification.ui.edit.parts.PSAnnotationEditPart;
import org.reclipse.structure.specification.ui.edit.parts.PSCombinedFragmentEditPart;
import org.reclipse.structure.specification.ui.edit.parts.PSObjectEditPart;
import org.reclipse.structure.specification.ui.utils.PSConstants;
import org.reclipse.structure.specification.util.ModelHelper;



/**
 * This handler is used to execute all commands that create or edit node (and fragment) constraints.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSConstraintHandler extends AbstractHandler
{
   protected static final String METRIC = "org.reclipse.structure.specification.ui.commands.AddMetricConstraint"; //$NON-NLS-1$

   protected static final String ATTRIBUTE = "org.reclipse.structure.specification.ui.commands.AddAttributeConstraint"; //$NON-NLS-1$

   protected static final String SET_SIZE = "org.reclipse.structure.specification.ui.commands.AddSetSizeConstraint"; //$NON-NLS-1$

   protected static final String FUZZY_METRIC = "org.reclipse.structure.specification.ui.commands.AddFuzzyConstraint"; //$NON-NLS-1$

   protected static final String FUZZY_SIZE = "org.reclipse.structure.specification.ui.commands.AddFuzzySetConstraint"; //$NON-NLS-1$

   protected static final String FUZZY_SET_RATING = "org.reclipse.structure.specification.ui.commands.AddSetRatingFunction"; //$NON-NLS-1$


   @Override
   public Object execute(ExecutionEvent event) throws ExecutionException
   {
      ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event)
            .getActivePage().getSelection();
      if (selection != null && selection instanceof IStructuredSelection)
      {
         IStructuredSelection strucSelection = (IStructuredSelection) selection;
         for (Object o : strucSelection.toList())
         {
            if (o instanceof PSObjectEditPart
                  || o instanceof PSAnnotationEditPart
                  || o instanceof PSCombinedFragmentEditPart)
            {
               CreateNodeConstraintAction action = new CreateNodeConstraintAction(
                     HandlerUtil.getActivePart(event),
                     (AbstractDiagramEditPart) o, event.getCommand().getId());

               action.run();
            }
            else if (o instanceof AbstractPSNodeConstraintEditPart)
            {
               EditNodeConstraintAction action = new EditNodeConstraintAction(
                     HandlerUtil.getActivePart(event),
                     (AbstractPSNodeConstraintEditPart) o);

               action.run();
            }
         }
      }
      return null;
   }

   /**
    * This creation action is used by the creation request.
    * 
    * @author harka
    * @author Last editor: $Author$
    * @version $Revision$ $Date$
    */
   private static class CreateNodeConstraintAction extends SelectionAction
   {

      private String id;

      private AbstractDiagramEditPart host;


      /**
       * Default constructor.
       * 
       * @param part The workbench part.
       */
      public CreateNodeConstraintAction(IWorkbenchPart part,
            AbstractDiagramEditPart host, String id)
      {
         super(part);
         this.id = id;
         this.host = host;
      }


      @Override
      public void run()
      {
         // prepare command (incl. resize when necessary)
         CompoundCommand command = new CompoundCommand();

         EClass type = null;
         String text = "";

         if (id.equals(FUZZY_METRIC) || id.equals(FUZZY_SIZE)
               || id.equals(FUZZY_SET_RATING))
         {
            // fuzzy constraints
            String acronym = null;

            if (id.equals(FUZZY_METRIC))
            {
               // fuzzy metric constraint
               type = SpecificationPackage.eINSTANCE
                     .getPSFuzzyMetricConstraint();

               // get first metric
               if (!MetricUtil.getAllMetrics().isEmpty())
               {
                  acronym = MetricUtil.getAllMetrics().iterator().next()
                        .getAcronym();
               }
            }
            else if (id.equals(FUZZY_SIZE))
            {
               // fuzzy set size constraint
               type = SpecificationPackage.eINSTANCE
                     .getPSFuzzyMetricConstraint();
               acronym = PSConstants.CONSTRAINT_TEXT_SIZE_ATTR;
            }
            else if (id.equals(FUZZY_SET_RATING))
            {
               // fuzzy set rating
               type = SpecificationPackage.eINSTANCE
                     .getPSFuzzySetRatingConstraint();
            }

            text = "fuzzy constraint: " + acronym;
            command.add(new CreatePSFuzzyConstraintCommand(host.getModel(),
                  type, acronym));
         }
         else if (id.equals(METRIC) || id.equals(SET_SIZE))
         {
            // metric constraints
            String acronym = null;
            type = SpecificationPackage.eINSTANCE.getPSMetricConstraint();

            if (id.equals(METRIC))
            {
               // get first metric
               if (!MetricUtil.getAllMetrics().isEmpty())
               {
                  acronym = MetricUtil.getAllMetrics().iterator().next()
                        .getAcronym();
               }
            }
            if (id.equals(SET_SIZE))
            {
               acronym = PSConstants.CONSTRAINT_TEXT_SIZE_ATTR;
            }

            text = acronym + " " + ModelHelper.getReadable(OperatorType.EQUAL)
                  + " null";
            command.add(new CreatePSNodeConstraintCommand(host.getModel(),
                  type, acronym));
         }
         else if (id.equals(ATTRIBUTE))
         {
            // attribute constraint
            text = ModelHelper.getAttributeText(ModelHelper
                  .getFirstAttribute((PSObject) host.getRealModel()))
                  + " "
                  + ModelHelper.getReadable(OperatorType.EQUAL) + " null";

            command.add(new CreatePSNodeConstraintCommand(host.getModel(),
                  SpecificationPackage.eINSTANCE.getPSAttributeConstraint(),
                  null));
         }

         if (!command.isEmpty())
         {
            if (!(host instanceof PSCombinedFragmentEditPart))
            {
               // add resize command on PSNode
               Font font = Commons4EclipseFonts
                     .getFont(Commons4EclipseFonts.FONT_DEFAULT_NORMAL);
               Dimension size = TextUtilities.INSTANCE.getStringExtents(text,
                     font);
               Rectangle rect = new Rectangle();
               rect.x = host.getModel().getX();
               rect.y = host.getModel().getY();
               rect.width = Math.max(host.getModel().getWidth(),
                     size.width + 14);
               rect.height = host.getModel().getHeight() + size.height + 5;

               command.add(new SetConstraintCommand(host, rect));
            }

            execute(command.unwrap());
         }
      }


      @Override
      protected boolean calculateEnabled()
      {
         return host != null;
      }
   }

   /**
    * This creation action is used by the creation request.
    * 
    * @author harka
    * @author Last editor: $Author$
    * @version $Revision$ $Date$
    */
   private static class EditNodeConstraintAction extends SelectionAction
   {

      private PSFuzzyConstraint constraint;


      /**
       * Default constructor.
       * 
       * @param activePart The workbench part.
       */
      public EditNodeConstraintAction(IWorkbenchPart activePart,
            AbstractPSNodeConstraintEditPart constraintPart)
      {
         super(activePart);

         constraint = (PSFuzzyConstraint) constraintPart.getRealModel();
      }


      @Override
      public void run()
      {
         if (calculateEnabled())
         {
            EditPSFuzzyExpressionCommand command = new EditPSFuzzyExpressionCommand(
                  constraint);
            execute(command);
         }
      }


      @Override
      protected boolean calculateEnabled()
      {
         return constraint != null;
      }
   }
}
