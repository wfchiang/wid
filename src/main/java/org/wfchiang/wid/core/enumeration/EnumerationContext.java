package org.wfchiang.wid.core.enumeration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.parser.util.ResolverFully;
import org.json.JSONObject;
import org.wfchiang.wid.core.enumeration.object.DefaultObjectEnumerator;
import org.wfchiang.wid.core.enumeration.object.ObjectEnumerator;
import org.wfchiang.wid.core.enumeration.string.DefaultStringEnumerator;
import org.wfchiang.wid.core.enumeration.string.StringEnumerator;
import org.wfchiang.wid.core.exception.WidSchemaException;
import org.wfchiang.wid.core.exception.WidUnsupportedClassException;
import org.wfchiang.wid.core.util.WidShortHands;

import java.util.*;

public class EnumerationContext {

    private final OpenAPI openAPI;
    private final ResolverFully resolverFully;
    private StringEnumerator stringEnumerator;
    private ObjectEnumerator objectEnumerator;
    private EnumerationHistory enumerationHistory;

    public EnumerationContext () {
        this.openAPI = null;
        this.resolverFully = new ResolverFully();
        init();
    }

    public EnumerationContext (OpenAPI openAPI) {
        this.openAPI = openAPI;
        this.resolverFully = new ResolverFully();
        if (this.openAPI != null) {
            this.resolverFully.resolveFully(this.openAPI);
        }
        init();
    }

    private void init () {
        this.stringEnumerator = new DefaultStringEnumerator();
        this.objectEnumerator = new DefaultObjectEnumerator();
        this.enumerationHistory = new EnumerationHistory();
    }

    public Schema findSchemaByKey (Schema rootSchema, List<String> key) {
        if (rootSchema == null) {
            throw new IllegalArgumentException("rootSchema cannot be null");
        }
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }

        if (key.size() == 0) {
            return rootSchema;
        }

        if (!(rootSchema instanceof ObjectSchema)) {
            throw new WidSchemaException("rootSchema needs to be an ObjectSchema");
        }

        ObjectSchema fullyResolvedObjectSchema = (this.openAPI != null ? (ObjectSchema) this.resolverFully.resolveSchema(rootSchema) : (ObjectSchema) rootSchema);

        String keyElement = key.get(0);

        Map<String, Schema> properties = fullyResolvedObjectSchema.getProperties();
        if (!properties.containsKey(keyElement)) {
            throw new WidSchemaException("Invalid key element \"" + keyElement + "\"");
        }

        return this.findSchemaByKey(properties.get(keyElement), key.subList(1, key.size()));
    }

    public Collection<Object> enumerate (Schema schema) {
        Schema fullyResolvedSchema = (this.openAPI != null ? this.resolverFully.resolveSchema(schema) : schema);
        return this.enumerateWithFullyResolvedSchema(fullyResolvedSchema);
    }

    public Collection<Object> enumerateWithFullyResolvedSchema (Schema schema) {
        Collection<Object> enuObjects = new HashSet<>();

        if (schema instanceof StringSchema) {
            Collection<String> enuStrings = this.stringEnumerator.enumerate((StringSchema)schema, this);
            Iterator<String> strIter = enuStrings.iterator();
            while(strIter.hasNext()) {
                enuObjects.add(strIter.next());
            }
        }
        else if (schema instanceof ObjectSchema) {
            Collection<JSONObject> enuJSONs = this.objectEnumerator.enumerate((ObjectSchema)schema, this);
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

    public OpenAPI getOpenAPI() {
        return openAPI;
    }

    public ResolverFully getResolverFully() {
        return resolverFully;
    }

    public StringEnumerator getStringEnumerator() {
        return stringEnumerator;
    }

    public void setStringEnumerator(StringEnumerator stringEnumerator) {
        this.stringEnumerator = stringEnumerator;
    }

    public ObjectEnumerator getObjectEnumerator() {
        return objectEnumerator;
    }

    public void setObjectEnumerator(ObjectEnumerator objectEnumerator) {
        this.objectEnumerator = objectEnumerator;
    }

    public EnumerationHistory getEnumerationHistory() {
        return enumerationHistory;
    }

    public void setEnumerationHistory(EnumerationHistory enumerationHistory) {
        this.enumerationHistory = enumerationHistory;
    }
}
