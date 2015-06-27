package org.reclipse.generator.ui.wizard;


import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.PluginRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.reclipse.generator.ui.util.PluginSelectionDialog;
import org.reclipse.generator.ui.util.TableSelectionListener;


/**
 * @author lowende
 * @author Last editor: $Author: oleg82 $
 * @version $Revision: 4532 $ $Date: 2010-09-14 13:20:22 +0200 (Di, 14 Sep 2010) $
 */
public class CompileDependenciesPage extends WizardPage
{

   private static final String CLASS_PATH = "classPath";

   private static final String REQUIRED_PLUGINS = "requiredPlugins";

   private ArrayList<String> classPaths, requiredPlugins;

   private Table cpTable, rpTable;

   private Section classPathSection, requiredPluginsSection;

   private ScrolledForm mainForm;


   public CompileDependenciesPage(Collection<String> defaultRequiredPlugins)
   {
      super("ClassPathPage");

      setTitle("Additional Classpaths for Compilation");
      setDescription("Add additional paths for compilation of "
            + "the structural patterns catalog.");

      this.classPaths = new ArrayList<String>();
      this.requiredPlugins = new ArrayList<String>();

      if(defaultRequiredPlugins != null)
      {
         this.requiredPlugins.addAll(defaultRequiredPlugins);
      }
   }


   /**
    * 
    */
   private void initTableContent()
   {
      IDialogSettings settings = getDialogSettings();
      String[] cps = null, rps = null;
      if (settings != null)
      {
         cps = settings.getArray(CLASS_PATH);
         rps = settings.getArray(REQUIRED_PLUGINS);
      }
      if(cps != null)
      {
         //we have something non-default, remove default entries
         classPaths.clear();
         for (int i = 0; i < cps.length; i++)
         {
            classPaths.add(cps[i]);
         }
      }
      
      
      if(rps != null)
      {
         //we have something non-default, remove default entries
         requiredPlugins.clear();
         for (int i = 0; i < rps.length; i++)
         {
            requiredPlugins.add(rps[i]);
         }
      }
   }


   /**
    * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
    */
   public void createControl(Composite parent)
   {
      FormToolkit kit = new FormToolkit(parent.getDisplay());
      mainForm = kit.createScrolledForm(parent);
      GridLayout gridLayout = new GridLayout(1, false);
      GridData gridData = new GridData(GridData.FILL_BOTH);
      mainForm.getBody().setLayoutData(gridData);
      mainForm.getBody().setLayout(gridLayout);
      
      createRequiredPluginsSection(mainForm, kit);
      createClassPathSection(mainForm, kit);

      
      initTableContent();
      refreshCPTable();
      refreshRPTable();
      
      setControl(mainForm);
      Dialog.applyDialogFont(parent);
   }


   public Collection<String> getClassPath()
   {
      return this.classPaths;
   }


   public Collection<String> getRequiredPlugins()
   {
      return this.requiredPlugins;
   }


   public void finish()
   {
      IDialogSettings settings = getDialogSettings();

      if (settings != null)
      {
         Object[] objects = this.classPaths.toArray();
         String[] values = new String[objects.length];
         for (int i = 0; i < values.length; i++)
         {
            values[i] = (String) objects[i];
         }
         settings.put(CLASS_PATH, values);

         objects = this.requiredPlugins.toArray();
         values = new String[objects.length];
         for (int i = 0; i < values.length; i++)
         {
            values[i] = (String) objects[i];
         }
         settings.put(REQUIRED_PLUGINS, values);
      }
   }


   private void createClassPathSection(ScrolledForm parent, FormToolkit kit)
   {
      classPathSection = kit.createSection(parent.getBody(),
            ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE
                  | ExpandableComposite.EXPANDED);
      classPathSection.setText("Additional Classpaths for Compilation");
      classPathSection
            .setDescription("Add additional paths for compilation of "
                  + "the structural patterns catalog.");
      GridData gridData = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_FILL);
      classPathSection.setLayoutData(gridData);

      Composite sectionClient = kit.createComposite(classPathSection, SWT.NONE);
      sectionClient.setLayout(new GridLayout(2, false));

