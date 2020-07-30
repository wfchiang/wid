package org.wfchiang.wid.core.util;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.junit.Test;
import org.junit.Assert;
import org.wfchiang.wid.TestingUtils;

import java.io.IOException;
import java.util.Map;

public class WidOpenAPIUtilsTest {

    @Test
    public void getOpenAPIFromContent_0 () throws IOException {
        OpenAPI openAPI = TestingUtils.getOpenAPIFromClassPath("examples/ex0.yml");

        Map<String, Schema> componentSchemas = TestingUtils.getComponentSchemasFromOpenAPI(openAPI);

        ObjectSchema objectSchema0 = TestingUtils.getObjectSchemaFromDefinitions("Object0", componentSchemas);

        Map<String, Schema> schema0Properties = objectSchema0.getProperties();

        StringSchema stringSchema0 = TestingUtils.getStringSchemaFromDefinitions("strKey0", schema0Properties);
        Assert.assertNotNull(stringSchema0.getMaxLength());
        Assert.assertEquals(3, stringSchema0.getMaxLength().intValue());
        Assert.assertNull(stringSchema0.getExample());

        StringSchema stringSchema1 = TestingUtils.getStringSchemaFromDefinitions("strKey1", schema0Properties);
        Assert.assertNull(stringSchema1.getMaxLength());
        Assert.assertNotNull(stringSchema1.getExample());
    }
}
