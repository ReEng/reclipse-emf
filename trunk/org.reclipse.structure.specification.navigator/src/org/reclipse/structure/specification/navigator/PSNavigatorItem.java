package org.reclipse.structure.specification.navigator;


import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.swt.graphics.Image;

import org.fujaba.commons.notation.HierarchicalNode;


/**
 * This class represents all elements of a {@link org.reclipse.structure.specification.PSCatalog
 * PSCatalog} which are used in the navigation in the project explorer.
 * 
 * @author harka
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 */
public class PSNavigatorItem extends PlatformObject
{

   /**
    * The adapter registration has to be done statically so that the navigator contributions can be
    * created.
    */
   static
   {
      Platform.getAdapterManager().registerAdapters(new IAdapterFactory()
      {
         @Override
         public Object getAdapter(Object object,
               @SuppressWarnings("rawtypes") Class type)
         {
            if (object instanceof PSNavigatorItem && type == EObject.class)
            {
               return ((PSNavigatorItem) object).getItem();
            }
            return null;
         }


         @Override
         public Class<?>[] getAdapterList()
         {
            return new Class[] { EObject.class };
         }
      }, PSNavigatorItem.class);
   }

   private Image image;

   private Object parent;

   private HierarchicalNode item;

   private Set<PSNavigatorItem> children;


   /**
    * The default constructor.
    */
   public PSNavigatorItem(HierarchicalNode item, Object parent)
   {
      this.parent = parent;
      this.item = item;
   }


   /**
    * Adds the given child to this navigator item's children.
    * 
    * @param child The child to add.
    * @return Returns <code>true</code> if this item did not already contain the specified child.
    */
   public boolean addChild(PSNavigatorItem child)
   {
      if (children == null)
      {
         children = new HashSet<PSNavigatorItem>();
      }

      return children.add(child);
   }


   /**
    * Adds the given children to this navigator item's children.
    * 
    * @param children The children to add.
    * @return Returns <code>true</code> if this item's children has been changed.
    */
   public boolean addChildren(Collection<PSNavigatorItem> children)
   {
      if (children == null)
      {
         children = new HashSet<PSNavigatorItem>();
      }

      return children.addAll(children);
   }


   @Override
   public boolean equals(Object other)
   {
      if (other instanceof PSNavigatorItem)
      {
         return EcoreUtil.getURI(getItem()).equals(
               EcoreUtil.getURI(((PSNavigatorItem) other).getItem()));
      }

      return super.equals(other);
   }


   /**
    * Getter of the item's children.
    * 
    * @return Returns all the item's children.
    */
   public Set<PSNavigatorItem> getChildren()
   {
      if (children == null)
      {
         return Collections.emptySet();
      }

      return children;
   }


   /**
    * Getter of the item's image.
    * 
    * @return Returns an adequate image for the item.
    */
   public Image getImage()
   {
      if (image == null)
      {
         image = PSNavigatorPlugin.getInstance().getImage(
               PSNavigatorPlugin.IMG_PATTERN);
      }

      return image;
   }


   /**
    * Getter of the item.
    * 
    * @return Returns the item.
    */
   public HierarchicalNode getItem()
   {
      return item;
   }


   /**
    * Getter of the item's parent.
    * 
    * @return Returns the parent of the item.
    */
   public Object getParent()
   {
      return parent;
   }


   /**
    * Getter of the item's text representation.
    * 
    * @return Returns a text representation for the item.
    */
   public String getText()
   {
      if (item.getModel() instanceof ENamedElement)
      {
         return ((ENamedElement) item.getModel()).getName();
      }

      return "unknown";
   }


   @Override
   public int hashCode()
   {
      return EcoreUtil.getURI(getItem()).hashCode();
   }
}
