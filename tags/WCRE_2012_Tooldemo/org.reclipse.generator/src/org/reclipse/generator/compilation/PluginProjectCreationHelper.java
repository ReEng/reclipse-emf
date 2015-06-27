/**
 * 
 */
package org.reclipse.generator.compilation;


import java.io.ByteArrayInputStream;
import java.util.Hashtable;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.environments.IExecutionEnvironment;
import org.eclipse.jdt.launching.environments.IExecutionEnvironmentsManager;


/**
 * This class is a result out of several internal eclipse pde classes, which are necessary to create
 * a plug-in project. As long as these classes are not part of the eclipse interface(it is planned
 * to be) this helper class will do this for us. Please, don't make this class accessible to others.
 * This class is highly dependent of how Eclipse works internally and therefore must be customized in
 * future releases.
 * 
 * 
 * @author Oleg
 * @author Last editor: $Author$
 * @version $Revision$ $Date$
 * 
 */
class PluginProjectCreationHelper
{
   private static Hashtable<String, Integer> fSeverityTable = null;

   private static final int SEVERITY_ERROR = 3;

   private static final int SEVERITY_WARNING = 2;

   private static final int SEVERITY_IGNORE = 1;

   private static final IPath REQUIRED_PLUGINS_CONTAINER_PATH = new Path(
         "org.eclipse.pde.core.requiredPlugins");

   private static final String PLUGIN_NATURE = "org.eclipse.pde.PluginNature";


   static IProject createProject(String projectname,
         String[] pluginDependencies, String executionEnvironment)
         throws CoreException,
            JavaModelException
   {
      IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(
            projectname);
      project.create(null);
      project.open(null);

      if (executionEnvironment == null)
      {
         executionEnvironment = "1.5";
      }

      addNatureToProject(project, PLUGIN_NATURE, null); // was PDE.PLUGIN_NATURE before
      setupJava(project, executionEnvironment);
      IFolder metaFolder = project.getFolder("META-INF");
      metaFolder.create(true, true, null);
      createManifest(metaFolder, projectname, pluginDependencies);
      createBuildProperties(project);
      return project;
   }


   private static void addNatureToProject(IProject proj, String natureId,
         IProgressMonitor monitor) throws CoreException
   {
      IProjectDescription description = proj.getDescription();
      String[] prevNatures = description.getNatureIds();
      String[] newNatures = new String[prevNatures.length + 1];
      System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
      newNatures[prevNatures.length] = natureId;
      description.setNatureIds(newNatures);
      proj.setDescription(description, monitor);
   }


   private static void setupJava(IProject project, String executionEnvironment)
         throws CoreException,
            JavaModelException
   {
      addNatureToProject(project, JavaCore.NATURE_ID, null);
      IFolder srcFolder = project.getFolder("src");
      srcFolder.create(true, true, null);
      IFolder binFolder = project.getFolder("bin");
      if (!binFolder.exists())
      {
         binFolder.create(true, true, null);
      }
      IJavaProject javaProject = JavaCore.create(project);
      javaProject.setOutputLocation(binFolder.getFullPath(), null);
      IClasspathEntry[] entries = new IClasspathEntry[3];
      setComplianceOptions(javaProject, executionEnvironment); // was internal ClasspathComputer
                                                               // before
      entries[0] = createJREEntry(executionEnvironment); // was internal ClasspathComputer before
      entries[1] = createContainerEntry(); // was internal ClasspathComputer before
      entries[2] = JavaCore.newSourceEntry(srcFolder.getFullPath());
      javaProject.setRawClasspath(entries, null);
   }


   private static void createManifest(IFolder metaFolder, String name,
         String[] pluginDependencies) throws CoreException
   {
      IFile manifest = metaFolder.getFile("MANIFEST.MF");
      StringBuffer contents = new StringBuffer();
      contents.append("Manifest-Version: 1.0\n");
      contents.append("Bundle-ManifestVersion: 2\n");
      contents.append("Bundle-Name: " + name + "\n");
      contents.append("Bundle-SymbolicName: " + name + "\n");
      contents.append("Bundle-Version: 1.0.0\n");
      contents.append("Bundle-RequiredExecutionEnvironment: J2SE-1.5\n");
      if (pluginDependencies != null)
      {
         contents.append("Require-Bundle: ");
         for (int i = 0; i < pluginDependencies.length; i++)
         {
            if (i != 0)
            {
               contents.append(",\n ");
            }
            contents.append(pluginDependencies[i]);
         }
         contents.append("\n");
      }
      contents.append("\n");
      manifest.create(new ByteArrayInputStream(contents.toString().getBytes()),
            false, null);
   }


