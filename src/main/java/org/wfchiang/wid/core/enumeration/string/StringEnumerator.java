package org.wfchiang.wid.core.enumeration.string;

import io.swagger.v3.oas.models.media.StringSchema;
import org.wfchiang.wid.core.enumeration.EnumerationContext;

import java.util.Collection;

public interface StringEnumerator {
    public Collection<String> enumerate (StringSchema stringSchema, EnumerationContext enumerationContext);
}
