package org.reclipse.structure.specification.ui.provider;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Image;
import org.fujaba.commons.notation.HierarchicalNode;
import org.reclipse.structure.specification.ui.utils.PSComposedAdapterFactory;


/**
 * 
 */

/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 *
 */
public class CatalogTreeLabelProvider extends AdapterFactoryLabelProvider implements ILabelProvider
{

   public CatalogTreeLabelProvider()
   {
      super(PSComposedAdapterFactory.getAdapterFactory());
   }

   public Image getImage(Object element) 
   {
       return super.getImage(element);
   }

   public String getText(Object element) 
   {
       if(element instanceof HierarchicalNode)
       {
          HierarchicalNode node = (HierarchicalNode) element;
          if(node.getModel() instanceof ENamedElement)
          {
             return "Pattern: " + ((ENamedElement) node.getModel()).getName();
          }
       }
       return super.getText(element);
   }
}