   private static void createBuildProperties(IProject project)
         throws CoreException
   {
      IFile buildProperties = project.getFile("build.properties");
      StringBuffer contents = new StringBuffer();
      contents.append("source.. = src/\n");
      contents.append("output.. = bin/\n");
      contents.append("bin.includes = META-INF/,\\\n");
      contents.append("               .\n");
      buildProperties.create(new ByteArrayInputStream(contents.toString()
            .getBytes()), false, null);
   }


   private static void setComplianceOptions(IJavaProject project,
         String compliance)
   {
      Map<String, String> map = project.getOptions(false);
      if (compliance == null)
      {
         if (map.size() > 0)
         {
            map.remove(JavaCore.COMPILER_COMPLIANCE);
            map.remove(JavaCore.COMPILER_SOURCE);
            map.remove(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM);
            map.remove(JavaCore.COMPILER_PB_ASSERT_IDENTIFIER);
            map.remove(JavaCore.COMPILER_PB_ENUM_IDENTIFIER);
         }
         else
         {
            return;
         }
      }
      else if (JavaCore.VERSION_1_6.equals(compliance))
      {
         map.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_6);
         map.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_6);
         map.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM,
               JavaCore.VERSION_1_6);
         map.put(JavaCore.COMPILER_PB_ASSERT_IDENTIFIER, JavaCore.ERROR);
         map.put(JavaCore.COMPILER_PB_ENUM_IDENTIFIER, JavaCore.ERROR);
      }
      else if (JavaCore.VERSION_1_5.equals(compliance))
      {
         map.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_5);
         map.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_5);
         map.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM,
               JavaCore.VERSION_1_5);
         map.put(JavaCore.COMPILER_PB_ASSERT_IDENTIFIER, JavaCore.ERROR);
         map.put(JavaCore.COMPILER_PB_ENUM_IDENTIFIER, JavaCore.ERROR);
      }
      else if (JavaCore.VERSION_1_4.equals(compliance))
      {
         map.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_4);
         map.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_3);
         map.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM,
               JavaCore.VERSION_1_2);
         updateSeverityComplianceOption(map,
               JavaCore.COMPILER_PB_ASSERT_IDENTIFIER, JavaCore.WARNING);
         updateSeverityComplianceOption(map,
               JavaCore.COMPILER_PB_ENUM_IDENTIFIER, JavaCore.WARNING);
      }
      else if (JavaCore.VERSION_1_3.equals(compliance))
      {
         map.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_3);
         map.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_3);
         map.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM,
               JavaCore.VERSION_1_1);
         updateSeverityComplianceOption(map,
               JavaCore.COMPILER_PB_ASSERT_IDENTIFIER, JavaCore.IGNORE);
         updateSeverityComplianceOption(map,
               JavaCore.COMPILER_PB_ENUM_IDENTIFIER, JavaCore.IGNORE);
      }
      project.setOptions(map);
   }


   private static void updateSeverityComplianceOption(Map<String, String> map,
         String key, String value)
   {
      Integer current_value = null;
      Integer new_value = null;
      String current_string_value = null;
      int current_int_value = 0;
      int new_int_value = 0;
      // Initialize the severity table (only once)
      if (fSeverityTable == null)
      {
         fSeverityTable = new Hashtable<String, Integer>(SEVERITY_ERROR);
         fSeverityTable.put(JavaCore.IGNORE, new Integer(SEVERITY_IGNORE));
         fSeverityTable.put(JavaCore.WARNING, new Integer(SEVERITY_WARNING));
         fSeverityTable.put(JavaCore.ERROR, new Integer(SEVERITY_ERROR));
      }
      // Get the current severity
      current_string_value = (String) map.get(key);
      if (current_string_value != null)
      {
         current_value = (Integer) fSeverityTable.get(current_string_value);
         if (current_value != null)
         {
            current_int_value = current_value.intValue();
         }
      }
      // Get the new severity
      new_value = (Integer) fSeverityTable.get(value);
      if (new_value != null)
      {
         new_int_value = new_value.intValue();
      }
      // If the current severity is not higher than the new severity, replace it
      if (new_int_value > current_int_value)
      {
         map.put(key, value);
      }
   }


   private static IClasspathEntry createJREEntry(String ee)
   {
      IPath path = null;
      if (ee != null)
      {
         IExecutionEnvironmentsManager manager = JavaRuntime
               .getExecutionEnvironmentsManager();
         IExecutionEnvironment env = manager.getEnvironment(ee);
         if (env != null)
            path = JavaRuntime.newJREContainerPath(env);
      }
      if (path == null)
         path = JavaRuntime.newDefaultJREContainerPath();
      return JavaCore.newContainerEntry(path);
   }


   private static IClasspathEntry createContainerEntry()
   {
      return JavaCore.newContainerEntry(REQUIRED_PLUGINS_CONTAINER_PATH); // was PDECore.REQUIRED...
      // before
   }


}
