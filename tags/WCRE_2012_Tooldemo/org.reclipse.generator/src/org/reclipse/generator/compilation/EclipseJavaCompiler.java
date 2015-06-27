package org.reclipse.generator.compilation;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.reclipse.generator.GeneratorConstants;
import org.reclipse.generator.generation.AbstractGenerator;
import org.reclipse.generator.generation.FileInformation;
import org.reclipse.generator.generation.JavaFileInformation;


/**
 * @author mm
 * @author Last editor: $Author: oleg82 $
 * @version $Revision: 4520 $ $Date: 2010-08-26 19:56:11 +0200 (Do, 26 Aug 2010) $
 */
public class EclipseJavaCompiler extends JavaCompiler
{

   private static final String TMP_PROJECT_NAME = "org.reclipse.generator.temporaryproject";

   private IProject tmpProject;

   private IJavaProject tmpJavaProject;

   private IPackageFragmentRoot rootPackage;

   private IFolder binFolder;

   private HashSet<IPackageFragment> createdPackages = new HashSet<IPackageFragment>();

   private boolean autoBuild = false;

   private boolean removeProject;

   private boolean debugInfo;

   private int codeComplianceLevel;

   private AbstractGenerator outputProvider;

   Collection<String> classPaths, pluginDependencies;


   public EclipseJavaCompiler(boolean removeProject,
         Collection<String> classPaths, Collection<String> pluginDependencies,
         boolean debugInfo, int codeComplianceLevel)
   {
      this.removeProject = removeProject;
      this.debugInfo = debugInfo;
      this.codeComplianceLevel = codeComplianceLevel;
      this.classPaths = classPaths;
      this.pluginDependencies = pluginDependencies;
   }


   private IPackageFragmentRoot getSourceFolder() throws CoreException
   {
      IFolder srcFolder = this.tmpProject.getFolder("src");
      IPackageFragmentRoot root = this.tmpJavaProject
            .getPackageFragmentRoot(srcFolder);
      return root;
   }


   private void createTemporaryProject() throws CoreException
   {
      // delete an already existing project
      deleteTemporaryProject();

      // create temporary project
      this.tmpProject = PluginProjectCreationHelper.createProject(
            TMP_PROJECT_NAME, pluginDependencies
                  .toArray(new String[pluginDependencies.size()]),
            getCodeComplianceLevel());

      // get/create Java project
      this.tmpJavaProject = JavaCore.create(this.tmpProject);
      // get bin folder
      this.binFolder = this.tmpProject.getFolder("bin");
      // create root package
      this.rootPackage = getSourceFolder();

      initClassPath();
   }


   private void initClassPath() throws JavaModelException, CoreException
   {
      // define array for new entries
      IClasspathEntry[] newEntries = new IClasspathEntry[this.classPaths.size()];
      // add paths from preferences
      int i = 0;
      Iterator<String> pathIter = this.classPaths.iterator();
      while (pathIter.hasNext())
      {
         String tmpClasspath = pathIter.next();
         if (tmpClasspath.endsWith(".jar") || tmpClasspath.endsWith(".zip"))
         {
            newEntries[i] = JavaCore.newLibraryEntry(new Path(tmpClasspath),
                  null, null);
         }
         else
         {
            // entry is an external folder - thus it has to be linked into the project first
            String name = tmpClasspath.replace(':', '_').replace(
                  File.separatorChar, '_');
            IFolder folderLink = this.tmpProject.getFolder(name);
            folderLink.createLink(new Path(tmpClasspath),
                  IResource.ALLOW_MISSING_LOCAL, null);
            newEntries[i] = JavaCore.newLibraryEntry(folderLink.getFullPath(),
                  null, null);
         }
         i++;
      }
      // set class path on project
      IClasspathEntry[] existing = this.tmpJavaProject.getRawClasspath();
      IClasspathEntry[] newClassPathEntries = new IClasspathEntry[existing.length
            + newEntries.length];
      for (int j = 0; j < newClassPathEntries.length; j++)
      {
         if (j < existing.length)
         {
            newClassPathEntries[j] = existing[j];
         }
         else
         {
            newClassPathEntries[j] = newEntries[j - existing.length];
         }
      }
      this.tmpJavaProject.setRawClasspath(newClassPathEntries, null);
   }


   private void deleteTemporaryProject() throws CoreException
   {
      if (this.tmpJavaProject != null)
      {
         this.tmpJavaProject.close();
         this.tmpJavaProject = null;
      }

      if (this.tmpProject != null)
      {
         this.tmpProject.close(null);
         this.tmpProject.delete(true, true, null);
         this.tmpProject = null;
      }
      else
      {
         IProject prj = ResourcesPlugin.getWorkspace().getRoot().getProject(
               TMP_PROJECT_NAME);
         if (prj.exists())
         {
            prj.delete(true, true, null);
         }
      }
   }


   private void deactivateAutoBuilding() throws CoreException
   {
      IWorkspace wspace = ResourcesPlugin.getWorkspace();
      IWorkspaceDescription wsDescription = wspace.getDescription();
      this.autoBuild = wsDescription.isAutoBuilding();
      if (this.autoBuild)
      {
         wsDescription.setAutoBuilding(false);
         wspace.setDescription(wsDescription);
      }
   }


