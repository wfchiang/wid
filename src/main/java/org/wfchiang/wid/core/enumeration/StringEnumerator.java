package org.wfchiang.wid.core.enumeration;

import io.swagger.oas.inflector.examples.models.Example;
import io.swagger.oas.inflector.examples.models.StringExample;
import org.wfchiang.wid.core.exception.WidUnsupportedClassException;
import org.wfchiang.wid.core.model.EnumerationContext;

import java.util.HashSet;
import java.util.Set;

public class StringEnumerator implements Enumerator {
    @Override
    public Set<Object> enumerate(Example example, EnumerationContext enumerationContext) {
        if (example instanceof StringExample) {
            StringExample stringExample = (StringExample) example;
            Set<Object> objectSet = new HashSet<>();
            objectSet.add(stringExample.getValue());
            return objectSet;
        }
        else {
            throw new WidUnsupportedClassException("Argument \"example\" need to be a StringExample");
        }
    }
}
