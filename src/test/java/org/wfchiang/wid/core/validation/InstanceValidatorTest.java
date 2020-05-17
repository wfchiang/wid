package org.wfchiang.wid.core.validation;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wfchiang.wid.core.TestingUtils;
import org.wfchiang.wid.core.enumeration.EnumerationContext;
import org.wfchiang.wid.core.enumeration.EnumerationHistory;
import org.wfchiang.wid.core.exception.WidValidationException;

import java.io.IOException;
import java.util.Map;

public class InstanceValidatorTest {

    EnumerationHistory enumerationHistory;
    EnumerationContext enumerationContext;

    @Before
    public void init() {
        this.enumerationHistory = new EnumerationHistory();
        this.enumerationContext = new EnumerationContext();
    }

    @Test
    public void validate_0 () throws IOException, JSONException {
        OpenAPI openAPI = TestingUtils.getOpenAPIFromClassPath("examples/ex0.yml");
        Map<String, Schema> componentSchemas = TestingUtils.getComponentSchemasFromOpenAPI(openAPI);
        ObjectSchema objectSchema0 = TestingUtils.getObjectSchemaFromDefinitions("Object0", componentSchemas);

        InstanceValidator instanceValidator = new InstanceValidator();
        ValidationResult validationResult;

        validationResult = instanceValidator.validate(openAPI, null, objectSchema0);
        Assert.assertNotNull(validationResult);
        Assert.assertEquals(ValidationStatus.PASS, validationResult.getValidationStatus());

        JSONObject enuJSON = new JSONObject();
        enuJSON.put("strKey0", null);
        enuJSON.put("strKey1", "value1");
        validationResult = instanceValidator.validate(openAPI, enuJSON, objectSchema0);
        Assert.assertNotNull(validationResult);
        Assert.assertEquals(ValidationStatus.FAIL, validationResult.getValidationStatus());

        enuJSON.put("strKey0", "123");
        validationResult = instanceValidator.validate(openAPI, enuJSON, objectSchema0);
        Assert.assertNotNull(validationResult);
        Assert.assertEquals(ValidationStatus.PASS, validationResult.getValidationStatus());

        enuJSON.put("strKey0", "1234");
        validationResult = instanceValidator.validate(openAPI, enuJSON, objectSchema0);
        Assert.assertNotNull(validationResult);
        Assert.assertEquals(ValidationStatus.FAIL, validationResult.getValidationStatus());
    }

    @Test(expected = WidValidationException.class)
    public void validate_e0 () throws IOException, JSONException {
        OpenAPI openAPI = TestingUtils.getOpenAPIFromClassPath("examples/ex0.yml");
        Map<String, Schema> componentSchemas = TestingUtils.getComponentSchemasFromOpenAPI(openAPI);
        ObjectSchema objectSchema0 = TestingUtils.getObjectSchemaFromDefinitions("Object0", componentSchemas);

        InstanceValidator instanceValidator = new InstanceValidator();
        instanceValidator.validate(openAPI, "123", objectSchema0);
    }
}
