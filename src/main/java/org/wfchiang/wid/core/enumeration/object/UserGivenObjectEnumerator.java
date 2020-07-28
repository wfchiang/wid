package org.wfchiang.wid.core.enumeration.object;

import io.swagger.v3.oas.models.media.ObjectSchema;
import org.json.JSONObject;
import org.wfchiang.wid.core.enumeration.EnumerationContext;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserGivenObjectEnumerator implements ObjectEnumerator {

    private Collection<JSONObject> givenObjects;

    public UserGivenObjectEnumerator () {
        this.givenObjects = new HashSet<>();
    }

    public void addObject (JSONObject jsonObject) {
        this.givenObjects.add(jsonObject);
    }

    @Override
    public Collection<JSONObject> enumerate(ObjectSchema objectSchema, EnumerationContext enumerationContext) {
        return this.givenObjects;
    }
}
