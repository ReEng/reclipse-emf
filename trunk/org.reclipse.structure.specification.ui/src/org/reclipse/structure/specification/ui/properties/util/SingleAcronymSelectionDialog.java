package org.reclipse.structure.specification.ui.properties.util;


import java.util.Collection;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.reclipse.metrics.extensionpoints.Metric;
import org.reclipse.structure.specification.SpecificationFactory;
import org.reclipse.structure.specification.provider.SpecificationItemProviderAdapterFactory;
import org.reclipse.structure.specification.ui.PSPlugin;


public class SingleAcronymSelectionDialog extends Dialog
{
   private class PSMetricLabelProvider extends LabelProvider implements
         ITableLabelProvider
   {
      private final Image image;


      public PSMetricLabelProvider()
      {
         AdapterFactoryLabelProvider provider = new AdapterFactoryLabelProvider(
               new SpecificationItemProviderAdapterFactory());
         image = provider.getImage(SpecificationFactory.eINSTANCE
               .createPSMetricConstraint());
      }


      @Override
      public Image getColumnImage(Object element, int index)
      {
         // image only on the first column
         if (index == 0)
         {
            return image;
         }

         return null;
      }


      @Override
      public String getColumnText(Object element, int index)
      {
         // get element
         Metric metric = (Metric) element;

         switch (index)
         {
            case 0:
               return metric.getAcronym();
            case 1:
               return metric.getName();
            default:
               return ""; //$NON-NLS-1$
         }
      }
   }

   private String title;

   private TableViewer viewer;

   private CLabel infoboxHeader;

   private Text infoboxText;

   private Metric selected;

   private Collection<Metric> input;


   public SingleAcronymSelectionDialog()
   {
      super(PlatformUI.getWorkbench().getDisplay().getActiveShell());
   }


   @Override
   protected void configureShell(Shell shell)
   {
      // configure
      super.configureShell(shell);

      // set title
      shell.setText(title);
   }


   @Override
   protected Control createDialogArea(Composite parent)
   {
      // get dialog composite
      Composite main = (Composite) super.createDialogArea(parent);

      // available label
      Label filtered = new Label(main, SWT.LEAD);
      filtered.setText("&Metric:");

      // add viewer
      viewer = new TableViewer(main, SWT.BORDER | SWT.FULL_SELECTION
            | SWT.V_SCROLL);
      viewer.setContentProvider(new ArrayContentProvider());
      viewer.setLabelProvider(new PSMetricLabelProvider());
      viewer.setComparator(new ViewerComparator()
      {
         @Override
         public int compare(Viewer viewer, Object one, Object two)
         {
            Metric mOne = (Metric) one;
            Metric mTwo = (Metric) two;

            return mOne.getAcronym().compareTo(mTwo.getAcronym());
         }
      });
      GridDataFactory.fillDefaults().grab(true, true)
            .applyTo(viewer.getTable());

      // configure table
      viewer.getTable().setHeaderVisible(true);

      // prepare columns
      final TableColumn colAcronym = new TableColumn(viewer.getTable(),
            SWT.LEAD);
      colAcronym.setText("Acronym");
      colAcronym.setWidth(72);

      TableColumn colDescription = new TableColumn(viewer.getTable(), SWT.LEAD);
      colDescription.setText("Description");
      colDescription.setWidth(320);

      // info box header
      infoboxHeader = new CLabel(main, SWT.LEAD);
      GridDataFactory.fillDefaults().grab(true, false).applyTo(infoboxHeader);

      infoboxText = new Text(main, SWT.LEAD | SWT.READ_ONLY | SWT.MULTI
            | SWT.WRAP);
      infoboxText.setText(""); //$NON-NLS-1$
      GridDataFactory.fillDefaults().grab(true, false).applyTo(infoboxText);

      // hook listeners
      hookListeners();

      // fill viewer
      viewer.setInput(input);

      // set selection
      viewer.setSelection(new StructuredSelection(selected), true);

      return main;
   }


   @Override
   protected IDialogSettings getDialogBoundsSettings()
   {
      // get settings root
      IDialogSettings root = PSPlugin.getDefault().getDialogSettings();

      // get the settings
      IDialogSettings settings = root.getSection(getClass().getName());

      // create when nothing exists
      if (settings == null)
      {
         settings = root.addNewSection(getClass().getName());
         settings.put("DIALOG_X_ORIGIN", 50); //$NON-NLS-1$
         settings.put("DIALOG_Y_ORIGIN", 50); //$NON-NLS-1$
         settings.put("DIALOG_WIDTH", 450); //$NON-NLS-1$
         settings.put("DIALOG_HEIGHT", 500); //$NON-NLS-1$
      }

      // return settings;
      return settings;
   }


   public Metric getSelected()
   {
      return selected;
   }


   protected void handleSelection(StructuredSelection selection)
   {
      // get reference
      selected = (Metric) selection.getFirstElement();

      // refresh info fields
      PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable()
      {
         @Override
         public void run()
         {
            // create header text
            StringBuilder header = new StringBuilder();
            header.append(selected.getAcronym());
            header.append(": "); //$NON-NLS-1$
            header.append(selected.getName());

            // create image
            Image image = ((PSMetricLabelProvider) viewer.getLabelProvider())
                  .getColumnImage(selected, 0);

            // set them
            infoboxHeader.setImage(image);
            infoboxHeader.setText(header.toString());

            // get documentation
            String info = selected.getDescription();
            if (info == null || info.trim().isEmpty())
            {
               info = "No further documentation available.";
            }

            infoboxText.setText(info);
            infoboxText.getParent().layout();
         }
      });

      // hide OK button
      Button okay = getButton(IDialogConstants.OK_ID);
      if (okay != null)
      {
         okay.setEnabled(selected != null);
      }
   }


   private void hookListeners()
   {
      // viewer selection handler
      viewer.addPostSelectionChangedListener(new ISelectionChangedListener()
      {
         @Override
         public void selectionChanged(SelectionChangedEvent event)
         {
            if (!event.getSelection().isEmpty()
                  && event.getSelection() instanceof StructuredSelection)
            {
               handleSelection((StructuredSelection) event.getSelection());
            }
         }
      });

      // viewer double click
      viewer.addDoubleClickListener(new IDoubleClickListener()
      {
         @Override
         public void doubleClick(DoubleClickEvent event)
         {
            // handle the selection
            if (!event.getSelection().isEmpty()
                  && event.getSelection() instanceof StructuredSelection)
            {
               handleSelection((StructuredSelection) event.getSelection());
            }

            // check for correct selection
            if (selected != null)
            {
               okPressed();
            }
         }
      });
   }


   @Override
   protected boolean isResizable()
   {
      return true;
   }


   public void setInput(Collection<Metric> input, String currentAcronym)
   {
      // set current type selection
      selected = null;
      for (Metric metric : input)
      {
         if (metric.getAcronym().equals(currentAcronym))
         {
            selected = metric;
            break;
         }
      }

      // set input
      this.input = input;
   }


   public void setTitle(String title)
   {
      this.title = title;
   }
}
