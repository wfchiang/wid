package org.wfchiang.wid.core.enumeration;

import io.swagger.v3.oas.models.media.StringSchema;

import java.util.Set;

public interface StringEnumerator {
    public Set<String> enumerate (StringSchema stringSchema, EnumerationContext enumerationContext);
}
