package org.wfchiang.wid.core.enumeration.object;

import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import org.json.JSONObject;
import org.wfchiang.wid.core.enumeration.EnumerationContext;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DefaultObjectEnumerator implements ObjectEnumerator {
    @Override
    public Set<JSONObject> enumerate(ObjectSchema objectSchema, EnumerationContext enumerationContext) {
        if (objectSchema == null || enumerationContext == null) {
            throw new IllegalArgumentException("objectSchema or context is null");
        }

        Set<JSONObject> objectSet = new HashSet<>();
        Map<String, Schema> properties = objectSchema.getProperties();
        if (properties != null) {
            JSONObject jsonObject = new JSONObject();
            for (String key : properties.keySet()) {
                Schema valueSchema = properties.get(key);
                Set<Object> enuObjects = enumerationContext.enumerate(valueSchema);
                if (enuObjects != null && enuObjects.size() > 0) {
                    jsonObject.put(key, enuObjects.iterator().next());
                }
            }
            objectSet.add(jsonObject);
        }

        return objectSet;
    }
}
