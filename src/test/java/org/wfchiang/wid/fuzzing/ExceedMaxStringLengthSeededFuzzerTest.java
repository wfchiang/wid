package org.wfchiang.wid.fuzzing;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wfchiang.wid.TestingUtils;
import org.wfchiang.wid.core.enumeration.EnumerationContext;
import org.wfchiang.wid.core.enumeration.object.DefaultObjectEnumerator;
import org.wfchiang.wid.core.enumeration.string.DefaultFixedLengthStringEnumerator;
import org.wfchiang.wid.core.enumeration.string.FixedLengthStringEnumerator;
import org.wfchiang.wid.core.testing.TestCase;
import org.wfchiang.wid.core.testing.TestStatus;
import org.wfchiang.wid.core.util.WidShortHands;

import java.io.IOException;
import java.util.*;

public class ExceedMaxStringLengthSeededFuzzerTest {

    OpenAPI openAPI0;
    ObjectSchema object1Schema;
    JSONObject seedObject1;

    @Before
    public void init() throws IOException {
        this.openAPI0 = TestingUtils.getOpenAPIFromClassPath("examples/ex0.yml");

        Map<String, Schema> componentSchemas = TestingUtils.getComponentSchemasFromOpenAPI(openAPI0);
        this.object1Schema = TestingUtils.getObjectSchemaFromDefinitions("Object1", componentSchemas);

        EnumerationContext enumerationContext = new EnumerationContext(openAPI0);
        DefaultFixedLengthStringEnumerator defaultFixedLengthStringEnumerator
                = new DefaultFixedLengthStringEnumerator(2, 'J');
        enumerationContext.setStringEnumerator(defaultFixedLengthStringEnumerator);

        Collection<JSONObject> enuObjects = new DefaultObjectEnumerator().enumerate(object1Schema, enumerationContext);
        this.seedObject1 = enuObjects.iterator().next();
    }

    @Test
    public void fuzz_0 () throws JSONException {
        System.out.println("==== Seed Object ====");
        System.out.println(seedObject1.toString(4));

        DefaultFixedLengthStringEnumerator stringEnumerator =
                new DefaultFixedLengthStringEnumerator();
        stringEnumerator.setDefaultChar('X');

        // Fuzz for an unbounded field
        List<String> fieldKey = Arrays.asList(new String[]{"objKey1", "strKey1"});
        ExceedMaxStringLengthSeededFuzzer fuzzer =
                new ExceedMaxStringLengthSeededFuzzer(
                        this.openAPI0,
                        this.object1Schema,
                        this.seedObject1,
                        fieldKey,
                        stringEnumerator);

        Collection<TestCase> testCases = fuzzer.fuzz();

        Assert.assertNotNull(testCases);
        Assert.assertEquals(0, testCases.size());

        // Fuzz for a bounded field
        fieldKey = Arrays.asList(new String[]{"objKey1", "strKey0"});
        fuzzer.setFieldKey(fieldKey);

        testCases = fuzzer.fuzz();

        Assert.assertNotNull(testCases);
        Assert.assertEquals(1, testCases.size());

        TestCase testCase = testCases.iterator().next();
        Assert.assertEquals(TestStatus.FAIL, testCase.getExpectedStatus());
        String old_value = (String) WidShortHands.readJSONObject(seedObject1, fieldKey);
        String new_value = (String) WidShortHands.readJSONObject(testCase.getInputInstance(), fieldKey);
        Assert.assertTrue(old_value.length() <= 3);
        Assert.assertTrue(new_value.length() > 3);

        System.out.println("==== Fuzzed Object ====");
        System.out.println(testCase.getInputInstance().toString(4));
    }
}
