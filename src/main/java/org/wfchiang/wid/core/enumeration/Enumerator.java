package org.wfchiang.wid.core.enumeration;

import org.wfchiang.wid.core.model.EnumerationContext;
import io.swagger.oas.inflector.examples.models.Example;

public interface Enumerator {
    public Object enumerate (Example example, EnumerationContext enumerationContext);
}
