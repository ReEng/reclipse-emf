package org.reclipse.tracedefinition.editor;


import java.io.File;
import java.io.FileNotFoundException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.reclipse.tracer.model.definition.TraceDefinition;
import org.reclipse.tracer.model.definition.xml.TraceDefinitionReader;


/**
 * @author lowende
 * @author Last editor: $Author: lowende $
 * @version $Revision: 3784 $ $Date: 2007-09-09 23:41:26 +0200 (So, 09 Sep 2007) $
 */
public class TraceDefinitionEditor extends EditorPart
{

   TreeViewer treeViewer;

   TraceDefinition traceDefinition;


   /**
    * @see org.eclipse.ui.IEditorPart#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
    */
   @Override
   public void init(final IEditorSite site, final IEditorInput editorInput)
         throws PartInitException
   {
      if (!(editorInput instanceof IFileEditorInput))
      {
         throw new PartInitException(
               "Invalid Input: Must be instance of IFileEditorInput");
      }
      setSite(site);
      setInput(editorInput);
   }


   /**
    * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
    */
   @Override
   public void createPartControl(final Composite parent)
   {
      this.treeViewer = new TreeViewer(parent, SWT.SINGLE);
      this.treeViewer.setContentProvider(new TraceDefinitionContentProvider());
      this.treeViewer.setLabelProvider(new TraceDefinitionLabelProvider());
      this.treeViewer.getTree().getDisplay().asyncExec(new Runnable()
      {
         /**
          * @see java.lang.Runnable#run()
          */
         public void run()
         {
            TraceDefinitionEditor.this.treeViewer
                  .setInput(TraceDefinitionEditor.this.traceDefinition);
         }
      });
   }


   /**
    * @see org.eclipse.ui.part.EditorPart#isDirty()
    */
   @Override
   public boolean isDirty()
   {
      return false;
   }


   /**
    * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
    */
   @Override
   public void setFocus()
   {
      this.treeViewer.getControl().setFocus();
   }


   /**
    * @see org.eclipse.ui.ISaveablePart#doSave(org.eclipse.core.runtime.IProgressMonitor)
    */
   @Override
   public void doSave(final IProgressMonitor monitor)
   {
   }


   /**
    * @see org.eclipse.ui.ISaveablePart#doSaveAs()
    */
   @Override
   public void doSaveAs()
   {
      updateTitle();
   }


   /**
    * @see org.eclipse.ui.ISaveablePart#isSaveAsAllowed()
    */
   @Override
   public boolean isSaveAsAllowed()
   {
      return true;
   }


   /**
    * @see org.eclipse.ui.part.EditorPart#setInput(org.eclipse.ui.IEditorInput)
    */
   @Override
   protected void setInput(final IEditorInput input)
   {
      if (!(input instanceof IFileEditorInput))
      {
         throw new IllegalArgumentException(
               "Argument 'input' must be instance of IFileEditorInput!");
      }

      super.setInput(input);
      try
      {
         final IFileEditorInput fileInput = (IFileEditorInput) input;
         final File file = fileInput.getFile().getLocation().toFile();
         this.traceDefinition = TraceDefinitionReader.load(file);
         updateTitle();
      }
      catch (final FileNotFoundException exception)
      {
         MessageDialog.openError(getSite().getShell(),
               "Error opening trace definition",
               "Could not open trace definition: " + exception.getMessage());
      }
   }


   private void updateTitle()
   {
      final IEditorInput input = getEditorInput();
      setPartName(input.getName());
      setTitleToolTip(input.getToolTipText());
   }

}
