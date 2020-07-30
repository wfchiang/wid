package org.wfchiang.wid.core.enumeration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wfchiang.wid.TestingUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class EnumerationContextTest {

    EnumerationHistory enumerationHistory;
    EnumerationContext enumerationContext;

    @Before
    public void init() {
        this.enumerationHistory = new EnumerationHistory();
        this.enumerationContext = new EnumerationContext();
    }

    @Test
    public void findSchemaByKey_0 () throws IOException {
        OpenAPI openAPI = TestingUtils.getOpenAPIFromClassPath("examples/ex0.yml");

        Map<String, Schema> componentSchemas = TestingUtils.getComponentSchemasFromOpenAPI(openAPI);

        ObjectSchema rootSchema = TestingUtils.getObjectSchemaFromDefinitions("Object1", componentSchemas);

        EnumerationContext econtext = new EnumerationContext(openAPI);

        Schema returned;
        List<String> key = new ArrayList<>();

        key.add("strKey0");
        returned = econtext.findSchemaByKey(rootSchema, key);
        Assert.assertNotNull(returned);
        Assert.assertTrue(returned instanceof StringSchema);

        key = new ArrayList<>();
        key.add("objKey0");
        returned = econtext.findSchemaByKey(rootSchema, key);
        Assert.assertNotNull(returned);
        Assert.assertTrue(returned instanceof ObjectSchema);
        key.add("strKey00");
        returned = econtext.findSchemaByKey(rootSchema, key);
        Assert.assertNotNull(returned);
        Assert.assertTrue(returned instanceof StringSchema);

        key = new ArrayList<>();
        key.add("objKey1");
        key.add("strKey1");
        returned = econtext.findSchemaByKey(rootSchema, key);
        Assert.assertNotNull(returned);
        Assert.assertTrue(returned instanceof StringSchema);

        StringSchema stringSchema = (StringSchema) returned;
        Assert.assertNotNull(stringSchema.getExample());
        Assert.assertTrue(stringSchema.getExample() instanceof String);
        String exampleString = (String) stringSchema.getExample();
        Assert.assertEquals("strValue1", exampleString);
    }

    @Test
    public void enumerate_0 () throws IOException, JSONException {
        OpenAPI openAPI = TestingUtils.getOpenAPIFromClassPath("examples/ex0.yml");

        Map<String, Schema> componentSchemas = TestingUtils.getComponentSchemasFromOpenAPI(openAPI);

        ObjectSchema objectSchema0 = TestingUtils.getObjectSchemaFromDefinitions("Object0", componentSchemas);

        Collection<Object> enuObjects = this.enumerationContext.enumerate(objectSchema0);
        Assert.assertNotNull(enuObjects);
        Assert.assertEquals(1, enuObjects.size());

        Object enuObject = enuObjects.iterator().next();
        Assert.assertNotNull(enuObject);
        Assert.assertTrue(enuObject instanceof JSONObject);
        JSONObject enuJSON = (JSONObject) enuObject;
        Assert.assertNotNull(enuJSON);
        Assert.assertTrue(enuJSON.has("strKey0"));
        Assert.assertTrue(enuJSON.has("strKey1"));
        Assert.assertEquals("", enuJSON.get("strKey0"));
        Assert.assertEquals("", enuJSON.get("strKey1"));
    }
}
