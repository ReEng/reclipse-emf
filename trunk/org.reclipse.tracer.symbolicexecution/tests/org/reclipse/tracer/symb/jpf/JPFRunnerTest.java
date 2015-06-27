package org.reclipse.tracer.symb.jpf;

import java.io.IOException;

import org.junit.Test;
import org.reclipse.tracer.symbolicexecution.jpf.JPFRunner;

public class JPFRunnerTest 
{
	@Test
	public void usage() throws IOException
	{
		JPFRunner jpfRunner = new JPFRunner();	
		jpfRunner.addClassPath("./lib/jpf-classes.jar");
		jpfRunner.addNativeClassPath("./lib/jpf-symbc/jpf-symbc.jar");
		jpfRunner.setSUT("./bin", "org.reclipse.tracer.symbolicexecution.jpf.example.Simple");
		jpfRunner.run(new String[]{});
	}

}
