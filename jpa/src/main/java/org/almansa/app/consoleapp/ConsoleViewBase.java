package org.almansa.app.consoleapp;

public abstract class ConsoleViewBase {
    public abstract void handleRequest(String request);

    public String[] splitBySpace(String source) {
        return source.split("\\s+");
    }
}