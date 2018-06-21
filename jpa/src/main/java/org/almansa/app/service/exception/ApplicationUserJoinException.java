package org.almansa.app.service.exception;

public class ApplicationUserJoinException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ApplicationUserJoinException(String message, Exception exception) {
        super(message, exception);
    }
    
    public ApplicationUserJoinException(Exception exception) {
        super(exception);
    }

    public ApplicationUserJoinException(String message) {
        super(message);
    }
}