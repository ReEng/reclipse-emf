package org.reclipse.structure.specification.ui.editor;


import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.fujaba.commons.editor.AbstractSimpleEditorPart;
import org.fujaba.commons.editor.NestedEObjectEditorInput;
import org.fujaba.commons.notation.HierarchicalNode;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.ui.PSImages;
import org.reclipse.structure.specification.ui.edit.parts.PSEditPartFactory;



/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSCatalogEditorPage extends AbstractSimpleEditorPart implements
      IEditingDomainProvider
{
   public final static String ID = "org.reclipse.structure.specification.ui.PSCatalogEditor";

   private MultiPagePSCatalogEditor parentEditor;


   /**
    * Default constructor.
    */
   public PSCatalogEditorPage(MultiPagePSCatalogEditor parentEditor)
   {
      super();
      this.parentEditor = parentEditor;
   }


   @Override
   protected void performSaveAs(IFile diagramFile)
   {
	   parentEditor.save();
   }


   @Override
   protected void setInput(IEditorInput input)
   {
      setInputWithNotify(input);
      if (input instanceof NestedEObjectEditorInput)
      {
         // get model
         EObject model = ((NestedEObjectEditorInput) input).getModel();

         // go through the diagram resource
         for (EObject content : parentEditor.getDiagramResource().getContents())
         {
            // check the type
            if (content instanceof HierarchicalNode
                  && model.equals(((HierarchicalNode) content).getModel()))
            {
               setDiagram((HierarchicalNode) content);
               return;
            }
         }
      }
   }


   @Override
   protected void createEditPartFactory()
   {
      this.editPartFactory = new PSEditPartFactory();
   }


   @Override
   protected Image createEditorImage()
   {
      return PSImages.getImage(PSImages.IMAGE_OBJ_PATTERN_RULE_16);
   }


   @Override
   public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter)
   {
      if (adapter.equals(IEditingDomainProvider.class))
      {
         return this;
      }
      return super.getAdapter(adapter);
   }


   @Override
   public CommandStack getCommandStack()
   {
      return parentEditor.getCommandStack();
   }


   /**
    * Customized, such that we can use the generated EMF.edit item providers and do not need to care
    * for manual property specification.
    * 
    * @see org.fujaba.commons.editor.AbstractSimpleEditorPart#getPropertySheetPage()
    */
   @Override
   protected PropertySheetPage getPropertySheetPage()
   {
      return this.parentEditor.getPropertySheetPage();
   }


   @Override
   public EditingDomain getEditingDomain()
   {
      return parentEditor.getEditingDomain();
   }


   @Override
   public PSPatternSpecification getDiagramModel()
   {
      return (PSPatternSpecification) super.getDiagramModel();
   }
}
