package org.reclipse.structure.specification.ui.properties.sections;


import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
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
import org.reclipse.structure.specification.PSConnection;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.util.ModelHelper;


public class PSLinkQualifierSection extends AbstractSection
{

   private CLabel label;

   private Text normal;

   private Combo referenced;

   private Combo inherited;

   private TextChangeListener listener;


   @Override
   protected void createWidgets(Composite parent)
   {
      // create label
      label = getWidgetFactory()
            .createCLabel(parent, getLabelText(), SWT.TRAIL);
      label.setToolTipText("This qualified String will be used to qualify the annotated element.");

      // create simple text field
      normal = getWidgetFactory().createText(parent, "", SWT.SINGLE | SWT.LEAD);
      normal.setVisible(false);

      // create read-only combo
      referenced = new Combo(parent, SWT.READ_ONLY | SWT.DROP_DOWN);
      referenced.setVisible(false);

      // create writable combo
      inherited = new Combo(parent, SWT.DROP_DOWN);
      inherited.setVisible(false);
   }


   @Override
   protected PSLink getElement()
   {
      return (PSLink) super.getElement();
   }


   @Override
   protected EStructuralFeature getFeature()
   {
      return SpecificationPackage.Literals.PS_LINK__QUALIFIER;
   }


   private String[] getItems()
   {
      List<String> list = ModelHelper.getAllValidQualifiers(getElement());
      return list.toArray(new String[list.size()]);
   }


   @Override
   protected String getLabelText()
   {
      return "Qualifier";
   }


   private void handleTextModified()
   {
      if (isTextValid())
      {
         createCommand(getElement().getQualifier(), normal.getText());
      }
      else
      {
         refresh();
      }
   }


   @Override
   protected void hookListeners()
   {
      // add listener to the static combo
      referenced.addSelectionListener(new SelectionAdapter()
      {
         @Override
         public void widgetSelected(SelectionEvent e)
         {
            normal.setText(referenced.getText());
            handleTextModified();
         }
      });

      // add listener to the writable combo
      inherited.addSelectionListener(new SelectionAdapter()
      {
         @Override
         public void widgetSelected(SelectionEvent e)
         {
            normal.setText(inherited.getText());
            handleTextModified();
         }
      });
      inherited.addFocusListener(new FocusAdapter()
      {
         @Override
         public void focusLost(FocusEvent e)
         {
            normal.setText(inherited.getText());
            handleTextModified();
         }
      });
      inherited.addKeyListener(new KeyAdapter()
      {
         @Override
         public void keyPressed(KeyEvent e)
         {
            if (e.character == SWT.CR)
            {
               normal.setText(inherited.getText());
               handleTextModified();
            }
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

      listener.startListeningTo(normal);

      normal.addListener(SWT.Modify, new Listener()
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
      // get pattern
      PSPatternSpecification pattern = getElement().getPatternSpecification();

      // get new text
      String qualifier = normal.getText();

      // check if it is a link from the create annotation
      if (ModelHelper.getCreateAnnotation(pattern).equals(
            getElement().getSource()))
      {
         // go through the nodes
         for (PSConnection conn : pattern.getConnections())
         {
            // check if it is an other create link
            if (conn instanceof PSLink && ModelHelper.isCreate((PSLink) conn)
                  && !conn.equals(getElement()))
            {
               // check if the link is a create link
               if (qualifier.equals(((PSLink) conn).getQualifier()))
               {
                  return false;
               }
            }
         }
      }

      return true;
   }


   @Override
   public void refresh()
   {
      // get qualifier
      String qualifier = getElement().getQualifier();
      if (qualifier == null)
      {
         qualifier = ""; //$NON-NLS-1$
      }

      // set text
      normal.setText(qualifier);

      // select in static combo
      referenced.select(referenced.indexOf(qualifier));

      // select in writable combo
      inherited.select(inherited.indexOf(qualifier));

      // make the right one visible
      if (ModelHelper.isCreate(getElement()))
      {
         if (getElement().getPatternSpecification().getSuperPattern() == null
               || getItems().length < 1)
         {
            // normal
            normal.setVisible(true);
            inherited.setVisible(false);
            referenced.setVisible(false);
         }
         else
         {
            // inherited
            normal.setVisible(false);
            inherited.setVisible(true);
            referenced.setVisible(false);
         }
      }
      else
      {
         // referenced
         normal.setVisible(false);
         inherited.setVisible(false);
         referenced.setVisible(true);
      }
   }


   @Override
   public void setInput(IWorkbenchPart part, ISelection selection)
   {
      super.setInput(part, selection);

      // fill the combos
      String[] items = getItems();
      referenced.setItems(items);
      inherited.setItems(items);
   }


   @Override
   protected void setSectionData(Composite composite)
   {
      // normal text field
      FormData data = new FormData();
      data.left = new FormAttachment(0, getStandardLabelWidth(composite,
            new String[] { getLabelText() }));
      data.right = new FormAttachment(100, 0);
      data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
      data.bottom = new FormAttachment(100, 0);
      normal.setLayoutData(data);

      // static combo
      data = new FormData();
      data.left = new FormAttachment(0, getStandardLabelWidth(composite,
            new String[] { getLabelText() }));
      data.right = new FormAttachment(100, 0);
      data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
      data.bottom = new FormAttachment(100, 0);
      referenced.setLayoutData(data);

      // writable combo
      data = new FormData();
      data.left = new FormAttachment(0, getStandardLabelWidth(composite,
            new String[] { getLabelText() }));
      data.right = new FormAttachment(100, 0);
      data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
      data.bottom = new FormAttachment(100, 0);
      inherited.setLayoutData(data);

      // label
      data = new FormData();
      data.left = new FormAttachment(0, 0);
      data.right = new FormAttachment(normal, -ITabbedPropertyConstants.HSPACE);
      data.top = new FormAttachment(normal, 0, SWT.TOP);
      label.setLayoutData(data);
   }


   private void verifyField(Event e)
   {
      if (isTextValid())
      {
         setErrorMessage(null);
         normal.setBackground(null);
         e.doit = true;
      }
      else
      {
         setErrorMessage("The qualifier has to be unique.");
         normal.setBackground(ColorRegistry.COLOR_ERROR);
         e.doit = false;
      }
   }
}
