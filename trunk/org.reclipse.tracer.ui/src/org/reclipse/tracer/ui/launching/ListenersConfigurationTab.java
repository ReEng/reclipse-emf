package org.reclipse.tracer.ui.launching;


import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.reclipse.tracer.ITracerConstants;
import org.reclipse.tracer.ui.TracerUIImages;


/**
 * @author Lothar
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3786 $ $Date: 2007-09-09 23:44:20 +0200 (So, 09 Sep 2007) $
 */
public class ListenersConfigurationTab extends AbstractLaunchConfigurationTab
      implements SelectionListener, ICheckStateListener
{

   private static final String KEY_COLUMN = "keyColumn";

   private static final String VALUE_COLUMN = "valueColumn";


   private Map<String, MonitoredEventsListenerClass> listenerClasses;


   private CheckboxTableViewer listenersTableViewer;

   private TableViewer propertiesTableViewer;

   private Label propertiesLabel;


   public ListenersConfigurationTab()
   {
      initializeListenerClasses();
   }


   /**
    * @see org.eclipse.debug.ui.ILaunchConfigurationTab#createControl(org.eclipse.swt.widgets.Composite)
    */
   public void createControl(final Composite parent)
   {
      final GridLayout gridLayout = new GridLayout();
      gridLayout.numColumns = 1;

      final Composite composite = new Composite(parent, SWT.NONE);
      composite.setLayout(gridLayout);
      setControl(composite);

      GridData gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.verticalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      gridData.grabExcessVerticalSpace = true;
      composite.setLayoutData(gridData);

      final Label label = new Label(composite, SWT.LEFT);
      label.setText("Available Tracer Listeners:");
      gridData = new GridData();
      gridData.horizontalAlignment = SWT.LEFT;
      label.setLayoutData(gridData);

      createListenersTable(composite);

      this.propertiesLabel = new Label(composite, SWT.LEFT);
      this.propertiesLabel.setText("Properties:");
      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      this.propertiesLabel.setLayoutData(gridData);

      createPropertiesTable(composite);

      Dialog.applyDialogFont(parent);
   }


   private void createListenersTable(final Composite composite)
   {
      this.listenersTableViewer = CheckboxTableViewer.newCheckList(composite,
            SWT.H_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
      this.listenersTableViewer
            .setContentProvider(new ListenersTableContentProvider());
      this.listenersTableViewer
            .setLabelProvider(new ListenersTableLabelProvider());
      this.listenersTableViewer.addCheckStateListener(this);

      final Table table = this.listenersTableViewer.getTable();
      table.setLinesVisible(false);
      table.addSelectionListener(this);

      final GridData gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.verticalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      table.setLayoutData(gridData);

      final TableColumn column = new TableColumn(table, SWT.NONE);

      this.listenersTableViewer.setInput(this.listenerClasses.values());
      this.listenersTableViewer.refresh();
      column.pack();
   }


   private void createPropertiesTable(final Composite composite)
   {
      this.propertiesTableViewer = new TableViewer(composite, SWT.H_SCROLL
            | SWT.BORDER | SWT.FULL_SELECTION);
      this.propertiesTableViewer
            .setContentProvider(new PropertiesTableContentProvider());
      this.propertiesTableViewer
            .setLabelProvider(new PropertiesTableLabelProvider());
      this.propertiesTableViewer.setColumnProperties(new String[] { KEY_COLUMN,
            VALUE_COLUMN });
      this.propertiesTableViewer.setCellEditors(new CellEditor[] { null,
            new TextCellEditor(this.propertiesTableViewer.getTable()) });
      this.propertiesTableViewer
            .setCellModifier(new PropertiesTableCellModifier(
                  this.propertiesTableViewer));

      final Table table = this.propertiesTableViewer.getTable();
      table.setHeaderVisible(true);
      table.setLinesVisible(true);

      final GridData gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.verticalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      gridData.grabExcessVerticalSpace = true;
      table.setLayoutData(gridData);

      TableColumn column = new TableColumn(table, SWT.NONE);
      column.setText("Key");
      column.setWidth(150);

      column = new TableColumn(table, SWT.NONE);
      column.setText("Value");
      column.setWidth(250);
   }


   /**
    * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
    */
   public void widgetSelected(final SelectionEvent e)
   {
      final Table table = this.listenersTableViewer.getTable();
      final int selectedIndex = table.getSelectionIndex();

      Object selectedObject = null;
      if (selectedIndex >= 0)
      {
         selectedObject = this.listenersTableViewer.getElementAt(selectedIndex);
      }

      if (selectedObject != null)
      {
         final MonitoredEventsListenerClass listenerClass = (MonitoredEventsListenerClass) selectedObject;
         this.propertiesLabel.setText("Properties of "
               + listenerClass.getName() + ":");
      }
      else
      {
         this.propertiesLabel.setText("Properties:");
      }

      this.propertiesTableViewer.setInput(selectedObject);
      this.propertiesTableViewer.refresh();
   }


   /**
    * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
    */
   public void widgetDefaultSelected(final SelectionEvent e)
   {
   }


   /**
    * @see org.eclipse.jface.viewers.ICheckStateListener#checkStateChanged(org.eclipse.jface.viewers.CheckStateChangedEvent)
    */
   public void checkStateChanged(final CheckStateChangedEvent event)
   {
      final MonitoredEventsListenerClass listenerClass = (MonitoredEventsListenerClass) event
            .getElement();
      listenerClass.setEnabled(event.getChecked());

      update();
   }


   private void initializeListenerClasses()
   {
      this.listenerClasses = new HashMap<String, MonitoredEventsListenerClass>();

      final IExtension[] extensions = Platform.getExtensionRegistry()
            .getExtensionPoint(ITracerConstants.PLUGIN_ID,
                  ITracerConstants.MONITORED_EVENTS_LISTENERS_EXTENSION)
            .getExtensions();

      for (final IExtension extension : extensions)
      {
         final IConfigurationElement[] configElements = extension
               .getConfigurationElements();
         for (final IConfigurationElement configElement : configElements)
         {
            if (ITracerConstants.MONITORED_EVENTS_LISTENER_ELEMENT
                  .equals(configElement.getName()))
            {
               final boolean optional = Boolean.valueOf(configElement
                     .getAttribute(ITracerConstants.OPTIONAL_ATTRIBUTE));

               if (optional)
               {
                  final MonitoredEventsListenerClass listenerClass = new MonitoredEventsListenerClass();
                  listenerClass.setClassName(configElement
                        .getAttribute(ITracerConstants.CLASS_ATTRIBUTE));
                  listenerClass.setName(configElement
                        .getAttribute(ITracerConstants.NAME_ATTRIBUTE));
                  listenerClass.setEnabled(Boolean.valueOf(configElement
                        .getAttribute(ITracerConstants.ENABLED_ATTRIBUTE)));

                  final IConfigurationElement[] children = configElement
                        .getChildren(ITracerConstants.PROPERTY_KEY_ELEMENT);
                  for (final IConfigurationElement propertyKey : children)
                  {
                     final MonitoredEventsListenerProperty property = new MonitoredEventsListenerProperty();
                     property.setKey(propertyKey
                           .getAttribute(ITracerConstants.NAME_ATTRIBUTE));
                     property.setType(propertyKey
                           .getAttribute(ITracerConstants.TYPE_ATTRIBUTE));

                     final String defaultValue = propertyKey
                           .getAttribute(ITracerConstants.DEFAULT_VALUE_ATTRIBUTE);
                     if (defaultValue != null)
                     {
                        property.setValue(defaultValue);
                        property.setDefaultValue(defaultValue);
                     }
                     else
                     {
                        property.setValue("");
                        property.setDefaultValue("");
                     }

                     listenerClass.addProperty(property);
                  }
                  this.listenerClasses.put(listenerClass.getClassName(),
                        listenerClass);
               }
            }
         }
      }
   }


   /**
    * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getName()
    */
   public String getName()
   {
      return "Listeners";
   }


   /**
    * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getImage()
    */
   @Override
   public Image getImage()
   {
      return TracerUIImages.getImage(TracerUIImages.IMG_TRACER_LISTENERS);
   }


   /**
    * @see org.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(org.eclipse.debug.core.ILaunchConfiguration)
    */
   public void initializeFrom(final ILaunchConfiguration configuration)
   {
      for (final MonitoredEventsListenerClass listenerClass : this.listenerClasses
            .values())
      {
         try
         {
            listenerClass.setEnabled(configuration.getAttribute(listenerClass
                  .getClassName(), listenerClass.isEnabled()));
         }
         catch (final CoreException e)
         {
         }

         try
         {
            final Map<String, String> properties = configuration.getAttribute(
                  listenerClass.getClassName() + ITracerConstants.PROPERTIES,
                  new HashMap<String, String>());

            for (final String propertyKey : properties.keySet())
            {
               final MonitoredEventsListenerProperty property = listenerClass
                     .getProperty(propertyKey);
               if (property != null)
               {
                  property.setValue(properties.get(propertyKey));
               }
            }
         }
         catch (final CoreException e)
         {
         }
      }

      updateEnabledListeners();
   }


   /**
    * @see org.eclipse.debug.ui.ILaunchConfigurationTab#performApply(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
    */
   public void performApply(final ILaunchConfigurationWorkingCopy configuration)
   {
      for (final MonitoredEventsListenerClass listenerClass : this.listenerClasses
            .values())
      {
         configuration.setAttribute(listenerClass.getClassName(), listenerClass
               .isEnabled());

         final Map<String, String> properties = new HashMap<String, String>();
         for (final MonitoredEventsListenerProperty property : listenerClass
               .getProperties())
         {
            properties.put(property.getKey(), property.getValue());
         }
         configuration.setAttribute(listenerClass.getClassName()
               + ITracerConstants.PROPERTIES, properties);
      }
   }


   /**
    * @see org.eclipse.debug.ui.ILaunchConfigurationTab#setDefaults(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
    */
   public void setDefaults(final ILaunchConfigurationWorkingCopy configuration)
   {
      for (final MonitoredEventsListenerClass listenerClass : this.listenerClasses
            .values())
      {
         configuration.setAttribute(listenerClass.getClassName(), listenerClass
               .isEnabled());

         final Map<String, String> properties = new HashMap<String, String>();
         for (final MonitoredEventsListenerProperty property : listenerClass
               .getProperties())
         {
            properties.put(property.getKey(), property.getDefaultValue());
         }
         configuration.setAttribute(listenerClass.getClassName()
               + ITracerConstants.PROPERTIES, properties);
      }
   }


   /**
    * @see org.eclipse.debug.ui.AbstractLaunchConfigurationTab#isValid(org.eclipse.debug.core.ILaunchConfiguration)
    */
   @Override
   public boolean isValid(final ILaunchConfiguration configuration)
   {
      for (final MonitoredEventsListenerClass listenerClass : this.listenerClasses
            .values())
      {
         if (listenerClass.isEnabled())
         {
            for (final MonitoredEventsListenerProperty property : listenerClass
                  .getProperties())
            {
               if (property.getValue() == null
                     || property.getValue().trim().equals(""))
               {
                  setErrorMessage("Please provide values for all properties of enabled listeners.");
                  return false;
               }
            }
         }
      }

      setErrorMessage(null);
      return true;
   }


   private void updateEnabledListeners()
   {
      final Set<MonitoredEventsListenerClass> checkedListenerClasses = new HashSet<MonitoredEventsListenerClass>();
      for (final MonitoredEventsListenerClass listenerClass : this.listenerClasses
            .values())
      {
         if (listenerClass.isEnabled())
         {
            checkedListenerClasses.add(listenerClass);
         }
      }

      this.listenersTableViewer.setCheckedElements(checkedListenerClasses
            .toArray());
   }


   /* package */void update()
   {
      updateLaunchConfigurationDialog();
   }


   static class ListenersTableContentProvider implements
         IStructuredContentProvider
   {

      /**
       * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
       */
      public Object[] getElements(final Object inputElement)
      {
         final Collection<MonitoredEventsListenerClass> listeners = (Collection<MonitoredEventsListenerClass>) inputElement;
         return listeners.toArray();
      }


      /**
       * @see org.eclipse.jface.viewers.IContentProvider#dispose()
       */
      public void dispose()
      {
      }


      /**
       * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
       *      java.lang.Object, java.lang.Object)
       */
      public void inputChanged(final Viewer viewer, final Object oldInput,
            final Object newInput)
      {
      }

   }


   static class ListenersTableLabelProvider implements ITableLabelProvider
   {

      /**
       * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
       */
      public Image getColumnImage(final Object element, final int columnIndex)
      {
         return null;
      }


      /**
       * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
       */
      public String getColumnText(final Object element, final int columnIndex)
      {
         final MonitoredEventsListenerClass listenerClass = (MonitoredEventsListenerClass) element;
         if (columnIndex == 0)
         {
            return listenerClass.getName();
         }

         return null;
      }


      /**
       * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
       */
      public void addListener(final ILabelProviderListener listener)
      {
      }


      /**
       * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
       */
      public void dispose()
      {
      }


      /**
       * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object,
       *      java.lang.String)
       */
      public boolean isLabelProperty(final Object element, final String property)
      {
         return false;
      }


      /**
       * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
       */
      public void removeListener(final ILabelProviderListener listener)
      {
      }

   }


   static class PropertiesTableContentProvider implements
         IStructuredContentProvider
   {

      /**
       * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
       */
      public Object[] getElements(final Object inputElement)
      {
         final MonitoredEventsListenerClass listenerClass = (MonitoredEventsListenerClass) inputElement;
         return listenerClass.getProperties().toArray();
      }


      /**
       * @see org.eclipse.jface.viewers.IContentProvider#dispose()
       */
      public void dispose()
      {
      }


      /**
       * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
       *      java.lang.Object, java.lang.Object)
       */
      public void inputChanged(final Viewer viewer, final Object oldInput,
            final Object newInput)
      {
      }

   }


   static class PropertiesTableLabelProvider implements ITableLabelProvider
   {

      /**
       * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
       */
      public Image getColumnImage(final Object element, final int columnIndex)
      {
         return null;
      }


      /**
       * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
       */
      public String getColumnText(final Object element, final int columnIndex)
      {
         final MonitoredEventsListenerProperty property = (MonitoredEventsListenerProperty) element;
         if (columnIndex == 0)
         {
            if (property.getType() != null)
            {
               return property.getKey() + " (" + property.getType() + ")";
            }
            return property.getKey();
         }
         else if (columnIndex == 1)
         {
            return property.getValue();
         }
         return null;
      }


      /**
       * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
       */
      public void addListener(final ILabelProviderListener listener)
      {
      }


      /**
       * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
       */
      public void dispose()
      {
      }


      /**
       * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object,
       *      java.lang.String)
       */
      public boolean isLabelProperty(final Object element, final String property)
      {
         return false;
      }


      /**
       * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
       */
      public void removeListener(final ILabelProviderListener listener)
      {
      }

   }


   class PropertiesTableCellModifier implements ICellModifier
   {

      private final TableViewer tableViewer;


      public PropertiesTableCellModifier(final TableViewer tableViewer)
      {
         this.tableViewer = tableViewer;
      }


      /**
       * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
       */
      public boolean canModify(final Object element, final String property)
      {
         if (VALUE_COLUMN.equals(property))
         {
            return true;
         }
         return false;
      }


      /**
       * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object, java.lang.String)
       */
      public Object getValue(final Object element, final String property)
      {
         if (VALUE_COLUMN.equals(property))
         {
            final MonitoredEventsListenerProperty methodCallListenerProperty = (MonitoredEventsListenerProperty) element;
            return methodCallListenerProperty.getValue();
         }
         return null;
      }


      /**
       * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object, java.lang.String,
       *      java.lang.Object)
       */
      public void modify(final Object element, final String property,
            final Object value)
      {
         if (VALUE_COLUMN.equals(property) && value != null)
         {
            final String newValue = (String) value;
            final TableItem tableItem = (TableItem) element;
            final MonitoredEventsListenerProperty methodCallListenerProperty = (MonitoredEventsListenerProperty) tableItem
                  .getData();
            methodCallListenerProperty.setValue(newValue);
            this.tableViewer.refresh(methodCallListenerProperty);
            update();
         }
      }

   }

}
