package org.wfchiang.wid.core.validation;

import com.atlassian.oai.validator.report.MessageResolver;
import com.atlassian.oai.validator.report.ValidationReport;
import com.atlassian.oai.validator.schema.SchemaValidator;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.json.JSONObject;
import org.wfchiang.wid.core.exception.WidUnsupportedClassException;
import org.wfchiang.wid.core.exception.WidValidationException;
import org.wfchiang.wid.core.util.WidShortHands;

public class InstanceValidator {

    private MessageResolver messageResolver;

    public InstanceValidator () {
        this.messageResolver = new MessageResolver();
    }

    public ValidationResult validate (OpenAPI openAPI, Object instance, Schema schema) {
        if (schema == null) {
            throw new IllegalArgumentException("Cannot validate with null as schema");
        }

        SchemaValidator schemaValidator = new SchemaValidator(openAPI, this.messageResolver);
        ValidationStatus validationStatus = ValidationStatus.NOT_AVAILABLE;
        ValidationResult validationResult = new ValidationResult(validationStatus);

        String instanceString = null;

        if (instance == null) {
            Boolean isNullable = schema.getNullable();
            if (isNullable == null || isNullable.booleanValue()) {
                validationResult.setValidationStatus(ValidationStatus.PASS);
            }
            else {
                validationResult.setValidationStatus(ValidationStatus.FAIL);
            }
        }
        else if (instance instanceof String) {
            if (!(schema instanceof StringSchema)) {
                throw new WidValidationException("Invalid schema for String");
            }
            instanceString = (String) instance;
        }
        else if (instance instanceof JSONObject) {
            if (!(schema instanceof ObjectSchema)) {
                throw new WidValidationException("Invalid schema for JSONObject");
            }
            instanceString = ((JSONObject)instance).toString();
        }
        else {
            throw new WidUnsupportedClassException("Unsupported instance class " + WidShortHands.getClassName(instance) + " for validation");
        }

        if (ValidationStatus.NOT_AVAILABLE == validationResult.getValidationStatus()) {
            if (instanceString == null) {
                throw new WidValidationException("Cannot validate with null instanceString");
            }
            ValidationReport validationReport = schemaValidator.validate(instanceString, schema, null);
            validationResult.setValidationStatus(validationReport.hasErrors() ? ValidationStatus.FAIL : ValidationStatus.PASS);
            validationResult.setValidationReport(validationReport);
        }

        return validationResult;
    }
}
