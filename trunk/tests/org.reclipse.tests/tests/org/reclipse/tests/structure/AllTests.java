package org.reclipse.tests.structure;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({ DependenciesTests.class, SimpleTests.class, BlogTests.class })
public class AllTests
{
	// the rest is done by junit
}
