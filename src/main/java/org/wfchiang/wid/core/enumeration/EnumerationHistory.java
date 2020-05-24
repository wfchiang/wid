package org.wfchiang.wid.core.enumeration;

import io.swagger.v3.oas.models.media.Schema;
import org.wfchiang.wid.core.util.WidOpenAPIUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class EnumerationHistory {
    private Map<String, HashSet<Object>> previousEnumerations = new HashMap<String, HashSet<Object>>();

    public void add (Schema schema, Object enu) {
        String hashCode = WidOpenAPIUtils.getSchemaHashCode(schema);
        if (!this.previousEnumerations.containsKey(hashCode)) {
            this.previousEnumerations.put(hashCode, new HashSet<Object>());
        }
        this.previousEnumerations.get(hashCode).add(enu);
    }

    public int size (Schema schema) {
        String hashCode = WidOpenAPIUtils.getSchemaHashCode(schema);
        if (!this.previousEnumerations.containsKey(hashCode)) {
            return 0;
        }
        return this.previousEnumerations.get(hashCode).size();
    }
}
