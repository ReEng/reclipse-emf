/**
 * 
 */
package org.reclipse.structure.specification.ui.editor;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.jface.viewers.TreeNodeContentProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.fujaba.commons.editor.overviewpage.NestedDiagramsTreeSectionPart;
import org.fujaba.commons.identifier.IdentifierPackage;
import org.fujaba.commons.properties.TreeNodeLabelProvider;
import org.reclipse.structure.specification.PSAnnotation;
import org.reclipse.structure.specification.PSCatalog;
import org.reclipse.structure.specification.PSNode;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.SpecificationPackage;
import org.reclipse.structure.specification.ui.edit.commands.CreatePSPatternSpecificationCommand;
import org.reclipse.structure.specification.ui.edit.commands.DeletePSPatternSpecificationCommand;


/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 *
 */
public class PatternSpecificationSectionPart extends NestedDiagramsTreeSectionPart
{
   private PSCatalog catalog;

   private PatternSpecificationSectionPart(MultiPagePSCatalogEditor editor,
         Composite parent, FormToolkit toolkit)
   {
      super(editor, parent, toolkit);

      // set section data
      getSection().setText("Pattern Specifications contained in this catalog");
   }


   public PatternSpecificationSectionPart(MultiPagePSCatalogEditor editor,
         IManagedForm mForm)
   {
      this(editor, mForm.getForm().getBody(), mForm.getToolkit());
   }


   @Override
   protected boolean canRemove(EObject[] selected)
   {
      // check if it has super patterns
      for (int i = 0; i < selected.length; i++)
      {
         if(selected[i] instanceof PSPatternSpecification)
         {
            PSPatternSpecification selectedPattern = (PSPatternSpecification) selected[i];
            if (!selectedPattern.getSubPatterns().isEmpty())
            {
               return false;
            }
      
            // check if it is referenced anywhere
            for (PSPatternSpecification other : selectedPattern.getCatalog()
                  .getPatternSpecifications())
            {
               // don't check itself
               if (!other.equals(selected))
               {
                  // go through all nodes
                  for (PSNode node : other.getNodes())
                  {
                     // only check annotations
                     if (node instanceof PSAnnotation
                           && selected.equals(((PSAnnotation) node).getType()))
                     {
                        return false;
                     }
                  }
               }
            }
         }
      }

      return true;
   }

   
   @Override
   protected Command createAddDiagramCommand(String newName)
   {
      return new CreatePSPatternSpecificationCommand(catalog, editor.getDiagramResource(), newName);
   }


   @Override
   protected Command createDeleteDiagramCommandFor(EObject diagramRoot)
   {
      if(diagramRoot instanceof PSPatternSpecification)
      {
         return new DeletePSPatternSpecificationCommand((PSPatternSpecification)diagramRoot, this.editor.getDiagramResource());
      }
      
      return null;
   }


   @Override
   protected TreeNode[] getInput()
   {
      // create root node list
      List<TreeNode> roots = new ArrayList<TreeNode>();

      // go through the patterns
      for (PSPatternSpecification pattern : catalog.getPatternSpecifications())
      {
         // check that we get the 'root' patterns
         if (pattern.getSuperPattern() == null)
         {
            // add the pattern node
            roots.add(createNode(pattern, null));
         }
      }

      // set settings
      return roots.toArray(new TreeNode[roots.size()]);
   }


   protected void handleModelChanged(Notification msg)
   {
      // get feature
      Object feature = msg.getFeature();
      if (feature != null)
      {
         // check for changing feature
         if (feature
               .equals(SpecificationPackage.Literals.PS_CATALOG__PATTERN_SPECIFICATIONS)
               || feature
                     .equals(SpecificationPackage.Literals.PS_PATTERN_SPECIFICATION__ABSTRACT)
               || feature
                     .equals(SpecificationPackage.Literals.PS_PATTERN_SPECIFICATION__SUB_PATTERNS)
               || feature.equals(IdentifierPackage.Literals.IDENTIFIER__NAME))
         {
            refresh();
         }
      }
   }


   @Override
   public boolean setFormInput(Object input)
   {
      if (input instanceof PSCatalog)
      {
         // remove existing listeners
         if (catalog != null)
         {
            // remove from catalog
            catalog.eAdapters().remove(this);

            // remove from patterns
            for (PSPatternSpecification pattern : catalog.getPatternSpecifications())
            {
               pattern.eAdapters().remove(this);
            }
         }

         // set new model
         catalog = (PSCatalog) input;

         // add listeners
         if (!catalog.eAdapters().contains(this))
         {
            // add to catalog
            catalog.eAdapters().add(this);
         }
         // add to patterns
         for (PSPatternSpecification pattern : catalog.getPatternSpecifications())
         {
            if (!pattern.eAdapters().contains(this))
            {
               pattern.eAdapters().add(this);
            }
         }

         return true;
      }

      return super.setFormInput(input);
   }


   @Override
   protected TreeNode createNode(EObject type, TreeNode parent)
   {
      assert type instanceof PSPatternSpecification;
      PSPatternSpecification pattern = (PSPatternSpecification) type;
      // create node
      TreeNode node = new TreeNode(type);

      // set the nodes parent
      node.setParent(parent);

      // get children
      List<PSPatternSpecification> children = pattern.getSubPatterns();

      // create children array
      TreeNode[] childrenNodes = new TreeNode[children.size()];

      // collect the children
      int i = 0;
      for (PSPatternSpecification child : children)
      {
         childrenNodes[i] = createNode(child, node);
         i++;
      }

      // set the children
      node.setChildren(childrenNodes);

      return node;
   }


   @Override
   protected IBaseLabelProvider getLabelProvider()
   {
      return new TreeNodeLabelProvider(((MultiPagePSCatalogEditor)editor).getEditingDomain().getAdapterFactory());
   }


   @Override
   protected IContentProvider getContentProvider()
   {
      return new TreeNodeContentProvider();
   }


   @Override
   protected void openDiagramFor(EObject diagramRoot)
   {
      assert diagramRoot instanceof PSPatternSpecification;
      ((MultiPagePSCatalogEditor)editor).addPageFor((PSPatternSpecification) diagramRoot);
   }
}
