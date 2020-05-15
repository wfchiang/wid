package org.wfchiang.wid.core.enumeration;

import io.swagger.oas.inflector.examples.models.Example;
import io.swagger.oas.inflector.examples.models.ObjectExample;
import io.swagger.oas.inflector.examples.models.StringExample;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.wfchiang.wid.core.exception.WidUnsupportedClassException;
import org.wfchiang.wid.core.model.EnumerationContext;

import java.util.Map;

public class ObjectEnumerator implements Enumerator {

    private StringEnumerator stringEnumerator = new StringEnumerator();

    @Override
    public Object enumerate(Example example, EnumerationContext enumerationContext) {
        if (example instanceof ObjectExample) {
            ObjectExample objectExample = (ObjectExample) example;
            JSONObject jsonObject = new JSONObject();
            Map<String, Example> values = objectExample.getValues();

            for (String key : values.keySet()) {
                jsonObject.put(key, this.enumerate(values.get(key), enumerationContext));
            }

            return jsonObject;
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
