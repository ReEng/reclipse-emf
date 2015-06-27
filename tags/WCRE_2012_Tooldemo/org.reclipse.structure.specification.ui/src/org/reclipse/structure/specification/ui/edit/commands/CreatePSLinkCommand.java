package org.reclipse.structure.specification.ui.edit.commands;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.ui.IEditorPart;
import org.fujaba.commons.edit.parts.AbstractDiagramEditPart;
import org.fujaba.commons.notation.Node;
import org.reclipse.structure.inference.annotations.AnnotationsPackage;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSLink;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSObject;
import org.reclipse.structure.specification.SpecificationFactory;
import org.reclipse.structure.specification.ui.utils.PSConstants;
import org.reclipse.structure.specification.util.ModelHelper;



/**
 * This command is used to create a new {@link PSLink} between two elements.
 * 
 * @author mm
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class CreatePSLinkCommand extends AbstractCreatePSConnectionCommand
{

   private ResourceSet resourceSet;


   /**
    * Default constructor.
    * 
    * @param source The source (view) node from which the link starts.
    */
   public CreatePSLinkCommand(Node source, AbstractDiagramEditPart host)
   {
      super("create link", source);
      // cache resource set
      IEditorPart editor = (((DefaultEditDomain) host.getViewer()
            .getEditDomain()).getEditorPart());
      Object provider = editor.getAdapter(IEditingDomainProvider.class);
      if (provider != null && provider instanceof IEditingDomainProvider)
      {
         resourceSet = ((IEditingDomainProvider) provider).getEditingDomain()
               .getResourceSet();
      }
   }


   @Override
   protected PSLink getModel()
   {
      if (modelElement == null)
      {
         PSLink link = SpecificationFactory.eINSTANCE.createPSLink();

         link.setName(PSConstants.DEFAULT__MODEL_NAME_PREFIX__LINK
               + (getNumberOfPaths(false) + 1));
         link.setWeight(PSConstants.DEFAULT_WEIGHT);

         // set qualifier
         link.setQualifier(getNextQualifier());

         // set name for create annotation
         if (modelSource instanceof PSAnnotation
               && ModelHelper.isCreate((PSAnnotation) modelSource))
         {
            link.setName(PSConstants.ANNOTATED_ELEMENT);
         }

         modelElement = link;
      }

      return (PSLink) modelElement;
   }


   private String getNextQualifier()
   {
      // get source
      EObject source = getSource().getModel();

      if (source instanceof PSAnnotation)
      {
         if (ModelHelper.isCreate((PSAnnotation) source))
         {
            StringBuilder qualifier = new StringBuilder();

            qualifier.append("element");
            int count = ((PSNode) source).getOutgoing().size() + 1;
            qualifier.append(count);

            return qualifier.toString();
         }
      }

      return null;
   }


   @Override
   protected void redoModel()
   {
      super.redoModel();

      EReference inst = ModelHelper.getFirstReference(getModel().getSource(),
            getModel().getTarget());
      if (inst != null)
      {
         getModel().setInstanceOf(inst);
      }

      // set instance of the link to annotatedElement
      if (getModel().getSource() instanceof PSAnnotation)
      {
         getModel().setInstanceOf(
               AnnotationsPackage.eINSTANCE
                     .getASGAnnotation_AnnotatedElements());
      }

      if (getModel().getSource() instanceof PSAnnotation
            && getModel().getTarget() instanceof PSObject)
      {
         PSAnnotation anno = (PSAnnotation) getModel().getSource();

         // only set qualifier on non-creating annotation
         if (!ModelHelper.isCreate(anno))
         {
            getModel().setQualifier(
                  ModelHelper.getFirstQualifier(anno, (PSObject) getModel()
                        .getTarget()));
         }
      }
   }


   public ResourceSet getResourceSet()
   {
      return this.resourceSet;
   }
}
