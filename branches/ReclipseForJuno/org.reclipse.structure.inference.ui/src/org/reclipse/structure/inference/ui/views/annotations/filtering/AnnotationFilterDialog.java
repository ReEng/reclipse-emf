package org.reclipse.structure.inference.ui.views.annotations.filtering;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableColumn;
import org.reclipse.structure.InferenceUIPlugin;
import org.reclipse.structure.inference.ui.InferenceConstants;
import org.reclipse.structure.inference.ui.views.annotations.AnnotationView;


public class AnnotationFilterDialog extends Dialog
{
   private static final String SETTINGS_HIDDEN = "hidden"; //$NON-NLS-1$

   private static final String SETTINGS_NAMES = "names"; //$NON-NLS-1$

   private static final String SETTINGS_THRESHOLD = "threshold"; //$NON-NLS-1$

   private AnnotationFilterComparator comparator;

   private HashMap<String, AnnotationFilterItem> items;

   private TableViewer viewer;

   private IDialogSettings settings;


   public AnnotationFilterDialog(AnnotationView view)
   {
      super(view.getSite().getShell());
      setBlockOnOpen(true);

      items = new HashMap<String, AnnotationFilterItem>();
      comparator = new AnnotationFilterComparator();

      // configure settings
      IDialogSettings plugin = InferenceUIPlugin.getInstance()
            .getDialogSettings();
      settings = plugin.getSection(AnnotationFilterDialog.class
            .getCanonicalName());
      if (settings == null)
      {
         settings = plugin.addNewSection(AnnotationFilterDialog.class
               .getCanonicalName());
      }
   }


   @Override
   protected boolean isResizable()
   {
      return true;
   }


   @Override
   protected IDialogSettings getDialogBoundsSettings()
   {
      // provide default size
      String width = "DIALOG_WIDTH"; //$NON-NLS-1$
      String height = "DIALOG_HEIGHT"; //$NON-NLS-1$
      if (settings.get(width) == null)
      {
         settings.put(width, 386);
      }
      if (settings.get(height) == null)
      {
         settings.put(height, 420);
      }

      return settings;
   }


   @Override
   protected void configureShell(Shell shell)
   {
      super.configureShell(shell);

      shell.setText("Filter Annotations");
   }


