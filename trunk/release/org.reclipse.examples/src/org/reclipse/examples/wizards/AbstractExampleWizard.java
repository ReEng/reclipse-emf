package org.reclipse.examples.wizards;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.reclipse.examples.internal.Activator;


public abstract class AbstractExampleWizard extends Wizard implements
      INewWizard
{
   private static final String JAVA_NATURE = "org.eclipse.jdt.core.javanature"; //$NON-NLS-1$

   private static final String PLUGIN_NATURE = "org.eclipse.pde.PluginNature"; //$NON-NLS-1$


   /**
    * Renames the specified project to the specified name
    * 
    * @param project Project to rename
    * @param projectName New name for the project
    * @throws CoreException
    */
   private static void renameProject(IProject project, String projectName)
         throws CoreException
   {
      IProjectDescription description = project.getDescription();
      description.setName(projectName);
      project.move(description, IResource.FORCE | IResource.SHALLOW, null);
   }


   private WizardNewProjectCreationPage page;


   public AbstractExampleWizard()
   {
      super();

      // configure it
      ImageDescriptor imageDesc = Activator.getInstance().getImageDescriptor(
            Activator.IMG_WIZ_EXAMPLE);
      setDefaultPageImageDescriptor(imageDesc);
      setNeedsProgressMonitor(true);
      setWindowTitle(getExampleTitle());
   }


   @Override
   public void addPages()
   {
      super.addPages();

      // create the (single) page
      page = new WizardNewProjectCreationPage(
            WizardNewProjectCreationPage.class.getName());
      page.setTitle(getExampleTitle());
      page.setDescription(getExampleDescription());
      page.setInitialProjectName(getInitialProjectName());

      // add it
      addPage(page);
   }


   /**
    * Extracts the project archive to the specified folder.
    * 
    * @param projectFolderFile The folder where to unzip the project archive.
    * @param monitor Monitor to display progress and/or cancel operation.
    * @throws IOException Upon file read issues.
    * @throws InterruptedException On monitor canceling.
    */
   private void extractProject(File projectFolderFile, IProgressMonitor monitor)
         throws IOException,
            InterruptedException
   {
      // get project archive
      URL urlZip = FileLocator.find(Activator.getInstance().getBundle(),
            new Path(getZipFilePath()), null);
      URL urlZipLocal = FileLocator.toFileURL(urlZip);

      // walk each element and unzip
      ZipFile zipFile = new ZipFile(urlZipLocal.getPath());

      try
      {
         // allow for a hundred work units
         monitor.beginTask("Unzipping file", zipFile.size());

         unzip(zipFile, projectFolderFile, monitor);
      }
      finally
      {
         zipFile.close();
         monitor.done();
      }
   }


   protected abstract String getExampleDescription();


   protected abstract String getExampleTitle();


   protected String getInitialProjectName()
   {
      return "org.reclipse.example." + getExampleTitle();
   }


   protected abstract String getZipFilePath();


   @Override
   public void init(IWorkbench workbench, IStructuredSelection selection)
   {
      // nothing to do
   }


   @Override
   public boolean performFinish()
   {
      // collect page settings
      final String pName = page.getProjectName();
      final IPath pPath = page.getLocationPath();

      // create the operation
      WorkspaceModifyOperation operation = new WorkspaceModifyOperation()
      {
         @Override
         public void execute(IProgressMonitor monitor)
               throws InvocationTargetException,
                  InterruptedException
         {
            try
            {
               monitor.beginTask("Creating Project", 120);

               // create the project folder
               IPath projectPath = pPath;

               String projectName = pName;
               String projectFolder = projectPath.toOSString() + File.separator
                     + projectName;
               File projectFolderFile = new File(projectFolder);

               IWorkspace workspace = ResourcesPlugin.getWorkspace();
               IProject project = workspace.getRoot().getProject(projectName);

               // create and populate it
               if (!project.exists())
               {
                  projectFolderFile.mkdirs();
                  monitor.worked(10);

                  // copy plug-in project code
                  extractProject(projectFolderFile, new SubProgressMonitor(
                        monitor, 100));

                  if (monitor.isCanceled())
                  {
                     throw new InterruptedException();
                  }

                  if (projectPath.equals(workspace.getRoot().getLocation()))
                  {
                     project.create(monitor);
                  }
                  else
                  {
                     IProjectDescription desc = workspace
                           .newProjectDescription(project.getName());
                     desc.setLocation(new Path(projectFolder));

                     project.create(desc, monitor);
                  }
               }

               // ensure the project is open
               project.open(monitor);

               renameProject(project, projectName);

               // add Java and PDE natures
               IProjectDescription desc = workspace
                     .newProjectDescription(project.getName());
               desc.setNatureIds(new String[] { PLUGIN_NATURE, JAVA_NATURE });
               project.setDescription(desc, monitor);

               monitor.worked(10);
               if (monitor.isCanceled())
               {
                  throw new InterruptedException();
               }

            }
            catch (IOException e)
            {
               throw new RuntimeException(e);
            }
            catch (CoreException e)
            {
               throw new RuntimeException(e);
            }
            finally
            {
               monitor.done();
            }
         }
      };

      // run it
      try
      {
         getContainer().run(true, true, operation);
      }
      catch (InvocationTargetException e)
      {
         e.printStackTrace();
         return false;
      }
      catch (InterruptedException e)
      {
         e.printStackTrace();
         return false;
      }

      return true;
   }


   /**
    * Unzips the platform formatted zip file to specified folder
    * 
    * @param zipFile The platform formatted zip file
    * @param projectFolderFile The folder where to unzip the project archive
    * @param monitor Monitor to display progress and/or cancel operation
    * @throws IOException
    * @throws FileNotFoundException
    * @throws InterruptedException
    */
   private void unzip(ZipFile zipFile, File projectFolderFile,
         IProgressMonitor monitor)
         throws IOException,
            FileNotFoundException,
            InterruptedException
   {

      Enumeration<? extends ZipEntry> e = zipFile.entries();

      while (e.hasMoreElements())
      {
         ZipEntry zipEntry = (ZipEntry) e.nextElement();
         File file = new File(projectFolderFile, zipEntry.getName());

         if (false == zipEntry.isDirectory())
         {

            /*
             * Copy files (and make sure parent directory exist)
             */
            File parentFile = file.getParentFile();
            if (null != parentFile && false == parentFile.exists())
            {
               parentFile.mkdirs();
            }

            Path path = new Path(file.getPath());
            if (path.getFileExtension().equals("java")) { //$NON-NLS-1$
               InputStreamReader is = null;
               OutputStreamWriter os = null;

               try
               {
                  is = new InputStreamReader(zipFile.getInputStream(zipEntry),
                        "ISO-8859-1"); //$NON-NLS-1$
                  os = new OutputStreamWriter(new FileOutputStream(file),
                        ResourcesPlugin.getEncoding());
                  char[] buffer = new char[102400];
                  while (true)
                  {
                     int len = is.read(buffer);
                     if (len < 0)
                        break;
                     os.write(buffer, 0, len);
                  }
               }
               finally
               {
                  if (null != is)
                  {
                     is.close();
                  }
                  if (null != os)
                  {
                     os.close();
                  }
               }
            }
            else
            {
               InputStream is = null;
               OutputStream os = null;

               try
               {
                  is = zipFile.getInputStream(zipEntry);
                  os = new FileOutputStream(file);

                  byte[] buffer = new byte[102400];
                  while (true)
                  {
                     int len = is.read(buffer);
                     if (len < 0)
                        break;
                     os.write(buffer, 0, len);
                  }
               }
               finally
               {
                  if (null != is)
                  {
                     is.close();
                  }
                  if (null != os)
                  {
                     os.close();
                  }
               }
            }
         }

         monitor.worked(1);

         if (monitor.isCanceled())
         {
            throw new InterruptedException();
         }
      }
   }
}
