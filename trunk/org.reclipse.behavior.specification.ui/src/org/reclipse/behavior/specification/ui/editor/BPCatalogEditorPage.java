package org.reclipse.behavior.specification.ui.editor;


import org.eclipse.core.resources.IFile;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.fujaba.commons.FujabaCommonsImages;
import org.fujaba.commons.editor.AbstractSimpleEditorPart;
import org.fujaba.commons.editor.NestedEObjectEditorInput;
import org.reclipse.behavior.specification.BehavioralPattern;
import org.reclipse.behavior.specification.ui.BPPlugin;
import org.reclipse.behavior.specification.ui.editparts.BPEditPartFactory;



/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class BPCatalogEditorPage extends AbstractSimpleEditorPart implements
      IEditingDomainProvider
{
   public final static String ID = "org.reclipse.structure.specification.ui.PSCatalogEditor";

   private MultiPageBPCatalogEditor parentEditor;


   /**
    * Default constructor.
    */
   public BPCatalogEditorPage(MultiPageBPCatalogEditor parentEditor)
   {
      super();
      this.parentEditor = parentEditor;
   }


   @Override
   protected void performSaveAs(IFile diagramFile)
   {

   }


   @Override
   protected void setInput(IEditorInput input)
   {
      setInputWithNotify(input);
      if (input instanceof NestedEObjectEditorInput)
      {
         NestedEObjectEditorInput pseInput = (NestedEObjectEditorInput) input;
         BehavioralPattern pattern = (BehavioralPattern) pseInput.getModel();
         if (this.getGraphicalViewer() != null)
         {
            this.getGraphicalViewer().setContents(pattern);
         }
      }

      setPartName(input.getName());
   }


   @Override
   protected void createEditPartFactory()
   {
      this.editPartFactory = new BPEditPartFactory();
   }


   @Override
   protected Image createEditorImage()
   {
      ImageDescriptor descriptor = FujabaCommonsImages.getOrCreateDescriptor(
            BPPlugin.ID, "icons/obj/behavioralPattern.png");

      return descriptor.createImage();
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
   public BehavioralPattern getDiagramModel()
   {
      return (BehavioralPattern) super.getDiagramModel();
   }


   @Override
   protected void configureGraphicalViewer()
   {
      super.configureGraphicalViewer();
      // fixme: contents have to be set again because the behavioral pattern is not stored in
      // this.diagram because it is no HierarchicalNode.
      this.getGraphicalViewer().setContents(
            ((NestedEObjectEditorInput) this.getEditorInput()).getModel());

   }

}
