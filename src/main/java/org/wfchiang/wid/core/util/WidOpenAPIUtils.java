package org.wfchiang.wid.core.util;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;

public class WidOpenAPIUtils {

    public static OpenAPI getOpenAPIFromContent (String content) {
        return new OpenAPIV3Parser().readContents(content).getOpenAPI();
    }
}
