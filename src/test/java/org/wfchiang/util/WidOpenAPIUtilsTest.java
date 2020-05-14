package org.wfchiang.util;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.Test;
import org.springframework.util.Assert;

import java.io.IOException;

public class WidOpenAPIUtilsTest {

    @Test
    public void getOpenAPIFromContent_0 () throws IOException {
        String resourceClassPath = "examples/ex0.yml";
        String content = WidIOUtils.readClassPathResourceContent(resourceClassPath);
        OpenAPI openAPI = WidOpenAPIUtils.getOpenAPIFromContent(content);
        Assert.notNull(openAPI, "openAPI is null");
    }
}
