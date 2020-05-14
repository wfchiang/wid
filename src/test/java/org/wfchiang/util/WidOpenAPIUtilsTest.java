package org.wfchiang.util;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.junit.Test;
import org.junit.Assert;

import java.io.IOException;
import java.util.Map;

public class WidOpenAPIUtilsTest {

    @Test
    public void getOpenAPIFromContent_0 () throws IOException {
        String resourceClassPath = "examples/ex0.yml";
        String content = WidIOUtils.readClassPathResourceContent(resourceClassPath);
        OpenAPI openAPI = WidOpenAPIUtils.getOpenAPIFromContent(content);
        Assert.assertNotNull(openAPI);
        Assert.assertNotNull(openAPI.getComponents());
        Assert.assertNotNull(openAPI.getComponents().getSchemas());

        Map<String, Schema> schemas = openAPI.getComponents().getSchemas();

        String object0Name = "Object0";
        Assert.assertTrue(schemas.containsKey(object0Name));

        Schema schema0 = schemas.get(object0Name);
        Assert.assertTrue(schema0 instanceof ObjectSchema);

        ObjectSchema objectSchema0 = (ObjectSchema) schema0;
        Assert.assertNotNull(objectSchema0.getProperties());

        String key0Name = "strKey0";
        String key1Name = "strKey1";
        Map<String, Schema> schema0Properties = objectSchema0.getProperties();
        Assert.assertTrue(schema0Properties.containsKey(key0Name));
        Assert.assertTrue(schema0Properties.containsKey(key1Name));
        Assert.assertTrue(objectSchema0.getRequired().contains(key0Name));
        Assert.assertTrue(schema0Properties.get(key0Name) instanceof StringSchema);
        Assert.assertTrue(schema0Properties.get(key1Name) instanceof StringSchema);

        StringSchema stringSchema0 = (StringSchema) schema0Properties.get(key0Name);
        Assert.assertNotNull(stringSchema0.getMaxLength());
        Assert.assertEquals(10, stringSchema0.getMaxLength().intValue());

        StringSchema stringSchema1 = (StringSchema) schema0Properties.get(key1Name);
        Assert.assertNull(stringSchema1.getMaxLength());
    }
}
