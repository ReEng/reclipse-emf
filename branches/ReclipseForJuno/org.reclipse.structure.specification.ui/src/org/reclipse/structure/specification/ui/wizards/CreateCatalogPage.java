package org.reclipse.structure.specification.ui.wizards;


import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.reclipse.metamodel.MetaModel;
import org.reclipse.structure.specification.util.ModelHelper;


/**
 * This wizard page is used on the creation of a new pattern specification catalog to select a name,
 * whether or not to create an initial pattern, a name for the pattern, a meta model and a metrics
 * configuration for it.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class CreateCatalogPage extends WizardPage
{

   /**
    * The text field for the catalog name.
    */
   private Text catalogNameText;

   /**
    * The table viewer to show the registered meta models.
    */
   private TableViewer modelsViewer;

   /**
    * The icon of the meta model description.
    */
   private Label modelDescriptionIcon;

   /**
    * The description label for the selected meta model.
    */
   private Label modelDescriptionLabel;

   /**
    * The currently selected meta model.
    */
   protected MetaModel selectedMetaModel;


   /**
    * Default constructor for the page.
    * 
    * @param name The name of the page.
    */
   protected CreateCatalogPage(String name)
   {
      super(name);
      setPageComplete(isValid());
   }


   @Override
   public void createControl(Composite parent)
   {
      initializeDialogUnits(parent);

      // prepare layout for main composite
      GridLayout layout = new GridLayout();
      layout.verticalSpacing = 12;

      // create main composite
      Composite top = new Composite(parent, SWT.NONE);
      top.setLayout(layout);
      top.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

      createCatalogNameText(top);

      createModelsViewer(top);

      selectFirstModel();

      setControl(top);
   }


   @Override
   public void setVisible(boolean visible)
   {
      super.setVisible(visible);

      if (visible)
      {
         catalogNameText.setFocus();
         catalogNameText.selectAll();
      }
   }

   private void selectFirstModel()
   {
      if (modelsViewer != null)
      {
         // select first available meta model
         if (modelsViewer.getTable().getItemCount() > 0)
         {
            modelsViewer.getTable().setSelection(0);
            modelsViewer.setSelection(modelsViewer.getSelection());
         }
      }
   }


   /**
    * Selects the meta model from the selection when valid.
    * 
    * @param selection The selection.
    */
   protected void selectMetaModel(IStructuredSelection selection)
   {
      if (selection.getFirstElement() instanceof MetaModel)
      {
         selectedMetaModel = (MetaModel) selection.getFirstElement();
         modelDescriptionIcon.setImage(Dialog
               .getImage(Dialog.DLG_IMG_MESSAGE_INFO));
         modelDescriptionLabel.setText(selectedMetaModel.getDescription());
         modelDescriptionLabel.getParent().getParent().layout();
      }
   }


   /**
    * Checks whether all elements are valid.
    * 
    * @return Returns <code>true</code> when all elements have valid input, otherwise
    *         <code>false</code>.
    */
   protected boolean isValid()
   {
      if (getCatalogName() != null && getSelectedMetaModel() != null)
      {
         return true;
      }

      return false;
   }


   /**
    * Creates all relevant parts to configure a new catalog.
    * 
    * @param parent The parent composite on which to create.
    */
   private void createCatalogNameText(Composite parent)
   {
      Composite name = new Composite(parent, SWT.NONE);
      name.setLayout(new GridLayout(2, false));
      name.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));

      Label label = new Label(name, SWT.NONE);
      label.setText("Catalog &name:");

      catalogNameText = new Text(name, SWT.BORDER);
      catalogNameText.setText("New Catalog");
      catalogNameText.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true,
            false));
      catalogNameText.addModifyListener(new ModifyListener()
      {
         @Override
         public void modifyText(ModifyEvent e)
         {
            setPageComplete(isValid());
         }
      });
   }


   /**
    * Configures the meta models viewer on the selected parent composite.
    * 
    * @param parent The parent composite.
    */
   private void createModelsViewer(Composite parent)
   {
      // prepare group
      Group models = new Group(parent, SWT.NONE);
      models.setText("ASG Meta &Model");
      GridLayoutFactory.fillDefaults().margins(5, 5).numColumns(2).applyTo(models);
      GridDataFactory.fillDefaults().grab(true, false).applyTo(models);

      // prepare table viewer
      modelsViewer = new TableViewer(models, SWT.SINGLE | SWT.V_SCROLL
            | SWT.FULL_SELECTION | SWT.BORDER);
      GridDataFactory.fillDefaults().grab(true, false).span(2, 0).applyTo(modelsViewer.getControl());
      modelsViewer.getTable().setHeaderVisible(true);
      modelsViewer.getTable().setLinesVisible(true);
      modelsViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         @Override
         public void selectionChanged(SelectionChangedEvent event)
         {
            if (modelsViewer != null
                  && modelsViewer.getSelection() instanceof IStructuredSelection)
            {
               IStructuredSelection selection = (IStructuredSelection) modelsViewer
                     .getSelection();

               selectMetaModel(selection);
            }

            setPageComplete(isValid());
         }
      });
      createModelsColumns(modelsViewer);

      modelsViewer.setContentProvider(new ArrayContentProvider());
      modelsViewer.setInput(ModelHelper.getAllMetaModels());

      // prepare description icon and text
      modelDescriptionIcon = new Label(models, SWT.LEAD);
      modelDescriptionIcon.setImage(Dialog
            .getImage(Dialog.DLG_IMG_MESSAGE_ERROR));
      GridDataFactory.fillDefaults().applyTo(modelDescriptionIcon);

      modelDescriptionLabel = new Label(models, SWT.WRAP);
      GridDataFactory.fillDefaults().grab(true, false).applyTo(modelDescriptionLabel);
   }


   /**
    * Configures the columns for the meta model on the selected table viewer.
    * 
    * @param viewer The viewer on which to configure the columns.
    */
   private void createModelsColumns(TableViewer viewer)
   {
      String[] titles = { "Name", "Version", "ID" };
      int[] bounds = { 200, 60, 224 };

      // first column for the name
      TableViewerColumn col = createTableViewerColumn(viewer, titles[0],
            bounds[0]);
      col.setLabelProvider(new ColumnLabelProvider()
      {
         @Override
         public String getText(Object element)
         {
            MetaModel p = (MetaModel) element;
            return p.getName();
         }
      });

      // second column for the version
      col = createTableViewerColumn(viewer, titles[1], bounds[1]);
      col.setLabelProvider(new ColumnLabelProvider()
      {
         @Override
         public String getText(Object element)
         {
            MetaModel p = (MetaModel) element;
            return p.getVersion();
         }
      });

      // third column for the id
      col = createTableViewerColumn(viewer, titles[2], bounds[2]);
      col.setLabelProvider(new ColumnLabelProvider()
      {
         @Override
         public String getText(Object element)
         {
            MetaModel p = (MetaModel) element;
            return p.getId();
         }
      });
   }


   /**
    * Creates a new table viewer column with the specified title and width.
    * 
    * @param viewer The viewer on which to create the column.
    * @param title The title for the column.
    * @param width The width of the column.
    * @return Returns the created <code>{@link TableViewerColumn}</code>.
    */
   private TableViewerColumn createTableViewerColumn(TableViewer viewer,
         String title, int width)
   {
      TableViewerColumn col = new TableViewerColumn(viewer, SWT.LEAD);

      TableColumn column = col.getColumn();
      column.setText(title);
      column.setWidth(width);
      column.setResizable(true);
      column.setMoveable(true);

      return col;
   }


   /**
    * Getter of the (trimmed) name for the new catalog.
    * 
    * @return Returns the name for the new catalog.
    */
   public String getCatalogName()
   {
      if (catalogNameText != null
            && catalogNameText.getText().trim().length() > 0)
      {
         return catalogNameText.getText().trim();
      }

      return null;
   }


   /**
    * Getter of the selected meta model.
    * 
    * @return Returns the selected meta model.
    */
   public MetaModel getSelectedMetaModel()
   {
      return selectedMetaModel;
   }
}
