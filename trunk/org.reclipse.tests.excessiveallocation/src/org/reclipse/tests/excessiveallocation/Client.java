package org.reclipse.tests.excessiveallocation;

public class Client {

    private final IService service;

    public Client() {
        super();
        service = new Service();
    }

    public void start() {
        for (int i=0; i < 1000; i++) {
            final byte[] largeArray = new byte[10000];
            service.doSomething(largeArray);
        }
    }
}
