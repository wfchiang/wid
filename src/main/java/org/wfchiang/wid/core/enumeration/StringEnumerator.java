package org.wfchiang.wid.core.enumeration;

import io.swagger.oas.inflector.examples.models.Example;
import io.swagger.oas.inflector.examples.models.StringExample;
import org.wfchiang.wid.core.exception.WidUnsupportedClassException;
import org.wfchiang.wid.core.model.EnumerationContext;

public class StringEnumerator implements Enumerator {
    @Override
    public Object enumerate(Example example, EnumerationContext enumerationContext) {
        if (example instanceof StringExample) {
            StringExample stringExample = (StringExample) example;
            return stringExample.getValue();
        }
        else {
            throw new WidUnsupportedClassException("Argument \"example\" need to be a StringExample");
        }
    }
}
