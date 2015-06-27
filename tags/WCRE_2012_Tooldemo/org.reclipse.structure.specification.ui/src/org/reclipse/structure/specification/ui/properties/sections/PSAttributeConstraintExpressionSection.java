package org.reclipse.structure.specification.ui.properties.sections;


import java.io.StringReader;
import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.parser.IParseResult;
import org.fujaba.commons.properties.section.AbstractObjectSelectionComboSection;
import org.fujaba.commons.properties.util.ColorRegistry;
import org.reclipse.structure.specification.OperatorType;
import org.reclipse.structure.specification.PSAttributeConstraint;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.util.ModelHelper;
import org.storydriven.storydiagrams.expressions.common.parser.antlr.ExpressionsParser;
import org.storydriven.storydiagrams.expressions.common.ui.internal.ExpressionsActivator;

import com.google.inject.Injector;


public class PSAttributeConstraintExpressionSection extends
      AbstractObjectSelectionComboSection
{
   private Combo operatorCombo;

   private Text valueText;

   private Composite area;

   private ExpressionsParser parser;


   public PSAttributeConstraintExpressionSection()
   {
      parser = new ExpressionsParser();
      String INJECTOR_ID = "org.storydriven.modeling.expressions.common.Expressions"; //$NON-NLS-1$
      Injector injector = ExpressionsActivator.getInstance().getInjector(
            INJECTOR_ID);
      injector.injectMembers(parser);
   }


   @Override
   protected void createWidgets(Composite composite)
   {
      area = getWidgetFactory().createFlatFormComposite(composite);
      GridLayoutFactory.fillDefaults().numColumns(3).applyTo(area);

      // create combo
      list = new Combo(area, SWT.DROP_DOWN | SWT.READ_ONLY);
      GridDataFactory.fillDefaults().applyTo(list);

      // create label
      label = getWidgetFactory().createCLabel(composite, getLabelText(),
            SWT.TRAIL);

      operatorCombo = new Combo(area, SWT.DROP_DOWN | SWT.READ_ONLY);
      GridDataFactory.fillDefaults().applyTo(operatorCombo);

      valueText = getWidgetFactory()
            .createText(area, "", SWT.LEAD | SWT.BORDER);
      GridDataFactory.fillDefaults().grab(true, true).applyTo(valueText);
   }


   @Override
   protected void setSectionData(Composite composite)
   {
      FormData data = new FormData();
      data.left = new FormAttachment(0, getStandardLabelWidth(composite,
            new String[] { getLabelText() }));
      data.right = new FormAttachment(100, 0);
      data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
      data.bottom = new FormAttachment(100, 0);
      area.setLayoutData(data);

      data = new FormData();
      data.left = new FormAttachment(0, 0);
      data.right = new FormAttachment(area, -ITabbedPropertyConstants.HSPACE);
      data.top = new FormAttachment(area, 0, SWT.TOP);
      label.setLayoutData(data);
   }


   @Override
   public void refresh()
   {
      super.refresh();

      OperatorType[] array = OperatorType.values();
      for (int i = 0; i < array.length; i++)
      {
         if (array[i].equals(getElement().getOperator()))
         {
            operatorCombo.select(i);
            break;
         }
      }

      String text = getElement().getValueExpression();
      if (text == null)
      {
         text = ""; //$NON-NLS-1$
      }
      valueText.setText(text);
   }


   @Override
   public void setInput(IWorkbenchPart part, ISelection selection)
   {
      super.setInput(part, selection);

      // fill operators
      operatorCombo.setItems(getOperators());
   }


   @Override
   protected void hookListeners()
   {
      super.hookListeners();

      // create listener
      SelectionAdapter listener = new SelectionAdapter()
      {
         @Override
         public void widgetSelected(SelectionEvent e)
         {
            checkExpression();
         }
      };

      list.addSelectionListener(listener);
      operatorCombo.addSelectionListener(listener);

      valueText.addModifyListener(new ModifyListener()
      {
         @Override
         public void modifyText(ModifyEvent e)
         {
            checkExpression();
         }
      });
   }


   protected void checkExpression()
   {
      String operator = ModelHelper
            .getReadable(OperatorType.values()[operatorCombo
                  .getSelectionIndex()]);
      StringBuilder expression = new StringBuilder();

      expression.append(getElement().getNode().getName());
      expression.append(".");
      expression.append(getElement().getAttribute().getName());
      expression.append(operator);
      expression.append(valueText.getText());

      IParseResult result = parser
            .parse(new StringReader(expression.toString()));

      if (!result.hasSyntaxErrors())
      {
         CompoundCommand command = new CompoundCommand();

         // set value
         Object value = valueText.getText();
         Object feature = SpecificationPackage.Literals.PS_BOOLEAN_CONSTRAINT__VALUE_EXPRESSION;
         command.append(SetCommand.create(getEditingDomain(), getElement(),
               feature, value));

         // set operator
         value = OperatorType.values()[operatorCombo.getSelectionIndex()];
         feature = SpecificationPackage.Literals.PS_BOOLEAN_CONSTRAINT__OPERATOR;
         command.append(SetCommand.create(getEditingDomain(), getElement(),
               feature, value));

         // set full expression
         value = expression.toString();
         feature = SpecificationPackage.Literals.PS_NODE_CONSTRAINT__EXPRESSION;
         command.append(SetCommand.create(getEditingDomain(), getElement(),
               feature, value));

         getEditingDomain().getCommandStack().execute(command);

         setErrorMessage(null);
         valueText.setBackground(null);
      }
      else
      {
         for (INode err : result.getSyntaxErrors())
         {
            setErrorMessage("Syntax Error: " + err.getText());
            valueText.setBackground(ColorRegistry.COLOR_ERROR);
         }
      }
   }


   private String[] getOperators()
   {
      String[] names = new String[OperatorType.values().length];
      int i = 0;
      for (OperatorType op : OperatorType.values())
      {
         names[i] = ModelHelper.getReadable(op);
         i++;
      }

      return names;
   }


   @Override
   protected EAttribute getCurrentValue()
   {
      return getElement().getAttribute();
   }


   @Override
   protected PSAttributeConstraint getElement()
   {
      return (PSAttributeConstraint) super.getElement();
   }


   @Override
   protected EStructuralFeature getFeature()
   {
      return SpecificationPackage.Literals.PS_ATTRIBUTE_CONSTRAINT__ATTRIBUTE;
   }


   @Override
   protected String getLabelText()
   {
      return "Expression";
   }


   @Override
   protected List<EAttribute> getPossibilities()
   {
      return ModelHelper.getAllValidAttributes((PSObject) getElement()
            .getNode());
   }


   @Override
   protected String getValueText(Object element)
   {
      // get element
      EAttribute attribute = (EAttribute) element;

      // create builder
      StringBuilder name = new StringBuilder();

      name.append(attribute.getName());

      // add type when existing
      if (attribute.getEAttributeType() != null)
      {
         name.append(": "); //$NON-NLS-1$
         name.append(attribute.getEAttributeType().getName());
      }

      return name.toString();
   }
}
