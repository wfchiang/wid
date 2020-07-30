package org.wfchiang.wid.core.exception;

public class WidFuzzerException extends RuntimeException {
    public WidFuzzerException (String message) {
        super(message);
    }
    public WidFuzzerException (String message, Throwable throwable) {
        super(message, throwable);
    }
}
