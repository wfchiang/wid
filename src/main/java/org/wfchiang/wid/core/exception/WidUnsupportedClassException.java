package org.wfchiang.wid.core.exception;

public class WidUnsupportedClassException extends RuntimeException {
    public WidUnsupportedClassException(String message) {
        super(message);
    }
    public WidUnsupportedClassException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
