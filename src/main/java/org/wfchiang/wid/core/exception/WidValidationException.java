package org.wfchiang.wid.core.exception;

public class WidValidationException extends RuntimeException {
    public WidValidationException (String message) {
        super(message);
    }
    public WidValidationException (String message, Throwable throwable) {
        super(message, throwable);
    }
}