   private void restoreAutoBuilding()
   {
      IWorkspace wspace = ResourcesPlugin.getWorkspace();
      IWorkspaceDescription wsDescription = wspace.getDescription();
      if (this.autoBuild != wsDescription.isAutoBuilding())
      {
         wsDescription.setAutoBuilding(this.autoBuild);
         try
         {
            wspace.setDescription(wsDescription);
         }
         catch (CoreException e)
         {
            e.printStackTrace();
            this.outputProvider
                  .append("Restoring setting \"Project -> Build Automatically\" failed:");
            this.outputProvider.append("  " + e.getMessage());
         }
      }
   }


   private void initialize() throws CoreException
   {
      // deactivate auto building
      deactivateAutoBuilding();

      this.outputProvider.append("Creating temporary project...");
      createTemporaryProject();
   }


   /**
    * @see org.reclipse.generator.compilation.JavaCompiler#compileJavaFiles(org.reclipse.generator.OutputProvider,
    *      java.util.Set)
    */
   @Override
   public void compileJavaFiles(AbstractGenerator outputProvider,
         Set<JavaFileInformation> javaFiles) throws Exception
   {
      this.outputProvider = outputProvider;
      initialize();

      // export given java files
      for (JavaFileInformation fileInfo : javaFiles)
      {
         IPackageFragment filePackage = this.rootPackage
               .getPackageFragment(fileInfo.getPackageName());
         if (!filePackage.exists())
         {
            filePackage = this.rootPackage.createPackageFragment(fileInfo
                  .getPackageName(), true, null);
            this.createdPackages.add(filePackage);
         }

         String fileName = fileInfo.getName();
         if (!fileName.endsWith(".java"))
         {
            fileName += ".java";
         }
         ICompilationUnit cu = filePackage.createCompilationUnit(fileName,
               fileInfo.getContent(), true, null);
         File exportedFile = ((IFile) cu.getCorrespondingResource())
               .getLocation().toFile();
         fileInfo.setFile(exportedFile);
      }

      configureDebugOptions();

      // build the files
      this.outputProvider.append("Starting project build...");
      this.tmpProject.build(IncrementalProjectBuilder.FULL_BUILD,
            new BuildProgressMonitor());
      this.outputProvider.append("Build finished.");

      // collect class files
      for (JavaFileInformation fileInfo : javaFiles)
      {
         Path path = new Path(fileInfo.getPackageName().replace('.',
               File.separatorChar));
         IFolder folder = this.binFolder.getFolder(path);

         String fileName = fileInfo.getName();
         if (fileName.endsWith(".java"))
         {
            int index = fileName.lastIndexOf(".java");
            fileName = fileName.substring(0, index);
         }
         fileName += ".class";
         IResource classFile = folder.findMember(fileName);
         if (classFile instanceof IFile)
         {
            fileInfo.setClassFile(((IFile) classFile).getLocation().toFile());
         }
         else
         {
            System.err.println("error, didn't generate file: " + fileName);
         }
      }
   }


   private void configureDebugOptions()
   {
      if (this.debugInfo)
      {
         this.tmpJavaProject.setOption(JavaCore.COMPILER_CODEGEN_UNUSED_LOCAL,
               JavaCore.PRESERVE);
         this.tmpJavaProject.setOption(JavaCore.COMPILER_LINE_NUMBER_ATTR,
               JavaCore.GENERATE);
         this.tmpJavaProject.setOption(JavaCore.COMPILER_LOCAL_VARIABLE_ATTR,
               JavaCore.GENERATE);
      }
   }


   private String getCodeComplianceLevel()
   {
      String compliance = null;
      switch (this.codeComplianceLevel)
      {
         case GeneratorConstants.JAVA_1_3:
            compliance = "1.3";
            break;
         case GeneratorConstants.JAVA_1_4:
            compliance = "1.4";
            break;
         case GeneratorConstants.JAVA_1_5:
            compliance = "1.5";
            break;
         case GeneratorConstants.JAVA_1_6:
            compliance = "1.6";
            break;
         default:
            compliance = "1.5";
            break;
      }
      return compliance;
   }


   /**
    * @see org.reclipse.generator.compilation.JavaCompiler#exportNonJavaFiles(java.util.Set)
    */
   @Override
   public void exportNonJavaFiles(Set<FileInformation> nonJavaFiles)
         throws Exception
   {
      for (FileInformation fileInfo : nonJavaFiles)
      {
         IFolder sourceFolder = (IFolder) this.rootPackage
               .getCorrespondingResource();
         IFile iFile = sourceFolder.getFile(fileInfo.getName());

         iFile.create(
               new ByteArrayInputStream(fileInfo.getContent().getBytes()),
               true, null);

         fileInfo.setFile(iFile.getLocation().toFile());
      }
   }


   /**
    * @see org.reclipse.generator.compilation.JavaCompiler#cleanUp()
    */
   @Override
   public void cleanUp()
   {
      if (this.removeProject)
      {
         // cleanup only if configured in preferences
         this.outputProvider.append("Removing temporary project...");

         try
         {
            deleteTemporaryProject();
         }
         catch (CoreException e)
         {
            this.outputProvider.append(e.toString());
            this.outputProvider.append(e.getLocalizedMessage());
         }
      }

      // restore auto building
      restoreAutoBuilding();
   }


   void appendOutput(String text)
   {
      this.outputProvider.append(text);
   }

   class BuildProgressMonitor extends NullProgressMonitor
   {
      /**
       * @see org.eclipse.core.runtime.IProgressMonitor#subTask(java.lang.String)
       */
      @Override
      public void subTask(String name)
      {
         appendOutput(name);
      }
   }

}
