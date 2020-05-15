package org.wfchiang.wid.core.util;

import com.fasterxml.jackson.databind.module.SimpleModule;
import io.swagger.oas.inflector.examples.ExampleBuilder;
import io.swagger.oas.inflector.examples.models.Example;
import io.swagger.oas.inflector.examples.models.ObjectExample;
import io.swagger.oas.inflector.examples.models.StringExample;
import io.swagger.oas.inflector.processors.JsonNodeExampleSerializer;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.parser.OpenAPIV3Parser;
import org.json.JSONObject;
import org.wfchiang.wid.core.exception.WidUnsupportedClassException;

import javax.annotation.PostConstruct;
import java.util.Map;

public class WidOpenAPIUtils {

    private static SimpleModule JSON_SIMPLE_MODULE = new SimpleModule().addSerializer(new JsonNodeExampleSerializer());

    @PostConstruct
    public void postConstruct () {
        Json.mapper().registerModule(JSON_SIMPLE_MODULE);
    }

    public static OpenAPI getOpenAPIFromContent (String content) {
        return new OpenAPIV3Parser().readContents(content).getOpenAPI();
    }

    public static Example createJsonExampleFromSchema (Schema targetedSchema, Map<String, Schema> definitions) {
        Example example = ExampleBuilder.fromSchema(targetedSchema, definitions);
        return example;
    }

    public static String createJsonExampleStringFromSchema (Schema targetedSchema, Map<String, Schema> definitions) {
        Example example = WidOpenAPIUtils.createJsonExampleFromSchema(targetedSchema, definitions);
        return Json.pretty(example);
    }

    public static Object extractObjectExample (ObjectExample objectExample) {
        JSONObject jsonObject = new JSONObject();

        Map<String, Example> values = objectExample.getValues();
        for (String key : values.keySet()) {
            jsonObject.put(key, WidOpenAPIUtils.extractExample(values.get(key)));
        }

        return jsonObject;
    }

    public static String extractStringExample (StringExample stringExample) {
        return stringExample.getValue();
    }

    public static Object extractExample (Example example) {
        if (example instanceof StringExample) {
            return WidOpenAPIUtils.extractStringExample((StringExample)example);
        }
        else if (example instanceof ObjectExample) {
            return WidOpenAPIUtils.extractObjectExample((ObjectExample)example);
        }
        else {
            throw new WidUnsupportedClassException("Unsupport Example class " + example.getClass().getName());
        }
    }
}
