package org.wfchiang.wid.core.enumeration.string;

import io.swagger.v3.oas.models.media.StringSchema;
import org.wfchiang.wid.core.enumeration.EnumerationContext;

import java.util.HashSet;
import java.util.Set;

public class DefaultStringEnumerator implements StringEnumerator {
    @Override
    public Set<String> enumerate(StringSchema stringSchema, EnumerationContext enumerationContext) {
        if (stringSchema == null || enumerationContext == null) {
            throw new IllegalArgumentException("stringSchema or context is null");
        }
        Set<String> strSet = new HashSet<>();
        strSet.add("");
        return strSet;
    }
}
