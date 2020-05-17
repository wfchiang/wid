package org.wfchiang.wid.core.util;

public class WidShortHands {

    public static final String NULL_CLASS_NAME = "(null)";

    public static String getClassName (Object object) {
        return (object == null ? NULL_CLASS_NAME : object.getClass().getName());
    }
}