      this.cpTable = kit.createTable(sectionClient, SWT.MULTI | SWT.VIRTUAL);
      gridData = new GridData(GridData.FILL_BOTH);
      this.cpTable.setLayoutData(gridData);
      classPathSection.setClient(sectionClient);

      Composite buttonComposite = kit.createComposite(sectionClient, SWT.NONE);
      buttonComposite.setLayout(new GridLayout());

      GridData data = new GridData();
      data.verticalAlignment = SWT.TOP;
      buttonComposite.setLayoutData(data);

      Button addLibraryButton = kit.createButton(buttonComposite,
            "Add Library ...", SWT.NONE);
      addLibraryButton.setText("Add Library ...");
      data = new GridData(GridData.FILL_HORIZONTAL);
      data.verticalAlignment = SWT.TOP;
      addLibraryButton.setLayoutData(data);
      addLibraryButton.addSelectionListener(new SelectionAdapter()
      {
         @Override
         public void widgetSelected(SelectionEvent e)
         {
            // open file dialog
            FileDialog fileDialog = new FileDialog(Display.getCurrent()
                  .getActiveShell(), SWT.SINGLE | SWT.OPEN);
            fileDialog
                  .setText("Choose a library to be added to the classpath.");
            fileDialog.setFilterExtensions(new String[] { "*.jar", "*.zip" });
            fileDialog.setFilterNames(new String[] { "Java Archive (*.jar)",
                  "ZIP Archive (*.zip)" });
            String fileName = fileDialog.open();
            if (fileName != null)
            {
               CompileDependenciesPage.this.classPaths.add(fileName);
               CompileDependenciesPage.this.refreshCPTable();
            }
         }
      });

      Button addDirectoryButton = kit.createButton(buttonComposite,
            "Add Directory ...", SWT.NONE);
      data = new GridData(GridData.FILL_HORIZONTAL);
      data.verticalAlignment = SWT.TOP;
      addDirectoryButton.setLayoutData(data);
      addDirectoryButton.addSelectionListener(new SelectionAdapter()
      {
         @Override
         public void widgetSelected(SelectionEvent e)
         {
            // open directory dialog
            DirectoryDialog dirDialog = new DirectoryDialog(Display
                  .getCurrent().getActiveShell(), SWT.SINGLE | SWT.OPEN);
            dirDialog
                  .setText("Choose a directory to be added to the classpath.");
            String dirName = dirDialog.open();
            if (dirName != null)
            {
               CompileDependenciesPage.this.classPaths.add(dirName);
               CompileDependenciesPage.this.refreshCPTable();

            }
         }
      });

      Button removeButton = kit.createButton(buttonComposite, "Remove",
            SWT.NONE);
      data = new GridData(GridData.FILL_HORIZONTAL);
      data.verticalAlignment = SWT.TOP;
      removeButton.setLayoutData(data);
      removeButton.setEnabled(false);
      removeButton.addSelectionListener(new SelectionAdapter()
      {
         @Override
         public void widgetSelected(SelectionEvent e)
         {
            if (CompileDependenciesPage.this.cpTable.getSelectionCount() > 0)
            {
               TableItem[] items = CompileDependenciesPage.this.cpTable.getSelection();
               for (TableItem item : items)
               {
                  String itemText = item.getText();
                  int index = CompileDependenciesPage.this.cpTable.indexOf(item);
                  if (index > -1)
                  {
                     CompileDependenciesPage.this.cpTable.remove(index);
                     CompileDependenciesPage.this.classPaths.remove(itemText);
                  }
               }

               CompileDependenciesPage.this.refreshCPTable();
            }
         }
      });


