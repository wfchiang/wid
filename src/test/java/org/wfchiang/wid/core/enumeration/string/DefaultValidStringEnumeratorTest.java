package org.wfchiang.wid.core.enumeration.string;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.junit.Assert;
import org.junit.Test;
import org.wfchiang.wid.TestingUtils;
import org.wfchiang.wid.core.enumeration.EnumerationContext;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public class DefaultValidStringEnumeratorTest {
    @Test
    public void enumerate_0 () throws IOException {

        OpenAPI openAPI = TestingUtils.getOpenAPIFromClassPath("examples/ex0.yml");
        Map<String, Schema> componentSchemas = TestingUtils.getComponentSchemasFromOpenAPI(openAPI);
        ObjectSchema objectSchema0 = TestingUtils.getObjectSchemaFromDefinitions("Object0", componentSchemas);
        ObjectSchema objectSchema1 = TestingUtils.getObjectSchemaFromDefinitions("Object1", componentSchemas);
        StringSchema schemaO0S1 = (StringSchema) objectSchema0.getProperties().get("strKey1");
        StringSchema schemaO1S0 = (StringSchema) objectSchema1.getProperties().get("strKey0");

        EnumerationContext enumerationContext = new EnumerationContext(openAPI);
        DefaultValidStringEnumerator defaultValidStringEnumerator = new DefaultValidStringEnumerator();

        Collection<String> enuSet;

        enuSet = defaultValidStringEnumerator.enumerate(schemaO0S1, enumerationContext);
        Assert.assertEquals(1, enuSet.size());
        Assert.assertEquals(null, enuSet.iterator().next());

        enuSet = defaultValidStringEnumerator.enumerate(schemaO1S0, enumerationContext);
        Assert.assertEquals(1, enuSet.size());
        Assert.assertEquals("aa", enuSet.iterator().next());
    }
}
