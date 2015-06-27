package org.reclipse.structure.specification.ui.wizards;

import org.eclipse.jface.wizard.Wizard;

/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public abstract class AbstractPSFuzzyExpressionWizard extends Wizard
{
   protected PSFuzzyExpressionWizardPage page = null;
   private boolean acronymSelectable;


   public AbstractPSFuzzyExpressionWizard(boolean acronymSelectable)
   {
      this.acronymSelectable = acronymSelectable;
      setNeedsProgressMonitor(false);
      setForcePreviousAndNextButtons(false);
   }

   @Override
   public void addPages()
   {
      this.page = new PSFuzzyExpressionWizardPage();
      addPage(page);
      this.page.setDisplayAcronym(acronymSelectable);
   }

   @Override
   public boolean canFinish()
   {
      return (page != null && page.isValidState());
   }
}
