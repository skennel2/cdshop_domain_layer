package org.almansa.app.domain.exception;

public class ApplicationUserJoinException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ApplicationUserJoinException(Throwable throwable) {
        super(throwable);
    }
    
    public ApplicationUserJoinException(String message) {
        super(message);
    }
}