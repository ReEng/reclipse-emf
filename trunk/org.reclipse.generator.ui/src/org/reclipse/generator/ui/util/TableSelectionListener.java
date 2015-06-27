/**
 * 
 */
package org.reclipse.generator.ui.util;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;

/**
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 *
 */
public class TableSelectionListener extends SelectionAdapter
{
   private Table table;

   private Button removeButton;


   public TableSelectionListener(Table table, Button button)
   {
      this.table = table;
      this.removeButton = button;
   }


   /**
    * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
    */
   @Override
   public void widgetSelected(SelectionEvent e)
   {
      this.removeButton.setEnabled(this.table.getSelectionCount() > 0);
   }
}
