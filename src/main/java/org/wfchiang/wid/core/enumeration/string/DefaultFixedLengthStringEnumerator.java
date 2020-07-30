package org.wfchiang.wid.core.enumeration.string;

import io.swagger.v3.oas.models.media.StringSchema;
import org.wfchiang.wid.core.enumeration.EnumerationContext;

import java.util.Collection;
import java.util.HashSet;

public class DefaultFixedLengthStringEnumerator extends FixedLengthStringEnumerator {

    private char defaultChar = ' ';

    public DefaultFixedLengthStringEnumerator () {
        super();
    }

    public DefaultFixedLengthStringEnumerator (int stringLength) {
        super(stringLength);
    }

    public DefaultFixedLengthStringEnumerator (int stringLength, char defaultChar) {
        super(stringLength);
        this.setDefaultChar(defaultChar);
    }

    @Override
    public Collection<String> enumerate(StringSchema stringSchema, EnumerationContext enumerationContext) {
        String enuString = "";
        for (int i = 0 ; i < this.stringLength ; i++) {
            enuString = enuString + this.defaultChar;
        }
        Collection<String> enuSet = new HashSet<>();
        enuSet.add(enuString);
        return enuSet;
    }

    public char getDefaultChar() {
        return defaultChar;
    }

    public void setDefaultChar(char defaultChar) {
        this.defaultChar = defaultChar;
    }
}
