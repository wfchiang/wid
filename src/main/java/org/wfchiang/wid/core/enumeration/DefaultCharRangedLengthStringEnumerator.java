package org.wfchiang.wid.core.enumeration;

import io.swagger.v3.oas.models.media.StringSchema;

import java.util.HashSet;
import java.util.Set;

public class DefaultCharRangedLengthStringEnumerator implements StringEnumerator {

    private String defaultChar = " ";
    private int minLength = 0;
    private int maxLength = 1;

    public DefaultCharRangedLengthStringEnumerator (String defaultChar, int minLength, int maxLength) {
        this.setDefaultChar(defaultChar);
        this.setMaxLength(maxLength);
        this.setMinLength(minLength);
    }

    @Override
    public Set<String> enumerate(StringSchema stringSchema, EnumerationContext enumerationContext) {
        Set<String> enuSet = new HashSet<>();
        String enuString = "";
        for (int i = 0 ; i < maxLength ; i++) {
            if ((minLength <= i) && (i < maxLength)) {
                enuSet.add(new String(enuString));
            }
            enuString = enuString + this.defaultChar;
        }
        return enuSet;
    }

    public String getDefaultChar() {
        return defaultChar;
    }

    public void setDefaultChar(String defaultChar) {
        if (defaultChar == null || defaultChar.length() != 1) {
            throw new IllegalArgumentException("Invalid defaultChar: " + defaultChar);
        }
        this.defaultChar = defaultChar;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        if (! ((0 <= minLength) && (minLength < this.maxLength))) {
            throw new IllegalArgumentException("Invalid minLength " + minLength);
        }
        this.minLength = minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        if (! ((0 <= this.minLength) && (this.minLength < maxLength))) {
            throw new IllegalArgumentException("Invalid maxLength " + maxLength);
        }
        this.maxLength = maxLength;
    }
}
