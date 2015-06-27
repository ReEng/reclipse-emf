/**
 * 
 */
package org.reclipse.behavior.specification.ui.editor;


import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.fujaba.commons.editor.overviewpage.NestedDiagramsSectionPart;
import org.fujaba.commons.identifier.Identifier;
import org.reclipse.behavior.specification.BPCatalog;
import org.reclipse.behavior.specification.BehavioralPattern;
import org.reclipse.behavior.specification.ui.commands.DeleteBehavioralPatternCommand;
import org.reclipse.behavior.specification.ui.wizards.NewBehavioralPatternWizard;


/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class BehavioralPatternSectionPart extends NestedDiagramsSectionPart
{
   private BPCatalog catalog;

   private final static String SECTION_NAME = "Behavioral Patterns belonging to this catalog";

   private final static String DELETE_CONFIRM_QUESTION = "Are you sure you want to delete selected behavioral patterns?";

   private final static String CHOOSE_A_NAME_MESSAGE = "Choose a name for the new pattern specification.";

   private final static String DEFAULT_NAME = "NewPattern";


   public BehavioralPatternSectionPart(MultiPageBPCatalogEditor editor, Composite parent, FormToolkit toolkit)
   {
      super(editor, parent, toolkit, SECTION_NAME, DELETE_CONFIRM_QUESTION, CHOOSE_A_NAME_MESSAGE, DEFAULT_NAME);
      this.catalog = editor.getCatalog();
   }


   public BehavioralPatternSectionPart(MultiPageBPCatalogEditor editor, IManagedForm mForm)
   {
      this(editor, mForm.getForm().getBody(), mForm.getToolkit());
   }


   /**
    * @see org.fujaba.commons.editor.overviewpage.NestedDiagramsSectionPart#addButtonPressed(org.eclipse.swt.events.SelectionEvent)
    */
   @Override
   protected void addButtonPressed(SelectionEvent e)
   {
      NewBehavioralPatternWizard wizard = new NewBehavioralPatternWizard(catalog, (MultiPageBPCatalogEditor) editor);

      WizardDialog wizardDialog = new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
            wizard);
      wizardDialog.create();
      wizardDialog.open();
   }


   @Override
   protected Command createAddDiagramCommand(String newName)
   {
      // not necessary, since it is not called
      // the wizard performs this command
      return null;
   }


   @Override
   protected Command createDeleteDiagramCommandFor(EObject diagramRoot)
   {
      if (diagramRoot instanceof BehavioralPattern)
      {
         new DeleteBehavioralPatternCommand((BehavioralPattern) diagramRoot);
      }

      return null;
   }


   @Override
   protected EList<? extends Identifier> getDiagramElements()
   {
      return this.catalog.getBehavioralPatterns();
   }


   @Override
   protected void openDiagramFor(EObject diagramRoot)
   {
      if (diagramRoot instanceof BehavioralPattern)
      {
         ((MultiPageBPCatalogEditor) this.editor).addPageFor((BehavioralPattern) diagramRoot);
      }
   }
}
