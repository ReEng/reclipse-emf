/**
 * 
 */
package org.reclipse.generator.ui.util;

import java.util.Comparator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.PluginRegistry;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog;
import org.reclipse.generator.ui.GeneratorUIActivator;

/**
 * This class is based on the class {@link org.eclipse.pde.internal.ui.dialog.PluginSelectionDialog},
 * which could not be references it is hidden.
 * 
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 *
 */
public class PluginSelectionDialog extends FilteredItemsSelectionDialog
{
   private static final String DIALOG_SETTINGS = "org.reclipse.generator.ui.util.PluginSelectionDialog";
   private IPluginModelBase[] fModels;
   private static final String MESSAGE = "Select a Plug-in:";
   private static final String TITLE = "Plug-in Selection";
   
   public PluginSelectionDialog(Shell parentShell, boolean multipleSelection) {
      this(parentShell, getElements(), multipleSelection);
   }

   public PluginSelectionDialog(Shell parentShell, IPluginModelBase[] models, boolean multipleSelection) {
      super(parentShell, multipleSelection);
      fModels = models;
      setTitle(TITLE);
      setMessage(MESSAGE);
   }

   
   private static IPluginModelBase[] getElements() {
      return PluginRegistry.getActiveModels(false);
   }

   /**
    * @see org.eclipse.ui.dialogs.FilteredItemsSelectionDialog#createExtendedContentArea(org.eclipse.swt.widgets.Composite)
    */
   @Override
   protected Control createExtendedContentArea(Composite parent)
   {
      return null;
   }


   /**
    * @see org.eclipse.ui.dialogs.FilteredItemsSelectionDialog#createFilter()
    */
   @Override
   protected ItemsFilter createFilter()
   {
      return new PluginSearchItemsFilter();
   }


   /**
    * @see org.eclipse.ui.dialogs.FilteredItemsSelectionDialog#fillContentProvider(org.eclipse.ui.dialogs.FilteredItemsSelectionDialog.AbstractContentProvider, org.eclipse.ui.dialogs.FilteredItemsSelectionDialog.ItemsFilter, org.eclipse.core.runtime.IProgressMonitor)
    */
   @Override
   protected void fillContentProvider(AbstractContentProvider contentProvider,
         ItemsFilter itemsFilter, IProgressMonitor progressMonitor)
         throws CoreException
   {
      for (int i = 0; i < fModels.length; i++) {
         contentProvider.add(fModels[i], itemsFilter);
         progressMonitor.worked(1);
      }
      progressMonitor.done();
   }


   /**
    * @see org.eclipse.ui.dialogs.FilteredItemsSelectionDialog#getDialogSettings()
    */
   @Override
   protected IDialogSettings getDialogSettings()
   {
      IDialogSettings settings = GeneratorUIActivator.getDefault().getDialogSettings().getSection(DIALOG_SETTINGS);

      if (settings == null) {
         settings = GeneratorUIActivator.getDefault().getDialogSettings().addNewSection(DIALOG_SETTINGS);
      }

      return settings;
   }


   /**
    * @see org.eclipse.ui.dialogs.FilteredItemsSelectionDialog#getElementName(java.lang.Object)
    */
   @Override
   public String getElementName(Object item)
   {
      if (item instanceof IPluginModelBase) {
         IPluginModelBase model = (IPluginModelBase) item;
         return model.getPluginBase().getId();
      }
      return null;
   }


   /**
    * @see org.eclipse.ui.dialogs.FilteredItemsSelectionDialog#getItemsComparator()
    */
   @Override
   protected Comparator getItemsComparator()
   {
      return new PluginSearchComparator();
   }


   /**
    * @see org.eclipse.ui.dialogs.FilteredItemsSelectionDialog#validateItem(java.lang.Object)
    */
   @Override
   protected IStatus validateItem(Object item)
   {
      return new Status(IStatus.OK, GeneratorUIActivator.PLUGIN_ID, 0, "", null);
   }

   
   private class PluginSearchItemsFilter extends ItemsFilter {

      @Override
      public boolean isConsistentItem(Object item) {
         return true;
      }

      @Override
      public boolean matchItem(Object item) {
         String id = null;
         if (item instanceof IPluginModelBase) 
         {
            IPluginModelBase model = (IPluginModelBase) item;
            id = model.getPluginBase().getId();
         }

         return (matches(id));
      }

      @Override
      protected boolean matches(String text) {
         String pattern = patternMatcher.getPattern();
         if (pattern.indexOf("*") != 0 & pattern.indexOf("?") != 0 & pattern.indexOf(".") != 0) {//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            pattern = "*" + pattern; //$NON-NLS-1$
            patternMatcher.setPattern(pattern);
         }
         return patternMatcher.matches(text);
      }
   }
   
   private class PluginSearchComparator implements Comparator {

      public int compare(Object o1, Object o2) {
         int id1 = getId(o1);
         int id2 = getId(o2);

         if (id1 != id2)
            return id1 - id2;
         return compareSimilarObjects(o1, o2);
      }

      private int getId(Object element) {
         if (element instanceof IPluginModelBase) {
            return 100;
         }
         return 0;
      }

      private int compareSimilarObjects(Object o1, Object o2) {
         if (o1 instanceof IPluginModelBase && o2 instanceof IPluginModelBase) {
            IPluginModelBase ipmb1 = (IPluginModelBase) o1;
            IPluginModelBase ipmb2 = (IPluginModelBase) o2;
            return comparePlugins(ipmb1.getPluginBase(), ipmb2.getPluginBase());
         }
         return 0;
      }

      private int comparePlugins(IPluginBase ipmb1, IPluginBase ipmb2) {
         return ipmb1.getId().compareTo(ipmb2.getId());
      }

   }
}
