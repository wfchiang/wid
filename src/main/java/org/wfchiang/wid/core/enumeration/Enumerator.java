package org.wfchiang.wid.core.enumeration;

import org.wfchiang.wid.core.model.EnumerationContext;
import io.swagger.oas.inflector.examples.models.Example;

import java.util.Set;

public interface Enumerator {
    public Set<Object> enumerate (Example example, EnumerationContext enumerationContext);
}
