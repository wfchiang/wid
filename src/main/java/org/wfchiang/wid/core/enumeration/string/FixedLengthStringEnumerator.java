package org.wfchiang.wid.core.enumeration.string;

public abstract class FixedLengthStringEnumerator implements StringEnumerator {
    protected int stringLength;

    public FixedLengthStringEnumerator () {
        this(0);
    }

    public FixedLengthStringEnumerator (int stringLength) {
        this.setStringLength(stringLength);
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
