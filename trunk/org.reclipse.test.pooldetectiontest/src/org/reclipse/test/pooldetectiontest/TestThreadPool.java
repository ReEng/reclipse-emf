package org.reclipse.test.pooldetectiontest;

/**
 * Main class used to test the thread pool.
 * 
 * @author Jeff Heaton (http://www.jeffheaton.com)
 * @version 1.0
 */
public class TestThreadPool {
 /**
  * Main entry point.
  * 
  * @param args  No arguments are used.
  */
 public static void main(String args[])
 {
  ThreadPool pool = new ThreadPool(10);

  for (int i=1;i<25;i++) {
   pool.assign(new TestWorkerThread());
  }

  pool.complete();

  System.out.println("All tasks are done.");
 }
}