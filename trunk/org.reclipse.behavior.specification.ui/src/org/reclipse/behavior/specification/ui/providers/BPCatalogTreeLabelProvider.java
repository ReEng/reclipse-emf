package org.reclipse.behavior.specification.ui.providers;


import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Image;
import org.reclipse.behavior.specification.BPCatalog;
import org.reclipse.behavior.specification.BehavioralPattern;
import org.reclipse.behavior.specification.ui.util.BPComposedAdapterFactory;



/**
 * 
 */

/**
 * @author mcp
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
public class BPCatalogTreeLabelProvider extends AdapterFactoryLabelProvider
      implements ILabelProvider
{

   public BPCatalogTreeLabelProvider()
   {
      super(BPComposedAdapterFactory.getAdapterFactory());
   }


   public Image getImage(Object element)
   {
      return super.getImage(element);
   }


   public String getText(Object element)
   {
      if (element instanceof BPCatalog)
      {
         BPCatalog catalog = (BPCatalog) element;
         return "Catalog: " + catalog.getName();
      }
      else if (element instanceof BehavioralPattern)
      {
         BehavioralPattern pattern = (BehavioralPattern) element;
         return "Behavioral Pattern: " + pattern.getName();
      }
      return super.getText(element);
   }
}
