package org.reclipse.tests.structure.util;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.fujaba.commons.console.ReportLevel;
import org.reclipse.patterns.structure.generator.ui.ExportPatternsJob;
import org.reclipse.structure.specification.PSCatalog;
import org.reclipse.structure.specification.PSPatternSpecification;

@Deprecated
public final class Helper
{

	private static final String TMP_PROJECT_NAME = "tmp_generation_project";

	public static final String SEP = "/";

	public static final String EXTENSION_STORY = ".ecore";

	/**
	 * The resource set used to load any resource for the tests.
	 */
	private static ResourceSet ress = new ResourceSetImpl();

	private static String message;




	public static Resource getASTResource(String path)
	{
		return getResource(path, true, true);
	}


	public static PSCatalog getCatalog(Resource resource)
	{
		// get the catalog
		for (EObject element : resource.getContents())
		{
			if (element instanceof PSCatalog)
			{
				return (PSCatalog) element;
			}
		}

		// failed
		fail("the resource has no catalog inside");
		return null;
	}


	public static Resource getCatalogResource(String path)
	{
		return getResource(path, true, true);
	}


	private static Map<Object, Object> getLoadOptions(XMIResourceImpl res)
	{
		Map<Object, Object> options = res.getDefaultLoadOptions();
		options.put(XMLResource.OPTION_DEFER_ATTACHMENT, Boolean.TRUE);
		options.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
		options.put(XMLResource.OPTION_USE_DEPRECATED_METHODS, Boolean.TRUE);
		options.put(XMLResource.OPTION_USE_PARSER_POOL, new XMLParserPoolImpl());
		options.put(XMLResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP, new HashMap<Object, Object>());
		res.setIntrinsicIDToEObjectMap(new HashMap<String, EObject>());
		return options;
	}


	public static PSPatternSpecification getPattern(PSCatalog catalog, String name)
	{
		for (PSPatternSpecification pattern : catalog.getPatternSpecifications())
		{
			if (pattern.getName().equals(name))
			{
				return pattern;
			}
		}

		return null;
	}


	/**
	 * Creates and returns the resource under the specified parameters.
	 * 
	 * @param path The path for the URI under which to load the resource.
	 * @param plugin Whether the resource is plug-in or platform related.
	 * @param load Whether to load the resource.
	 * @return Returns the (possibly loaded) resource.
	 */
	public static Resource getResource(String path, boolean plugin, boolean load)
	{
		// create URI
		URI uri = plugin ? URI.createPlatformPluginURI(path, true) : URI.createPlatformResourceURI(path, true);

		// create resource
		Resource resource = ress.getResource(uri, true);

		// check it
		message = "the resource is null";
		assertNotNull(message, resource);

		// load when necessary
		if (load)
		{
			loadResource(resource);
		}

		return resource;
	}


	public static Resource getStoryResource(String pathCatalog)
	{
		String outPath = pathCatalog + ".ecore";

		// make project
		getTempFileName(outPath);

		// configure generator
		ExportPatternsJob generator = new ExportPatternsJob(pathCatalog, pathCatalog + ".ecore", ReportLevel.MINIMAL);

		// execute and assert
		message = "generation failed";
		assertEquals(message, Status.OK_STATUS, generator.run(new NullProgressMonitor()));

		// save resource
		return getResource(outPath, false, true);
	}


	public static String getTempFileName(String filename)
	{
		// cache some
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

		// get or create project
		IProject project = root.getProject(TMP_PROJECT_NAME);
		if (!project.exists())
		{
			try
			{
				project.create(null);
				project.open(null);
			}
			catch (CoreException e)
			{
				e.printStackTrace();
			}
		}

		// create path
		StringBuilder path = new StringBuilder();

		path.append(root.getLocation().toOSString());
		path.append(project.getFullPath());
		path.append(SEP);
		path.append(filename);
		path.append(".ecore");

		return path.toString();
	}


	private static void loadResource(Resource resource)
	{
		// resource must not be null
		assertNotNull(resource);

		// load it when not done already
		if (!resource.isLoaded())
		{
			// check type of resource
			assertTrue("resource is not of a valid type", resource instanceof XMIResourceImpl);

			try
			{
				resource.load(getLoadOptions((XMIResourceImpl) resource));
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		// check for succ. loading
		assertTrue(resource.isLoaded());
	}


	private Helper()
	{
		// hide constructor
	}
}
