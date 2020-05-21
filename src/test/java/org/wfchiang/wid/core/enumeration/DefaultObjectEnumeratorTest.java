package org.wfchiang.wid.core.enumeration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.parser.util.ResolverFully;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wfchiang.wid.core.TestingUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefaultObjectEnumeratorTest {

    EnumerationHistory enumerationHistory;
    EnumerationContext enumerationContext;

    @Before
    public void init() {
        this.enumerationHistory = new EnumerationHistory();
        this.enumerationContext = new EnumerationContext();
    }

    @Test
    public void enumerate_0 () throws IOException, JSONException {
        OpenAPI openAPI = TestingUtils.getOpenAPIFromClassPath("examples/ex0.yml");

        Map<String, Schema> componentSchemas = TestingUtils.getComponentSchemasFromOpenAPI(openAPI);

        ObjectSchema objectSchema0 = TestingUtils.getObjectSchemaFromDefinitions("Object0", componentSchemas);

        DefaultObjectEnumerator defaultObjectEnumerator = new DefaultObjectEnumerator();
        Set<JSONObject> enuObjects = defaultObjectEnumerator.enumerate(objectSchema0, this.enumerationContext);
        Assert.assertNotNull(enuObjects);
        Assert.assertEquals(1, enuObjects.size());

        JSONObject enuJSON = enuObjects.iterator().next();
        Assert.assertNotNull(enuJSON);
        Assert.assertTrue(enuJSON.has("strKey0"));
        Assert.assertTrue(enuJSON.has("strKey1"));
        Assert.assertEquals("", enuJSON.get("strKey0"));
        Assert.assertEquals("", enuJSON.get("strKey1"));
    }

    @Test
    public void enumerate_1 () throws IOException, JSONException {
        ObjectSchema objectSchema = new ObjectSchema();
        Assert.assertNull(objectSchema.getProperties());

        Set<JSONObject> enuObjects = new DefaultObjectEnumerator().enumerate(objectSchema, this.enumerationContext);
        Assert.assertNotNull(enuObjects);
        Assert.assertEquals(0, enuObjects.size());
    }

    @Test
    public void enumerate_2 () throws IOException, JSONException {
        ObjectSchema objectSchema = new ObjectSchema();
        Map<String, Schema> properties = new HashMap<>();
        objectSchema.setProperties(properties);

        Set<JSONObject> enuObjects = new DefaultObjectEnumerator().enumerate(objectSchema, this.enumerationContext);
        Assert.assertNotNull(enuObjects);
        Assert.assertEquals(1, enuObjects.size());

        JSONObject enuJSON = enuObjects.iterator().next();
        Assert.assertFalse(enuJSON.keys().hasNext());
    }

    @Test
    public void enumerate_3 () throws IOException, JSONException {
        OpenAPI openAPI = TestingUtils.getOpenAPIFromClassPath("examples/ex0.yml");

        Map<String, Schema> componentSchemas = TestingUtils.getComponentSchemasFromOpenAPI(openAPI);

        ObjectSchema objectSchema1 = TestingUtils.getObjectSchemaFromDefinitions("Object1", componentSchemas);

        EnumerationContext enumerationContext = new EnumerationContext(openAPI);
        Set<JSONObject> enuObjects = new DefaultObjectEnumerator().enumerate(objectSchema1, enumerationContext);
        Assert.assertNotNull(enuObjects);
        Assert.assertEquals(1, enuObjects.size());

        JSONObject enuJSON = enuObjects.iterator().next();

        System.out.println(enuJSON.toString());

        Assert.assertNotNull(enuJSON);
        Assert.assertTrue(enuJSON.has("strKey0"));
        Assert.assertEquals("", ((String)enuJSON.get("strKey0")));
        Assert.assertTrue(enuJSON.has("objKey0"));
        Assert.assertTrue(enuJSON.get("objKey0") instanceof JSONObject);
        Assert.assertTrue(((JSONObject)enuJSON.get("objKey0")).has("strKey00"));
        Assert.assertEquals("", ((JSONObject)((JSONObject)enuJSON.get("objKey0"))).get("strKey00"));
        Assert.assertTrue(enuJSON.has("objKey1"));
        Assert.assertTrue(enuJSON.get("objKey1") instanceof JSONObject);
        Assert.assertTrue(((JSONObject)enuJSON.get("objKey1")).has("strKey0"));
        Assert.assertEquals("", ((JSONObject)((JSONObject)enuJSON.get("objKey1"))).get("strKey1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void enumerate_e0 () throws IOException, JSONException {
        DefaultObjectEnumerator defaultObjectEnumerator = new DefaultObjectEnumerator();
        defaultObjectEnumerator.enumerate(null, this.enumerationContext);
    }

    @Test(expected = IllegalArgumentException.class)
    public void enumerate_e1 () throws IOException, JSONException {
        DefaultObjectEnumerator defaultObjectEnumerator = new DefaultObjectEnumerator();
        defaultObjectEnumerator.enumerate(new ObjectSchema(), null);
    }
}
