package org.wfchiang.wid.core.enumeration;

import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.json.JSONObject;
import org.wfchiang.wid.core.exception.WidUnsupportedClassException;
import org.wfchiang.wid.core.util.WidShortHands;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class EnumerationContext {

    private StringEnumerator stringEnumerator;
    private ObjectEnumerator objectEnumerator;
    private EnumerationHistory enumerationHistory;

    public EnumerationContext () {
        this.stringEnumerator = new DefaultStringEnumerator();
        this.objectEnumerator = new DefaultObjectEnumerator();
        this.enumerationHistory = new EnumerationHistory();
    }

    public Set<Object> enumerate (Schema schema) {
        Set<Object> enuObjects = new HashSet<>();

        if (schema instanceof StringSchema) {
            Set<String> enuStrings = this.stringEnumerator.enumerate((StringSchema)schema, this);
            Iterator<String> strIter = enuStrings.iterator();
            while(strIter.hasNext()) {
                enuObjects.add(strIter.next());
            }
        }
        else if (schema instanceof ObjectSchema) {
            Set<JSONObject> enuJSONs = this.objectEnumerator.enumerate((ObjectSchema)schema, this);
            Iterator<JSONObject> jsonIter = enuJSONs.iterator();
            while(jsonIter.hasNext()) {
                enuObjects.add(jsonIter.next());
            }
        }
        else {
            throw new WidUnsupportedClassException("Unsupported schema class: " + WidShortHands.getClassName(schema));
        }

        return enuObjects;
    }
}
