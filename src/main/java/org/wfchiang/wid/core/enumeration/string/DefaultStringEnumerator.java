package org.wfchiang.wid.core.enumeration.string;

import io.swagger.v3.oas.models.media.StringSchema;
import org.wfchiang.wid.core.enumeration.EnumerationContext;

import java.util.Collection;
import java.util.HashSet;

public class DefaultStringEnumerator implements StringEnumerator {
    @Override
    public Collection<String> enumerate(StringSchema stringSchema, EnumerationContext enumerationContext) {
        if (stringSchema == null || enumerationContext == null) {
            throw new IllegalArgumentException("stringSchema or context is null");
        }
        Collection<String> strSet = new HashSet<>();
        strSet.add("");
        return strSet;
    }
}
