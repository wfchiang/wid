package org.wfchiang.wid.core.enumeration.string;

import io.swagger.v3.oas.models.media.StringSchema;
import org.wfchiang.wid.core.enumeration.EnumerationContext;

import java.util.Set;

public interface StringEnumerator {
    public Set<String> enumerate (StringSchema stringSchema, EnumerationContext enumerationContext);
}
