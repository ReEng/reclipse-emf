/**
 * 
 */
package org.reclipse.structure.specification.ui.utils;

import java.util.HashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 *
 */
public class IFile2EditingDomainMapping
{
   private static HashMap<String, EditingDomain> mapping = new HashMap<String, EditingDomain>();
   
   public static void register(IFile file, EditingDomain domain)
   {
      String path = file.getFullPath().toString();
      if(path != null && domain != null)
      {
         mapping.put(path, domain);
      }
      else
      {
         throw new IllegalArgumentException("Argument must not be null");
      }
   }
   
   public static EditingDomain getDomain(IFile file)
   {
      if(file == null)
      {
         return null;
      }
      String path = file.getFullPath().toString();
      return mapping.get(path);
   }
}
