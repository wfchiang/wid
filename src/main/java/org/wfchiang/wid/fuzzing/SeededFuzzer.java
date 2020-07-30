package org.wfchiang.wid.fuzzing;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.ObjectSchema;
import org.json.JSONObject;
import org.wfchiang.wid.core.testing.TestCase;

import java.util.Collection;

public abstract class SeededFuzzer extends Fuzzer {

    protected  ObjectSchema seedSchema;
    protected JSONObject seedObject;

    public SeededFuzzer (OpenAPI openAPI, ObjectSchema seedSchema, JSONObject seedObject) {
        super(openAPI);
        this.setSeedSchema(seedSchema);
        this.setSeedObject(seedObject);
    }

    public ObjectSchema getSeedSchema() {
        return seedSchema;
    }

    public void setSeedSchema(ObjectSchema seedSchema) {
        if (seedSchema == null) {
            throw new IllegalArgumentException("seed schema cannot be null");
        }
        this.seedSchema = seedSchema;
    }

    public JSONObject getSeedObject() {
        return seedObject;
    }

    public void setSeedObject(JSONObject seedObject) {
        if (seedObject == null) {
            throw new IllegalArgumentException("seed object cannot be null");
        }
        this.seedObject = seedObject;
    }
}
