package org.reclipse.tracer.test;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchDelegate;
import org.reclipse.tracer.ITracerConstants;
import org.reclipse.tracer.exceptions.TracerStartException;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * This class is used for testing purposes only.
 * 
 * @author lowende
 * @author Last editor: $Author: mvdetten $
 * @version $Revision: 3962 $ $Date: 2008-08-19 15:13:30 +0200 (Di, 19 Aug 2008) $
 */
public class LaunchConfiguration implements ILaunchConfiguration
{

   /**
    * The launch modes set on this configuration.
    * 
    * @since 3.3
    */
   public static final String ATTR_LAUNCH_MODES = DebugPlugin
         .getUniqueIdentifier()
         + ".LAUNCH_MODES"; //$NON-NLS-1$

   private LaunchConfigurationInfo launchConfigInfo;

   private File file;


   private LaunchConfiguration(File file,
         LaunchConfigurationInfo launchConfigInfo)
   {
      this.file = file;
      this.launchConfigInfo = launchConfigInfo;
   }


   public static ILaunchConfiguration load(File file)
         throws TracerStartException
   {
      try
      {
         DocumentBuilder parser = DocumentBuilderFactory.newInstance()
               .newDocumentBuilder();
         parser.setErrorHandler(new DefaultHandler());

         Element root = parser.parse(new InputSource(new FileReader(file)))
               .getDocumentElement();

         LaunchConfigurationInfo launchConfigInfo = new LaunchConfigurationInfo();
         launchConfigInfo.initializeFromXML(root);

         return new LaunchConfiguration(file, launchConfigInfo);
      }
      catch (ParserConfigurationException e)
      {
         throw new TracerStartException("Parsing of '" + file.getAbsolutePath()
               + "' failed: " + e.getMessage(), e);
      }
      catch (FactoryConfigurationError e)
      {
         throw new TracerStartException("Parsing of '" + file.getAbsolutePath()
               + "' failed: " + e.getMessage(), e);
      }
      catch (SAXException e)
      {
         throw new TracerStartException("Parsing of '" + file.getAbsolutePath()
               + "' failed: " + e.getMessage(), e);
      }
      catch (IOException e)
      {
         throw new TracerStartException("Loading of '" + file.getAbsolutePath()
               + "' failed: " + e.getMessage(), e);
      }
   }


   private LaunchConfigurationInfo getInfo()
   {
      return this.launchConfigInfo;
   }


   /**
    * @see ILaunchConfiguration#launch(String, IProgressMonitor)
    */
   public ILaunch launch(String mode, IProgressMonitor monitor)
         throws CoreException
   {
      throw new UnsupportedOperationException(
            "This is just read only launch configuration.");
   }


   /**
    * @see org.eclipse.debug.core.ILaunchConfiguration#launch(java.lang.String,
    *      org.eclipse.core.runtime.IProgressMonitor, boolean)
    */
   public ILaunch launch(String mode, IProgressMonitor monitor, boolean build)
         throws CoreException
   {
      throw new UnsupportedOperationException(
            "This is just read only launch configuration.");
   }


   /**
    * @see org.eclipse.debug.core.ILaunchConfiguration#launch(java.lang.String,
    *      org.eclipse.core.runtime.IProgressMonitor, boolean, boolean)
    */
   public ILaunch launch(String mode, IProgressMonitor monitor, boolean build,
         boolean register) throws CoreException
   {
      throw new UnsupportedOperationException(
            "This is just read only launch configuration.");
   }


   /**
    * @see ILaunchConfiguration#supportsMode(String)
    */
   public boolean supportsMode(String mode) throws CoreException
   {
      return ITracerConstants.TRACE_MODE.equals(mode);
   }


   /**
    * A configuration's name is that of the last segment in it's location (subtract the ".launch"
    * extension).
    * 
    * @see ILaunchConfiguration#getName()
    */
   public String getName()
   {
      String name = getLocation().lastSegment();

      if (name.length() > LAUNCH_CONFIGURATION_FILE_EXTENSION.length())
      {
         name = name.substring(0, name.length()
               - (LAUNCH_CONFIGURATION_FILE_EXTENSION.length() + 1));
      }

      return name;
   }


   /**
    * @see ILaunchConfiguration#getLocation()
    */
   public IPath getLocation()
   {
      return new Path(this.file.getAbsolutePath());
   }


   /**
    * @see ILaunchConfiguration#exists()
    */
   public boolean exists()
   {
      return this.file.exists();
   }


   /**
    * @see ILaunchConfiguration#getAttribute(String, int)
    */
   public int getAttribute(String attributeName, int defaultValue)
         throws CoreException
   {
      return getInfo().getIntAttribute(attributeName, defaultValue);
   }


   /**
    * @see ILaunchConfiguration#getAttribute(String, String)
    */
   public String getAttribute(String attributeName, String defaultValue)
         throws CoreException
   {
      return getInfo().getStringAttribute(attributeName, defaultValue);
   }


