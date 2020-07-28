package org.wfchiang.wid.core.enumeration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wfchiang.wid.core.TestingUtils;

import java.io.IOException;
import java.util.Collection;
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
