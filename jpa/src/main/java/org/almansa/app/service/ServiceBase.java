package org.almansa.app.service;

public abstract class ServiceBase {
    public void verifyNull(Object obj) {
        if (obj == null) {
            throw new RuntimeException("null");
        }
    }
}
