package org.reclipse.tests.excessiveallocation;

public class Service implements IService {

    @Override
    public void doSomething(final byte[] data) {
        System.out.println("Service called");
    }

}
