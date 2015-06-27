package org.reclipse.tests.structure;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.tests.structure.util.TestsUtil;


public class SimpleTests
{
	private static Map<PSPatternSpecification, Collection<ASGAnnotation>> results;


	@BeforeClass
	public static void start()
	{
	   
		// get paths
		String catalogPath = TestsUtil.getPath("simple.psc");
		String hostPath = TestsUtil.getPath("blog.gast");

		// get results
		results = TestsUtil.getResults(catalogPath, hostPath, true);

		// there should be at least some annotations
		String message = "no resulting annotations";
		assertNotNull(message, results);
		assertFalse(message, results.isEmpty());
	}


	@AfterClass
	public static void stop()
	{
		results.clear();
		results = null;
	}


	@Test
	public void testSingleObject()
	{
		// prepare the data
		int expected = 6;
		PSPatternSpecification pattern = TestsUtil.getPattern(results, "SingleObject");
		Collection<ASGAnnotation> result = results.get(pattern);

		// check size
		TestsUtil.checkSize(result, expected);
	}


	@Test
	public void testSingleObjectAttributeConstraints()
	{
		// prepare the data
		int expected = 27; // TODO: count manually
		PSPatternSpecification pattern = TestsUtil.getPattern(results, "SingleObjectAttributeConstraints");
		Collection<ASGAnnotation> result = results.get(pattern);

		// check size
		TestsUtil.checkSize(result, expected);
	}


	@Test
	public void testSingleObjectMetricConstraint()
	{
		// prepare the data
		int expected = 51; // TODO: count manually
		PSPatternSpecification pattern = TestsUtil.getPattern(results, "SingleObjectMetricConstraint");
		Collection<ASGAnnotation> result = results.get(pattern);

		// check size
		TestsUtil.checkSize(result, expected);
	}


	@Test
	public void testNegativeObject()
	{
		// prepare the data
		int expected = 32; // TODO: count manually
		PSPatternSpecification pattern = TestsUtil.getPattern(results, "NegativeObject");
		Collection<ASGAnnotation> result = results.get(pattern);

		// check size
		TestsUtil.checkSize(result, expected);
	}


	@Test
	public void testSetObject()
	{
		// prepare the data
		int expected = 18; // TODO: count manually
		PSPatternSpecification pattern = TestsUtil.getPattern(results, "SetObject");
		Collection<ASGAnnotation> result = results.get(pattern);

		// check size
		TestsUtil.checkSize(result, expected);
	}


	@Test
	public void testAdditionalObject()
	{
		// prepare the data
		int expected = 51; // TODO: count manually
		PSPatternSpecification pattern = TestsUtil.getPattern(results, "AdditionalObject");
		Collection<ASGAnnotation> result = results.get(pattern);

		// check size
		TestsUtil.checkSize(result, expected);
	}
}
