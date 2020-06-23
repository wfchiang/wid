package org.wfchiang.wid.core.enumeration.string;

import io.swagger.v3.oas.models.media.StringSchema;
import org.wfchiang.wid.core.enumeration.EnumerationContext;

import java.util.HashSet;
import java.util.Set;

public class DefaultRangedLengthStringEnumerator implements StringEnumerator {

    private DefaultFixedLengthStringEnumerator defaultFixedLengthStringEnumerator;
    private int minLength = 0;
    private int maxLength = 1;

    public DefaultRangedLengthStringEnumerator(char defaultChar, int minLength, int maxLength) {
        this.defaultFixedLengthStringEnumerator = new DefaultFixedLengthStringEnumerator(0);
        this.setDefaultChar(defaultChar);
        this.setMaxLength(maxLength);
        this.setMinLength(minLength);
    }

    @Override
    public Set<String> enumerate(StringSchema stringSchema, EnumerationContext enumerationContext) {
        Set<String> enuSet = new HashSet<>();
        for (int i = this.minLength ; i < this.maxLength ; i++) {
            this.defaultFixedLengthStringEnumerator.setStringLength(i);
            Set<String> newSet = this.defaultFixedLengthStringEnumerator.enumerate(stringSchema, enumerationContext);
            enuSet.addAll(newSet);
        }
        return enuSet;
    }

    public char getDefaultChar() {
        return this.defaultFixedLengthStringEnumerator.getDefaultChar();
    }

    public void setDefaultChar(char defaultChar) {
        this.defaultFixedLengthStringEnumerator.setDefaultChar(defaultChar);
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
