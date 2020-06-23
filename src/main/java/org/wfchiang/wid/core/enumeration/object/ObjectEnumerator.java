package org.wfchiang.wid.core.enumeration.object;

import io.swagger.v3.oas.models.media.ObjectSchema;
import org.json.JSONObject;
import org.wfchiang.wid.core.enumeration.EnumerationContext;

import java.util.Set;

public interface ObjectEnumerator {
    public Set<JSONObject> enumerate (ObjectSchema objectSchema, EnumerationContext enumerationContext);
}