   @Override
   protected Control createDialogArea(Composite parent)
   {
      Composite main = (Composite) super.createDialogArea(parent);
      main.setLayout(new GridLayout());

      Label hint = new Label(main, SWT.LEAD);
      hint.setText("Select the annotations to exclude from the view:");
      GridDataFactory.fillDefaults().grab(true, false).applyTo(hint);

      createViewer(main);

      Composite buttons = new Composite(main, SWT.NONE);
      buttons.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
      buttons.setLayout(new GridLayout(3, false));

      Button selectAll = new Button(buttons, SWT.PUSH);
      selectAll.setText("Select All");
      selectAll.addSelectionListener(new SelectionListener()
      {
         @Override
         public void widgetSelected(SelectionEvent e)
         {
            widgetDefaultSelected(e);
         }


         @Override
         public void widgetDefaultSelected(SelectionEvent e)
         {
            select(true);
         }
      });

      Button selectNone = new Button(buttons, SWT.PUSH);
      selectNone.setText("Select None");
      selectNone.addSelectionListener(new SelectionAdapter()
      {
         @Override
         public void widgetSelected(SelectionEvent e)
         {
            select(false);
         }
      });

      Button cleanup = new Button(buttons, SWT.PUSH);
      cleanup.setText("Clean Up");
      cleanup
            .setLayoutData(new GridData(SWT.TRAIL, SWT.BEGINNING, true, false));
      cleanup.addSelectionListener(new SelectionAdapter()
      {
         @Override
         public void widgetSelected(SelectionEvent e)
         {
            cleanup();
         }
      });

      hint = new Label(main, SWT.LEAD | SWT.WRAP);
      hint.setText("All decorated annotations are available in the current results. Others were filtered in an other session. To remove all those from this filtering list you can clean them up.");
      hint.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));

      return main;
   }


   private void cleanup()
   {
      Set<String> delete = new HashSet<String>();

      // collect the item names to delete
      for (AnnotationFilterItem item : items.values())
      {
         if (!item.isAvailable())
         {
            delete.add(item.getName());
         }
      }

      // delete those
      for (String name : delete)
      {
         items.remove(name);
      }

      viewer.refresh();
   }


   private void select(boolean hidden)
   {
      for (AnnotationFilterItem item : items.values())
      {
         item.setHidden(hidden);
      }

      viewer.refresh();
   }


   public void loadFilters(Set<String> availables)
   {
      String[] values;

      // load status
      values = settings.getArray(SETTINGS_HIDDEN);
      if (values != null)
      {
         boolean[] hiddens = new boolean[values.length];
         for (int i = 0; i < values.length; i++)
         {
            hiddens[i] = Boolean.valueOf(values[i]);
         }

         // load names
         values = settings.getArray(SETTINGS_NAMES);
         String[] names = new String[values.length];
         for (int i = 0; i < values.length; i++)
         {
            names[i] = values[i];
         }

         // load thresholds
         values = settings.getArray(SETTINGS_THRESHOLD);
         double[] threshold = new double[values.length];
         for (int i = 0; i < values.length; i++)
         {
            threshold[i] = Double.valueOf(values[i]);
         }

         // create items
         for (int i = 0; i < values.length; i++)
         {
            items.put(names[i], new AnnotationFilterItem(names[i], hiddens[i],
                  threshold[i]));
         }
      }

      // add new items
      AnnotationFilterItem item;
      for (String name : availables)
      {
         item = items.get(name);

         if (item == null)
         {
            items.put(name, new AnnotationFilterItem(name));
         }
         else
         {
            item.setAvailable(true);
         }
      }
   }


   /**
    * Collects all annotation names that are selected to be filtered in the results and their
    * threshold. When they should be hidden, the threshold value is <code>-1</code>.
    * 
    * @return Returns all adequate elements with their threshold.
    */
   public Map<String, Double> getFiltering()
   {
      // prepare result
      Map<String, Double> result = new HashMap<String, Double>();

      // prepare settings arrays
      String[] hiddens = new String[items.size()];
      String[] names = new String[items.size()];
      String[] thresholds = new String[items.size()];

      // save the string[]s and and build the result
      int i = 0;
      for (AnnotationFilterItem item : items.values())
      {
         // collect settings arrays
         hiddens[i] = String.valueOf(item.isHidden());
         names[i] = item.getName();
         thresholds[i] = String.valueOf(item.getThreshold());
         i++;

         // add to result
         if (item.isAvailable())
         {
            double value = item.getThreshold();
            if (item.isHidden())
            {
               value = -1;
            }

            result.put(item.getName(), value);
         }
      }

      // save settings
      settings.put(SETTINGS_HIDDEN, hiddens);
      settings.put(SETTINGS_NAMES, names);
      settings.put(SETTINGS_THRESHOLD, thresholds);

      return result;
   }


   private void createViewer(Composite parent)
   {
      viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL
            | SWT.FULL_SELECTION | SWT.BORDER);
      viewer.getControl().setLayoutData(
            new GridData(SWT.FILL, SWT.FILL, true, true));
      viewer.setContentProvider(new ArrayContentProvider());
      viewer.setComparator(comparator);

      createHiddenColumn();
      createNameColumn();
      createThresholdColumn();
      createAvailableColumn();

      viewer.getTable().setHeaderVisible(true);
      viewer.getTable().setLinesVisible(true);
      viewer.getTable().setSortDirection(SWT.DOWN);

      viewer.setInput(items.values());
      viewer.refresh();
   }


   private TableViewerColumn createHiddenColumn()
   {
      final TableViewerColumn column = new TableViewerColumn(viewer, SWT.CENTER);

      column.getColumn().setText("");
      column.getColumn().setWidth(24);
      column.getColumn().setResizable(false);
      column.getColumn().setMoveable(false);

      column.setLabelProvider(new ColumnLabelProvider()
      {
         @Override
         public String getText(Object element)
         {
            return null;
         }


         @Override
         public Image getImage(Object element)
         {
            if (((AnnotationFilterItem) element).isHidden())
            {
               return InferenceUIPlugin.getInstance().getImage(
                     InferenceConstants.IMG_FILTER_CHECKED);
            }
            else
            {
               return InferenceUIPlugin.getInstance().getImage(
                     InferenceConstants.IMG_FILTER_UNCHECKED);
            }
         }
      });
      column.setEditingSupport(new StatusEditingSupport(viewer));
      column.getColumn().addSelectionListener(
            getSelectionAdapter(column.getColumn(), 0));

      return column;
   }


   private TableViewerColumn createNameColumn()
   {
      final TableViewerColumn column = new TableViewerColumn(viewer, SWT.LEAD);

      column.getColumn().setText("Annotation");
      column.getColumn().setWidth(224);
      column.getColumn().setResizable(true);

      column.setLabelProvider(new ColumnLabelProvider()
      {
         @Override
         public String getText(Object element)
         {
            return ((AnnotationFilterItem) element).getName();
         }
      });
      column.getColumn().addSelectionListener(
            getSelectionAdapter(column.getColumn(), 1));
      column.setEditingSupport(new StatusEditingSupport(viewer));

      viewer.getTable().setSortColumn(column.getColumn());

      return column;
   }


   private TableViewerColumn createThresholdColumn()
   {
      TableViewerColumn column = new TableViewerColumn(viewer, SWT.CENTER);

      column.getColumn().setText("Threshold");
      column.getColumn().setWidth(65);
      column.getColumn().setResizable(true);

      column.setLabelProvider(new CellLabelProvider()
      {
         @Override
         public void update(ViewerCell cell)
         {
            cell.setText(String.valueOf(((AnnotationFilterItem) cell
                  .getElement()).getThreshold()));
         }
      });
      column.setEditingSupport(new ThresholdEditingSupport(viewer));
      column.getColumn().addSelectionListener(
            getSelectionAdapter(column.getColumn(), 2));

      return column;
   }


   private TableViewerColumn createAvailableColumn()
   {
      final TableViewerColumn column = new TableViewerColumn(viewer, SWT.CENTER);

      column.getColumn().setText("");
      column.getColumn().setWidth(24);
      column.getColumn().setResizable(false);

      column.setLabelProvider(new ColumnLabelProvider()
      {
         @Override
         public String getText(Object element)
         {
            return null;
         }


         @Override
         public Image getImage(Object element)
         {
            if (((AnnotationFilterItem) element).isAvailable())
            {
               return InferenceUIPlugin.getInstance().getImage(
                     InferenceConstants.IMG_FILTER_AVAILABLE);
            }
            else
            {
               return InferenceUIPlugin.getInstance().getImage(
                     InferenceConstants.IMG_FILTER_UNAVAILABLE);
            }
         }
      });
      column.setEditingSupport(new StatusEditingSupport(viewer));
      column.getColumn().addSelectionListener(
            getSelectionAdapter(column.getColumn(), 3));

      return column;
   }


   private SelectionAdapter getSelectionAdapter(final TableColumn column,
         final int index)
   {
      SelectionAdapter adapter = new SelectionAdapter()
      {

         @Override
         public void widgetSelected(SelectionEvent e)
         {
            comparator.setColumn(index);
            int dir = viewer.getTable().getSortDirection();

            if (viewer.getTable().getSortColumn() == column)
            {
               dir = dir == SWT.UP ? SWT.DOWN : SWT.UP;
            }
            else
            {
               dir = SWT.DOWN;
            }

            viewer.getTable().setSortDirection(dir);
            viewer.getTable().setSortColumn(column);
            viewer.refresh();
         }
      };

      return adapter;
   }
}
