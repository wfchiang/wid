package org.wfchiang.wid.core.exception;

public class WidEnumerationException extends RuntimeException {
    public WidEnumerationException (String message) {
        super(message);
    }
    public WidEnumerationException (String message, Throwable throwable) {
        super(message, throwable);
    }
}
