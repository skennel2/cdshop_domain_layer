package org.almansa.app.console;

import org.almansa.app.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;

public class AlbumConsoleView extends ConsoleViewBase {

    @Autowired
    private AlbumService service;

    @Override
    public void handleRequest(String request) {

    }
}
