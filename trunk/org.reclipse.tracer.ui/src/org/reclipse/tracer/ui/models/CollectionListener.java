package org.reclipse.tracer.ui.models;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 2357 $ $Date: 2006-06-01 14:24:13 +0200 (Do, 01 Jun 2006) $
 */
public interface CollectionListener
{

   public void elementAdded(String collectionName, Object value);


   public void allElementsRemoved(String collectionName);

}
