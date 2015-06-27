/**
 * 
 */
package org.reclipse.generator.ui.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.dialogs.WizardExportResourcesPage;

/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 *
 */
public class SelectCatalogWizardPage extends WizardExportResourcesPage
{
   protected SelectCatalogWizardPage(String pageName, IStructuredSelection initialResourceSelection)
   {
      super(pageName, initialResourceSelection);
   }


   public IFile getFirstSelection()
   {
      Object o = this.getSelectedResources().get(0);
      if(o instanceof IFile)
      {
         return (IFile) o;
      }
      return null;
   }


   @Override
   public void createControl(Composite parent)
   {
      super.createControl(parent);
      setupBasedOnInitialSelections();
   }


   public void handleEvent(Event event)
   {
      
   }


   @Override
   protected void createDestinationGroup(Composite parent)
   {
      
   }


   @Override
   protected void createOptionsGroupButtons(Group optionsGroup)
   {
//      super.createOptionsGroupButtons(optionsGroup);
   }


   @Override
   protected boolean determinePageCompletion()
   {
      if(!super.determinePageCompletion())
      {
         return false;
      }
      
      if(this.getSelectedResources().size() != 1)
      {
         setErrorMessage("Choose exactly one pattern catalog.");
         return false;
      }
      
      IResource resource =  (IResource) this.getSelectedResources().get(0);
      if(!"psc".equals(resource.getFileExtension()))
      {
         setErrorMessage("Choose a .psc file .");
         return false;
      }
      
      setErrorMessage(null);
      return true;
   }


   @Override
   protected void createOptionsGroup(Composite parent)
   {
//      super.createOptionsGroup(parent);
   }


   @Override
   protected List getTypesToExport()
   {
      ArrayList<String> extensions = new ArrayList<String>();
      extensions.add("psc");
      return extensions;
   }


}
