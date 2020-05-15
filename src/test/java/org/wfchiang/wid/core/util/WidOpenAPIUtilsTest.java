package org.wfchiang.wid.core.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.oas.inflector.examples.models.Example;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.Assert;

import java.io.IOException;
import java.util.Map;

public class WidOpenAPIUtilsTest {

    private OpenAPI getOpenAPIFromClassPath (String resourceClassPath) throws IOException {
        Assert.assertNotNull(resourceClassPath);

        String content = WidIOUtils.readClassPathResourceContent(resourceClassPath);
        OpenAPI openAPI = WidOpenAPIUtils.getOpenAPIFromContent(content);
        Assert.assertNotNull(openAPI);
        return openAPI;
    }

    private Map<String, Schema> getComponentSchemasFromOpenAPI (OpenAPI openAPI) {
        Assert.assertNotNull(openAPI);
        Assert.assertNotNull(openAPI.getComponents());
        Assert.assertNotNull(openAPI.getComponents().getSchemas());
        return openAPI.getComponents().getSchemas();
    }

    private ObjectSchema getObjectSchemaFromDefinitions (String schemaName, Map<String, Schema> definitions) {
        Assert.assertNotNull(schemaName);
        Assert.assertNotNull(definitions);
        Assert.assertTrue(definitions.containsKey(schemaName));

        Schema schema = definitions.get(schemaName);
        Assert.assertNotNull(schema);
        Assert.assertTrue(schema instanceof ObjectSchema);
        return (ObjectSchema) schema;
    }

    private StringSchema getStringSchemaFromDefinitions (String schemaName, Map<String, Schema> definitions) {
        Assert.assertNotNull(schemaName);
        Assert.assertNotNull(definitions);
        Assert.assertTrue(definitions.containsKey(schemaName));

        Schema schema = definitions.get(schemaName);
        Assert.assertNotNull(schema);
        Assert.assertTrue(schema instanceof StringSchema);
        return (StringSchema) schema;
    }

    @Test
    public void getOpenAPIFromContent_0 () throws IOException {
        OpenAPI openAPI = this.getOpenAPIFromClassPath("examples/ex0.yml");

        Map<String, Schema> componentSchemas = this.getComponentSchemasFromOpenAPI(openAPI);

        ObjectSchema objectSchema0 = this.getObjectSchemaFromDefinitions("Object0", componentSchemas);

        Map<String, Schema> schema0Properties = objectSchema0.getProperties();

        StringSchema stringSchema0 = this.getStringSchemaFromDefinitions("strKey0", schema0Properties);
        Assert.assertNotNull(stringSchema0.getMaxLength());
        Assert.assertEquals(3, stringSchema0.getMaxLength().intValue());
        Assert.assertNull(stringSchema0.getExample());

        StringSchema stringSchema1 = this.getStringSchemaFromDefinitions("strKey1", schema0Properties);
        Assert.assertNull(stringSchema1.getMaxLength());
        Assert.assertNotNull(stringSchema1.getExample());
    }

    @Test
    public void createJsonExampleFromSchema_0 () throws IOException, JSONException {
        OpenAPI openAPI = this.getOpenAPIFromClassPath("examples/ex0.yml");

        Map<String, Schema> componentSchemas = this.getComponentSchemasFromOpenAPI(openAPI);

        ObjectSchema objectSchema0 = this.getObjectSchemaFromDefinitions("Object0", componentSchemas);

        Example example0 = WidOpenAPIUtils.createJsonExampleFromSchema(objectSchema0, componentSchemas);
        Assert.assertNotNull(example0);

        String exampleString0 = WidOpenAPIUtils.createJsonExampleStringFromSchema(objectSchema0, componentSchemas);
        Assert.assertNotNull(exampleString0);

        Object object0 = WidOpenAPIUtils.extractExample(example0);
        Assert.assertNotNull(object0);
        Assert.assertNotNull(object0 instanceof JSONObject);

        JSONObject jsonObject0 = (JSONObject) object0;
        Assert.assertTrue(jsonObject0.has("strKey1"));
        Assert.assertEquals("strValue1", jsonObject0.get("strKey1"));
    }
}
