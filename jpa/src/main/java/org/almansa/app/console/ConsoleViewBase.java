package org.almansa.app.console;

public abstract class ConsoleViewBase {
    public abstract void handleRequest(String request);

    public String[] splitBySpace(String source) {
        return source.split("\\s+");
    }
}