      TableSelectionListener listener = new TableSelectionListener(cpTable,
            removeButton);
      cpTable.addSelectionListener(listener);
   }


   private void createRequiredPluginsSection(ScrolledForm parent,
         FormToolkit kit)
   {
      requiredPluginsSection = kit.createSection(parent.getBody(),
            ExpandableComposite.TITLE_BAR// | ExpandableComposite.TWISTIE
                  | ExpandableComposite.EXPANDED);

      requiredPluginsSection.setText("Required Projects");
      GridData gridData = new GridData(GridData.FILL_BOTH);
      requiredPluginsSection.setLayoutData(gridData);


      Composite sectionClient = kit.createComposite(requiredPluginsSection,
            SWT.NONE);
      sectionClient.setLayout(new GridLayout(2, false));

      this.rpTable = kit.createTable(sectionClient, SWT.MULTI);
      gridData = new GridData(GridData.FILL_BOTH);
      this.rpTable.setLayoutData(gridData);
      requiredPluginsSection.setClient(sectionClient);

      Composite buttonComposite = kit.createComposite(sectionClient, SWT.NONE);
      buttonComposite.setLayout(new GridLayout());

      GridData data = new GridData();
      data.verticalAlignment = SWT.TOP;
      buttonComposite.setLayoutData(data);

      Button addButton = kit.createButton(buttonComposite, "Add...", SWT.NONE);
      addButton.addSelectionListener(new SelectionAdapter()
      {
         @Override
         public void widgetSelected(SelectionEvent e)
         {
            IPluginModelBase[] allModels = PluginRegistry
                  .getActiveModels(false);
            ArrayList<IPluginModelBase> newModels = new ArrayList<IPluginModelBase>();
            for (int i = 0; i < allModels.length; i++)
            {
               if (allModels[i] instanceof IPluginModelBase
                     && canAdd(allModels[i]))
               {
                  newModels.add(allModels[i]);
               }
            }
            IPluginModelBase[] candidateModels = (IPluginModelBase[]) newModels
                  .toArray(new IPluginModelBase[newModels.size()]);
            PluginSelectionDialog dialog = new PluginSelectionDialog(
                  CompileDependenciesPage.this.getShell(), candidateModels, true);
            if (dialog.open() == Window.OK)
            {
               Object[] models = dialog.getResult();
               for (int i = 0; i < models.length; i++)
               {
                  String id = ((IPluginModelBase) models[i]).getPluginBase()
                        .getId();
                  CompileDependenciesPage.this.requiredPlugins.add(id);
               }
               CompileDependenciesPage.this.refreshRPTable();
            }
         }
      });
      data = new GridData(GridData.FILL_HORIZONTAL);
      data.verticalAlignment = SWT.TOP;
      addButton.setLayoutData(data);

      Button removeButton = kit.createButton(buttonComposite, "Remove",
            SWT.NONE);
      removeButton.addSelectionListener(new SelectionAdapter()
      {
         @Override
         public void widgetSelected(SelectionEvent e)
         {
            if (CompileDependenciesPage.this.rpTable.getSelectionCount() > 0)
            {
               TableItem[] items = CompileDependenciesPage.this.rpTable.getSelection();
               for (TableItem item : items)
               {
                  String itemText = item.getText();
                  int index = CompileDependenciesPage.this.rpTable.indexOf(item);
                  if (index > -1)
                  {
                     CompileDependenciesPage.this.rpTable.remove(index);
                     CompileDependenciesPage.this.requiredPlugins.remove(itemText);
                  }
               }

               CompileDependenciesPage.this.refreshCPTable();
            }
         }
      });
      data = new GridData(GridData.FILL_HORIZONTAL);
      data.verticalAlignment = SWT.TOP;
      removeButton.setLayoutData(data);
      removeButton.setEnabled(false);

      TableSelectionListener listener = new TableSelectionListener(rpTable,
            removeButton);
      rpTable.addSelectionListener(listener);
   }


   private void refreshCPTable()
   {
      this.cpTable.removeAll();
      for (String classPath : this.classPaths)
      {
         TableItem item = new TableItem(this.cpTable, SWT.NONE);
         item.setText(classPath);
      }
   }


   private void refreshRPTable()
   {
      this.rpTable.removeAll();
      for (String requiredPlugin : this.requiredPlugins)
      {
         TableItem item = new TableItem(this.rpTable, SWT.NONE);
         item.setText(requiredPlugin);
      }
   }


   private boolean canAdd(IPluginModelBase candidate)
   {
      IPluginBase plugin = candidate.getPluginBase();
      if (candidate.isFragmentModel())
         return false;

      TableItem[] items = rpTable.getItems();

      for (int i = 0; i < items.length; i++)
      {
         String alreadyChosen = items[i].getText();
         if (plugin.getId().equals(alreadyChosen))
            return false;
      }
      return true;
   }
}
