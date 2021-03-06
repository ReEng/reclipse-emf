package org.reclipse.structure.specification.ui.editor;


import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.fujaba.commons.editor.overviewpage.AbstractOverviewPage;
import org.fujaba.commons.editor.overviewpage.TextSectionPart;
import org.fujaba.commons.identifier.IdentifierPackage;



/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSCatalogOverviewPage extends AbstractOverviewPage
{
   public PSCatalogOverviewPage(MultiPagePSCatalogEditor mpEditor)
   {
      super(mpEditor);
   }


   @Override
   protected void createFormContent(IManagedForm managedForm)
   {
      // get some
      ScrolledForm form = managedForm.getForm();
      FormToolkit toolkit = managedForm.getToolkit();

      // set form title
      form.setText(getTitle());
      toolkit.decorateFormHeading(form.getForm());

      // prepare form body
      GridLayoutFactory.fillDefaults().margins(6, 6).applyTo(form.getBody());

      // create name section
      TextSectionPart nameSectionPart = new TextSectionPart(editor,
            form.getBody(), toolkit, "Name",
            IdentifierPackage.Literals.IDENTIFIER__NAME);

      // set layout for it
      GridDataFactory.fillDefaults().grab(true, false)
            .applyTo(nameSectionPart.getSection());

      // add it
      managedForm.addPart(nameSectionPart);

      // add pattern view
      PatternSpecificationSectionPart patternsPart = new PatternSpecificationSectionPart(
            getEditor(), managedForm);
      GridDataFactory.fillDefaults().grab(true, true)
            .applyTo(patternsPart.getSection());

      managedForm.addPart(patternsPart);

      managedForm.setInput(getEditor().getCatalog());
   }


   @Override
   protected void createNestedDiagramsSectionPart(IManagedForm managedForm,
         ScrolledForm form, FormToolkit toolkit)
   {
      // already added
   }


   @Override
   public MultiPagePSCatalogEditor getEditor()
   {
      return (MultiPagePSCatalogEditor) super.getEditor();
   }
}
