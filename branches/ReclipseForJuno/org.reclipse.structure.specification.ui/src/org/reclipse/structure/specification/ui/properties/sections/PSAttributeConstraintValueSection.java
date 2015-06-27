package org.reclipse.structure.specification.ui.properties.sections;


import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.fujaba.commons.properties.section.AbstractSection;
import org.fujaba.commons.properties.util.ColorRegistry;
import org.fujaba.commons.properties.util.TextChangeListener;
import org.reclipse.structure.specification.PSAttributeConstraint;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.util.ModelHelper;


public class PSAttributeConstraintValueSection extends AbstractSection
{
   private CLabel label;

   private Text text;

   private Combo combo;

   private TextChangeListener listener;


   @Override
   protected void createWidgets(Composite parent)
   {
      // create label
      label = getWidgetFactory()
            .createCLabel(parent, getLabelText(), SWT.TRAIL);

      // create text
      text = getWidgetFactory().createText(parent, "", SWT.LEAD | SWT.SINGLE);
      text.setVisible(false);

      // create combo
      combo = new Combo(parent, SWT.DROP_DOWN | SWT.READ_ONLY);
      combo.setVisible(false);
   }


   @Override
   protected PSAttributeConstraint getElement()
   {
      return (PSAttributeConstraint) super.getElement();
   }


   @Override
   protected EStructuralFeature getFeature()
   {
      return SpecificationPackage.Literals.PS_BOOLEAN_CONSTRAINT__VALUE_EXPRESSION;
   }


   private String[] getItems()
   {
      List<String> list = ModelHelper.getAllValidValues(getElement());
      return list.toArray(new String[list.size()]);
   }


   @Override
   protected String getLabelText()
   {
      return "Value";
   }


   @Override
   protected void handleModelChanged(Notification msg)
   {
      if (msg.getFeature() != null
            && msg.getFeature()
                  .equals(
                        SpecificationPackage.Literals.PS_ATTRIBUTE_CONSTRAINT__ATTRIBUTE))
      {
         combo.setItems(getItems());
         refresh();
      }

      super.handleModelChanged(msg);
   }


   private void handleTextModified()
   {
      createCommand(getElement().getValueExpression(), text.getText());
   }


   @Override
   protected void hookListeners()
   {
      // add selection listener
      combo.addSelectionListener(new SelectionAdapter()
      {
         @Override
         public void widgetSelected(SelectionEvent e)
         {
            text.setText(combo.getText());
            handleTextModified();
         }
      });

      // add some listeners to the text widget
      listener = new TextChangeListener()
      {
         @Override
         public void textChanged(Control control)
         {
            handleTextModified();
         }
      };

      listener.startListeningTo(text);

      text.addListener(SWT.Modify, new Listener()
      {
         @Override
         public void handleEvent(Event e)
         {
            verifyField(e);
         }
      });
   }


   private boolean isTextValid()
   {
      // get new text
      String value = text.getText();

      // check for each type
      if (isBoolean(getElement().getAttribute()))
      {
         return "true".equals(value) || "false".equals(value); //$NON-NLS-1$ //$NON-NLS-2$
      }
      else if (isInteger(getElement().getAttribute()))
      {
         try
         {
            Integer.parseInt(value);
         }
         catch (NumberFormatException e)
         {
            return false;
         }
      }
      else if (isDouble(getElement().getAttribute()))
      {
         try
         {
            Double.parseDouble(value);
         }
         catch (NumberFormatException e)
         {
            return false;
         }
      }
      else if (isEnum(getElement().getAttribute()))
      {
         EEnum enumType = (EEnum) getElement().getAttribute()
               .getEAttributeType();
         for (EEnumLiteral literal : enumType.getELiterals())
         {
            // check the literal
            if (literal.getLiteral().equals(value)
                  || literal.getName().equals(value))
            {
               return true;
            }
         }

         // false otherwise
         return false;
      }

      return true;
   }


   private void verifyField(Event e)
   {
      if (isTextValid())
      {
         setErrorMessage(null);
         text.setBackground(null);
         e.doit = true;
      }
      else
      {
         setErrorMessage("The value is not valid for the attribute.");
         text.setBackground(ColorRegistry.COLOR_ERROR);
         e.doit = false;
      }
   }


   @Override
   public void refresh()
   {
      // get value
      String value = getElement().getValueExpression();
      if (value == null)
      {
         value = ""; //$NON-NLS-1$
      }

      // set text
      text.setText(value);

      // select in static combo
      combo.select(combo.indexOf(value));

      // make the right one visible
      if (isEnum(getElement().getAttribute())
            || isBoolean(getElement().getAttribute()))
      {
         // combo
         text.setVisible(false);
         combo.setVisible(true);
      }
      else
      {
         // text
         text.setVisible(true);
         combo.setVisible(false);
      }
   }


   @Override
   public void setInput(IWorkbenchPart part, ISelection selection)
   {
      super.setInput(part, selection);

      // fill the combo
      combo.setItems(getItems());
   }


   @Override
   protected void setSectionData(Composite parent)
   {
      // text
      FormData data = new FormData();
      data.left = new FormAttachment(0, getStandardLabelWidth(parent,
            new String[] { getLabelText() }));
      data.right = new FormAttachment(100, 0);
      data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
      data.bottom = new FormAttachment(100, 0);
      text.setLayoutData(data);

      // combo
      data = new FormData();
      data.left = new FormAttachment(0, getStandardLabelWidth(parent,
            new String[] { getLabelText() }));
      data.right = new FormAttachment(100, 0);
      data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
      data.bottom = new FormAttachment(100, 0);
      combo.setLayoutData(data);

      // label
      data = new FormData();
      data.left = new FormAttachment(0, 0);
      data.right = new FormAttachment(text, -ITabbedPropertyConstants.HSPACE);
      data.top = new FormAttachment(text, 0, SWT.TOP);
      label.setLayoutData(data);
   }
}
