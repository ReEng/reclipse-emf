package org.reclipse.behavior.specification.ui.util;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.views.properties.IPropertySource;


public class BPCatalogContentProvider extends
      AdapterFactoryContentProvider
{

   public BPCatalogContentProvider(AdapterFactory adapterFactory)
   {
      super(adapterFactory);
   }

   @Override
   public IPropertySource getPropertySource(Object object)
   {
      if(object instanceof AbstractGraphicalEditPart)
      {
         Object model = ((AbstractGraphicalEditPart)object).getModel();
         if(model instanceof View)
         {
            return super.getPropertySource(((View)((AbstractGraphicalEditPart)object).getModel()).getElement());
         }
         return super.getPropertySource(((AbstractGraphicalEditPart)object).getModel());
      }
      return super.getPropertySource(object);
   }
}
