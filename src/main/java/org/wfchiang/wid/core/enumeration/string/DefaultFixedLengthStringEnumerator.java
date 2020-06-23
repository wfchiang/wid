package org.wfchiang.wid.core.enumeration.string;

import io.swagger.v3.oas.models.media.StringSchema;
import org.wfchiang.wid.core.enumeration.EnumerationContext;

import java.util.HashSet;
import java.util.Set;

public class DefaultFixedLengthStringEnumerator implements StringEnumerator {

    private String defaultChar = " ";
    private int stringLength = 0;

    public DefaultFixedLengthStringEnumerator (int stringLength) {
        this.setStringLength(stringLength);
    }

    public DefaultFixedLengthStringEnumerator (int stringLength, String defaultChar) {
        this(stringLength);
        this.setDefaultChar(defaultChar);
    }

    @Override
    public Set<String> enumerate(StringSchema stringSchema, EnumerationContext enumerationContext) {
        String enuString = "";
        for (int i = 0 ; i < this.stringLength ; i++) {
            enuString = enuString + this.defaultChar;
        }
        Set<String> enuSet = new HashSet<>();
        enuSet.add(enuString);
        return enuSet;
    }

    public String getDefaultChar() {
        return defaultChar;
    }

    public void setDefaultChar(String defaultChar) {
        if (defaultChar == null || defaultChar.length() != 1) {
            throw new IllegalArgumentException("Invalid default char: " + defaultChar);
        }
        this.defaultChar = defaultChar;
    }

    public int getStringLength() {
        return stringLength;
    }

    public void setStringLength(int stringLength) {
        if (stringLength < 0) {
            throw new IllegalArgumentException("Invalid string length: " + stringLength);
        }
        this.stringLength = stringLength;
    }
}
