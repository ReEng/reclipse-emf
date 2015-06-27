package org.reclipse.tests.structure;


import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.fujaba.commons.console.ReportLevel;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.reclipse.structure.inference.DetectPatternsJob;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.evaluation.SimilarityEvaluator;
import org.reclipse.structure.inference.notification.InferenceProgressProvider;
import org.reclipse.structure.inference.strategy.BottomUpStrategy;
import org.reclipse.structure.specification.PSCatalog;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.tests.structure.util.ResultCollector;
import org.reclipse.tests.structure.util.TestsUtil;


public class DependenciesTests
{
	private static BottomUpStrategy strategy;

	private static Map<PSPatternSpecification, Collection<ASGAnnotation>> results;

	private static PSCatalog catalog;


	private static void checkRank(String name, int expected)
	{
		// prepare the data
		String message = "the rank is not correct for " + name;

		PSPatternSpecification pattern = null;
		for (PSPatternSpecification aPattern : catalog.getPatternSpecifications())
		{
			if (name.equals(aPattern.getName()))
			{
				pattern = aPattern;
				break;
			}
		}
		int actual = strategy.getRank(pattern);

		// assert it
		assertEquals(message, expected, actual);
	}


	@BeforeClass
	public static void start()
	{
		String catalogPath = TestsUtil.getPath("dependencies.psc");
		String hostPath = TestsUtil.getPath("blog.gast");

		ResourceSet resourceSet = new ResourceSetImpl();
      Resource catalogResource = resourceSet.getResource(URI.createPlatformResourceURI(catalogPath, true), true);
      Resource hostGraphResource = resourceSet.getResource(URI.createPlatformResourceURI(hostPath, true), true);
      Resource enginesResource = resourceSet.getResource(URI.createPlatformResourceURI(catalogPath + ".ecore", true), true);
		
		DetectPatternsJob job = new DetectPatternsJob(catalogResource, enginesResource, hostGraphResource, ReportLevel.MINIMAL);
		job.setUser(true);

		job.setEvaluator(new SimilarityEvaluator());
		job.setAnnotateAdditionalElements(false);

		// conf. results collector
		ResultCollector collector = new ResultCollector();
		InferenceProgressProvider.get(job.getEngine()).addListener(collector);

		// start
		job.schedule();

		// wait until engine has done its work
		while (!collector.isDone())
		{
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
			}
		}

		// store results
		results = collector.getCurrentResults();

		// get catalog
		for (PSPatternSpecification pattern : results.keySet())
		{
			catalog = pattern.getCatalog();
			break;
		}

		// configure 'second' strategy
		IProgressMonitor monitor = new NullProgressMonitor();
		monitor.setCanceled(true);
		strategy = new BottomUpStrategy(job.getEngine(), catalog);
		strategy.startInference(monitor);
	}


	@AfterClass
	public static void stop()
	{
		results.clear();
		results = null;

		strategy = null;
	}


	@Test
	public void testSimpleStructure()
	{
		checkRank("SimpleA", 0);
		checkRank("SimpleB", 0);
		checkRank("SimpleC", 0);
		checkRank("SimpleD", 0);
	}


	@Test
	public void testNonCircularStructure()
	{
		checkRank("RefA", 0);
		checkRank("RefB", 1);
		checkRank("RefC", 0);
		checkRank("RefD", 1);
		checkRank("RefE", 2);
	}


	@Test
	public void testCircularStructure()
	{
		checkRank("CircA", 0);
		checkRank("CircB", 0);
		checkRank("CircC", 1);
		checkRank("CircD", 2);
		checkRank("CircE", 3);
	}
}
