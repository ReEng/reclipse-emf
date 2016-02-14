package org.reclipse.tests.excessiveallocation;

public class Service implements IService {

    @Override
    public synchronized void doSomething(final byte[] data) {
    			System.out.println("Service called");
    }

}
