package org.wfchiang.wid.core.exception;

public class WidSchemaException extends RuntimeException {
    public WidSchemaException (String message) {
        super(message);
    }
    public WidSchemaException (String message, Throwable throwable) {
        super(message, throwable);
    }
}
