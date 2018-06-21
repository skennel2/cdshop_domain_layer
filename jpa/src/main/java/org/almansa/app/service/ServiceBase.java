package org.almansa.app.service;

public abstract class ServiceBase {
    public void verifyNull(Object obj) throws NullPointerException{
        if (obj == null) {
            throw new NullPointerException("null");
        }
    }
}