   /**
    * @see ILaunchConfiguration#getAttribute(String, boolean)
    */
   public boolean getAttribute(String attributeName, boolean defaultValue)
         throws CoreException
   {
      return getInfo().getBooleanAttribute(attributeName, defaultValue);
   }


   /**
    * @see ILaunchConfiguration#getAttribute(String, List)
    */
   public List getAttribute(String attributeName, List defaultValue)
         throws CoreException
   {
      return getInfo().getListAttribute(attributeName, defaultValue);
   }


   /**
    * @see ILaunchConfiguration#getAttribute(String, Map)
    */
   public Map getAttribute(String attributeName, Map defaultValue)
         throws CoreException
   {
      return getInfo().getMapAttribute(attributeName, defaultValue);
   }


   /**
    * @see ILaunchConfiguration#getType()
    */
   public ILaunchConfigurationType getType() throws CoreException
   {
      throw new UnsupportedOperationException(
            "This is just read only launch configuration.");
   }


   /**
    * @see ILaunchConfiguration#isLocal()
    */
   public boolean isLocal()
   {
      return false;
   }


   /**
    * @see ILaunchConfiguration#getWorkingCopy()
    */
   public ILaunchConfigurationWorkingCopy getWorkingCopy() throws CoreException
   {
      throw new UnsupportedOperationException(
            "This is just read only launch configuration.");
   }


   /**
    * @see ILaunchConfiguration#copy(String name)
    */
   public ILaunchConfigurationWorkingCopy copy(String name)
         throws CoreException
   {
      throw new UnsupportedOperationException(
            "This is just read only launch configuration.");
   }


   /**
    * @see ILaunchConfiguration#isWorkingCopy()
    */
   public boolean isWorkingCopy()
   {
      return false;
   }


   /**
    * @see ILaunchConfiguration#delete()
    */
   public void delete() throws CoreException
   {
      throw new UnsupportedOperationException(
            "This is just read only launch configuration.");
   }


   /**
    * @see ILaunchConfiguration#getMemento()
    */
   public String getMemento() throws CoreException
   {
      throw new UnsupportedOperationException(
            "This is just read only launch configuration.");
   }


   /**
    * @see ILaunchConfiguration#getFile()
    */
   public IFile getFile()
   {
      throw new UnsupportedOperationException(
            "This is just read only launch configuration.");
   }


   /**
    * @see ILaunchConfiguration#contentsEqual(ILaunchConfiguration)
    */
   public boolean contentsEqual(ILaunchConfiguration object)
   {
      return false;
   }


   /**
    * @see org.eclipse.debug.core.ILaunchConfiguration#getCategory()
    */
   public String getCategory() throws CoreException
   {
      return getType().getCategory();
   }


   /**
    * @see org.eclipse.debug.core.ILaunchConfiguration#getAttributes()
    */
   public Map getAttributes() throws CoreException
   {
      return getInfo().getAttributes();
   }


   /**
    * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
    */
   public Object getAdapter(Class adapter)
   {
      throw new UnsupportedOperationException(
            "This is just read only launch configuration.");
   }


   /**
    * @see org.eclipse.debug.core.ILaunchConfiguration#getMappedResources()
    */
   public IResource[] getMappedResources() throws CoreException
   {
      return null;
   }


   /**
    * @see org.eclipse.debug.core.ILaunchConfiguration#isMigrationCandidate()
    */
   public boolean isMigrationCandidate() throws CoreException
   {
      return false;
   }


   /**
    * @see org.eclipse.debug.core.ILaunchConfiguration#migrate()
    */
   public void migrate() throws CoreException
   {
   }


   /**
    * @see org.eclipse.debug.core.ILaunchConfiguration#getAttribute(java.lang.String, java.util.Set)
    */
   public Set getAttribute(String attributeName, Set defaultValue)
         throws CoreException
   {
      return getInfo().getSetAttribute(attributeName, defaultValue);
   }


   /**
    * @see org.eclipse.debug.core.ILaunchConfiguration#getModes()
    */
   public Set getModes() throws CoreException
   {
      Set options = getAttribute(ATTR_LAUNCH_MODES, (Set) null);
      return (options != null ? new HashSet(options) : new HashSet(0));
   }


   /**
    * @see org.eclipse.debug.core.ILaunchConfiguration#getPreferredDelegate(java.util.Set)
    */
   public ILaunchDelegate getPreferredDelegate(Set modes) throws CoreException
   {
      return null;
   }


   /**
    * @see org.eclipse.debug.core.ILaunchConfiguration#isReadOnly()
    */
   public boolean isReadOnly()
   {
      if (!isLocal())
      {
         IFile file = getFile();
         if (file != null)
         {
            return file.isReadOnly();
         }
      }
      else
      {
         File file = getLocation().toFile();
         if (file != null)
         {
            return file.exists() && !file.canWrite();
         }
      }
      return false;
   }


   public boolean hasAttribute(String attributeName) throws CoreException
   {
      return false;
   }

}
