package org.wfchiang.wid.fuzzing;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.json.JSONObject;
import org.wfchiang.wid.core.enumeration.EnumerationContext;
import org.wfchiang.wid.core.enumeration.string.FixedLengthStringEnumerator;
import org.wfchiang.wid.core.exception.WidFuzzerException;
import org.wfchiang.wid.core.testing.TestCase;
import org.wfchiang.wid.core.testing.TestStatus;
import org.wfchiang.wid.core.util.WidShortHands;

import java.util.*;

public class ExceedMaxStringLengthSeededFuzzer extends SeededFuzzer {

    private List<String> fieldKey;
    private FixedLengthStringEnumerator stringEnumerator;

    public ExceedMaxStringLengthSeededFuzzer(
            OpenAPI openAPI,
            ObjectSchema seedSchema,
            JSONObject seedObject,
            List<String> fieldKey,
            FixedLengthStringEnumerator stringEnumerator) {
        super(openAPI, seedSchema, seedObject);
        this.setFieldKey(fieldKey);
        this.setStringEnumerator(stringEnumerator);
    }

    @Override
    public Collection<TestCase> fuzz() {
        EnumerationContext enumerationContext = new EnumerationContext(this.openAPI);

        Schema targetedSchema = enumerationContext.findSchemaByKey(this.seedSchema, this.fieldKey);
        if (!(targetedSchema instanceof StringSchema)) {
            throw new WidFuzzerException("field key doesn't point to a string field");
        }
        StringSchema targetedStringSchema = (StringSchema) targetedSchema;

        // Enumerate the candidate strings
        String seedString = this.seedObject.toString();
        Collection<TestCase> testCases = new HashSet<>();
        if (targetedStringSchema.getMaxLength() != null) {
            this.stringEnumerator.setStringLength(targetedStringSchema.getMaxLength().intValue() + 1);
            Collection<String> candStrings = this.stringEnumerator.enumerate(targetedStringSchema, enumerationContext);
            Iterator<String> iter = candStrings.iterator();
            while (iter.hasNext()) {
                JSONObject newObj = new JSONObject(seedString);
                WidShortHands.writeJSONObject(newObj, this.fieldKey, iter.next());
                testCases.add(new TestCase(TestStatus.FAIL, newObj));
            }
        }

        return testCases;
    }

    /*
    Setters and Getters
     */
    public List<String> getFieldKey() {
        return fieldKey;
    }

    public void setFieldKey(List<String> fieldKey) {
        if (fieldKey == null) {
            throw new IllegalArgumentException("field key cannot be null");
        }
        this.fieldKey = fieldKey;
    }

    public FixedLengthStringEnumerator getStringEnumerator() {
        return stringEnumerator;
    }

    public void setStringEnumerator(FixedLengthStringEnumerator stringEnumerator) {
        if (stringEnumerator == null) {
            throw new IllegalArgumentException("string enumerator cannot be null");
        }
        this.stringEnumerator = stringEnumerator;
    }
}
