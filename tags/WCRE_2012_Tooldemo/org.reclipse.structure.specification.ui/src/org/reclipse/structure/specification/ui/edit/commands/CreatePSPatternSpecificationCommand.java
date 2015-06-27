/**
 * 
 */
package org.reclipse.structure.specification.ui.edit.commands;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gef.commands.Command;
import org.fujaba.commons.notation.HierarchicalNode;
import org.fujaba.commons.notation.NotationFactory;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSCatalog;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.SpecificationFactory;
import org.reclipse.structure.specification.ui.utils.PSConstants;


/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 *
 */
public class CreatePSPatternSpecificationCommand extends Command
{
   private PSCatalog catalog;
   private Resource diagramResource;
   private String name;
   
   private PSPatternSpecification newPattern;
   private PSAnnotation newAnnotation;
   private HierarchicalNode newPatternView;
   private HierarchicalNode newAnnotationView;
   
   
   /**
    * @param catalog the PSCatalog that will contain the pattern specification
    * @param diagramResource Resource that will contain the diagram
    */
   public CreatePSPatternSpecificationCommand(PSCatalog catalog, Resource diagramResource, String initialName)
   {
      super("create new pattern specification");
      this.catalog = catalog;
      this.diagramResource = diagramResource;
      this.name = initialName == null? "New Pattern" : initialName;
   }


   @Override
   public void execute()
   {
      redo();
   }

   @Override
   public void redo()
   {
      
      // create model level
      if(newPattern == null)
      {
         newPattern = SpecificationFactory.eINSTANCE.createPSPatternSpecification();
      }
      newPattern.setName(name);
      newPattern.setCatalog(catalog);

      if(newAnnotation == null)
      {
         newAnnotation = SpecificationFactory.eINSTANCE.createPSAnnotation();
      }
      newAnnotation.setPatternSpecification(newPattern);
      newAnnotation.setType(newPattern);
      newAnnotation.setWeight(PSConstants.DEFAULT_WEIGHT);


      // create view level
      if(newPatternView == null)
      {
         newPatternView = NotationFactory.eINSTANCE.createHierarchicalNode();
      }
      newPatternView.setModel(newPattern);

      if(newAnnotationView == null)
      {
         newAnnotationView = NotationFactory.eINSTANCE.createHierarchicalNode();
      }
      newAnnotationView.setModel(newAnnotation);
      newAnnotationView.setPersistent(true);
      newAnnotationView.setVisible(true);
      newAnnotationView.setHeight(80);
      newAnnotationView.setWidth(180);
      newAnnotationView.setX(60);
      newAnnotationView.setY(40);
      newAnnotationView.setParent(newPatternView);
      
      diagramResource.getContents().add(newPatternView);
   }

   @Override
   public void undo()
   {
      catalog.getPatternSpecifications().remove(newPattern);
      diagramResource.getContents().remove(newPatternView);
   }

}
