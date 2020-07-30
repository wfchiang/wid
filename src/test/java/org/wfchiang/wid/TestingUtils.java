package org.wfchiang.wid;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.junit.Assert;
import org.wfchiang.wid.core.util.WidIOUtils;
import org.wfchiang.wid.core.util.WidOpenAPIUtils;

import java.io.IOException;
import java.util.Map;

public class TestingUtils {

    public static OpenAPI getOpenAPIFromClassPath (String resourceClassPath) throws IOException {
        Assert.assertNotNull(resourceClassPath);

        String content = WidIOUtils.readClassPathResourceContent(resourceClassPath);
        OpenAPI openAPI = WidOpenAPIUtils.getOpenAPIFromContent(content);
        Assert.assertNotNull(openAPI);
        return openAPI;
    }

    public static Map<String, Schema> getComponentSchemasFromOpenAPI (OpenAPI openAPI) {
        Assert.assertNotNull(openAPI);
        Assert.assertNotNull(openAPI.getComponents());
        Assert.assertNotNull(openAPI.getComponents().getSchemas());
        return openAPI.getComponents().getSchemas();
    }

    public static ObjectSchema getObjectSchemaFromDefinitions (String schemaName, Map<String, Schema> definitions) {
        Assert.assertNotNull(schemaName);
        Assert.assertNotNull(definitions);
        Assert.assertTrue(definitions.containsKey(schemaName));

        Schema schema = definitions.get(schemaName);
        Assert.assertNotNull(schema);
        Assert.assertTrue(schema instanceof ObjectSchema);
        return (ObjectSchema) schema;
    }

    public static StringSchema getStringSchemaFromDefinitions (String schemaName, Map<String, Schema> definitions) {
        Assert.assertNotNull(schemaName);
        Assert.assertNotNull(definitions);
        Assert.assertTrue(definitions.containsKey(schemaName));

        Schema schema = definitions.get(schemaName);
        Assert.assertNotNull(schema);
        Assert.assertTrue(schema instanceof StringSchema);
        return (StringSchema) schema;
    }
}
