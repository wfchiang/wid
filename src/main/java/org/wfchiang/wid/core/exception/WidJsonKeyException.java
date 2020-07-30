package org.wfchiang.wid.core.exception;

public class WidJsonKeyException extends RuntimeException {
    public WidJsonKeyException (String message) {
        super(message);
    }
    public WidJsonKeyException (String message, Throwable throwable) {
        super(message, throwable);
    }
}
