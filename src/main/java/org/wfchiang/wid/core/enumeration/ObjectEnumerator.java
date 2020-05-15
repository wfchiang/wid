package org.wfchiang.wid.core.enumeration;

import io.swagger.oas.inflector.examples.models.Example;
import io.swagger.oas.inflector.examples.models.ObjectExample;
import io.swagger.oas.inflector.examples.models.StringExample;
import org.json.JSONObject;
import org.wfchiang.wid.core.exception.WidUnsupportedClassException;
import org.wfchiang.wid.core.model.EnumerationContext;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ObjectEnumerator implements Enumerator {

    private StringEnumerator stringEnumerator = new StringEnumerator();

    @Override
    public Set<Object> enumerate(Example example, EnumerationContext enumerationContext) {
        if (example instanceof ObjectExample) {
            ObjectExample objectExample = (ObjectExample) example;
            JSONObject jsonObject = new JSONObject();
            Map<String, Example> values = objectExample.getValues();

            for (String key : values.keySet()) {
                Set<Object> objectSet = this.enumerate(values.get(key), enumerationContext);
                if (objectSet.size() > 0) {
                    jsonObject.put(key, objectSet.iterator().next());
                }
            }

            Set<Object> objectSet = new HashSet<>();
            objectSet.add(jsonObject);

            return objectSet;
        }
        else if (example instanceof StringExample) {
            return this.stringEnumerator.enumerate(example, enumerationContext);
        }
        else {
            String className = (example == null ? "(null)" : example.getClass().getName());
            throw new WidUnsupportedClassException("Unsupported class of argument \"example\": " + className);
        }
    }

    public StringEnumerator getStringEnumerator() {
        return stringEnumerator;
    }

    public void setStringEnumerator(StringEnumerator stringEnumerator) {
        this.stringEnumerator = stringEnumerator;
    }
}
