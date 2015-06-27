package org.reclipse.tests.structure;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.tests.structure.util.TestsUtil;

import de.fzi.gast.types.GASTClass;
import de.fzi.gast.types.Visibilities;
import de.fzi.gast.variables.Field;


public class BlogTests
{
	private static String message;

	private static Map<PSPatternSpecification, Collection<ASGAnnotation>> results;


	@BeforeClass
	public static void start()
	{
		// get paths
		String catalogPath = TestsUtil.getPath("PatternsCatalogNew.psc");
		String hostPath = TestsUtil.getPath("blog.gast");

		// get results
		results = TestsUtil.getResults(catalogPath, hostPath, true);

		// there should be at least some annotations
		String message = "no resulting annotations";
		assertNotNull(message, results);
		assertFalse(message, results.isEmpty());
	}


	@Test
	public void testSingleton()
	{
		// prepare the data
		int expected = 1;
		PSPatternSpecification pattern = null;
		for (PSPatternSpecification found : results.keySet())
		{
			if (found.getName().equals("Singleton"))
			{
				pattern = found;
			}
		}

		// assert
		assertNotNull("nothing annotated.", pattern);

		Collection<ASGAnnotation> result = results.get(pattern);

		// check size
		TestsUtil.checkSize(result, expected);

		ASGAnnotation annotation = null;
		for (ASGAnnotation asgAnnotation : result)
		{
			annotation = asgAnnotation;
		}

		// check annotated size
		TestsUtil.checkSize(annotation.getAnnotatedElements(), 2);

		// check annotated instance field
		List<EObject> instance = annotation.getAnnotatedElements().get("instance");
		TestsUtil.checkSize(instance, 1);

		message = "the singleton instance field has the wrong type";
		assertTrue(message, instance.get(0) instanceof Field);

		Field instanceElement = (Field) instance.get(0);

		message = "the singleton instance field is not static";
		assertTrue(message, instanceElement.isStatic());

		message = "the singleton instance field is not private visible";
		assertEquals(message, instanceElement.getVisibility(), Visibilities.VISIBILITYPRIVAT);

		// check annotated singleton class
		List<EObject> singleton = annotation.getAnnotatedElements().get("singleton");
		TestsUtil.checkSize(singleton, 1);

		message = "the singleton is of a wrong type";
		assertTrue(message, singleton.get(0) instanceof GASTClass);

		GASTClass singletonElement = (GASTClass) singleton.get(0);
		assertNotNull(singletonElement);

		// check bounded objects
		EMap<String, EList<EObject>> bounded = annotation.getBoundObjects();
		TestsUtil.checkSize(bounded, 7);

		// check bounded elements
		TestsUtil.checkSize(bounded.get("instanceField"), 1);
		TestsUtil.checkSize(bounded.get("singletonClass"), 1);
		TestsUtil.checkSize(bounded.get("privateConstructor"), 1);
		TestsUtil.checkSize(bounded.get("publicConstructor"), 0);
		TestsUtil.checkSize(bounded.get("accessorMethod"), 1);
		TestsUtil.checkSize(bounded.get("fieldAccess"), 1);
	}


	@Test
	public void testObserver()
	{
		// prepare the data
		int expected = 1;
		PSPatternSpecification pattern = null;
		for (PSPatternSpecification found : results.keySet())
		{
			if (found.getName().equals("Observer"))
			{
				pattern = found;
			}
		}

		// assert
		assertNotNull("nothing annotated.", pattern);

		Collection<ASGAnnotation> result = results.get(pattern);

		// check size
		TestsUtil.checkSize(result, expected);
	}


	@Test
	public void testStrategy()
	{
		// prepare the data
		int expected = 1;
		PSPatternSpecification pattern = null;
		for (PSPatternSpecification found : results.keySet())
		{
			if (found.getName().equals("Strategy"))
			{
				pattern = found;
			}
		}

		// assert
		assertNotNull("nothing annotated.", pattern);

		Collection<ASGAnnotation> result = results.get(pattern);

		// check size
		TestsUtil.checkSize(result, expected);
	}


	@AfterClass
	public static void stop()
	{
		results.clear();
		results = null;
	}
}
