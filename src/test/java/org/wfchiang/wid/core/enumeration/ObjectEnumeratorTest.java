package org.wfchiang.wid.core.enumeration;

import io.swagger.oas.inflector.examples.models.ObjectExample;
import io.swagger.oas.inflector.examples.models.StringExample;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wfchiang.wid.core.model.EnumerationContext;
import org.wfchiang.wid.core.model.ExampleHistory;

public class ObjectEnumeratorTest {

    ExampleHistory exampleHistory;
    EnumerationContext enumerationContext;

    @Before
    public void init() {
        this.exampleHistory = new ExampleHistory();
        this.enumerationContext = new EnumerationContext(this.exampleHistory);
    }

    @Test
    public void enumerate_0 () throws JSONException {
        StringExample stringExample0 = new StringExample("value0");
        StringExample stringExample1 = new StringExample("value1");
        ObjectExample objectExample = new ObjectExample();
        objectExample.put("key0", stringExample0);
        objectExample.put("key1", stringExample1);

        ObjectEnumerator objectEnumerator = new ObjectEnumerator();

        Object enuObject;

        enuObject = objectEnumerator.enumerate(stringExample0, this.enumerationContext);
        Assert.assertNotNull(enuObject);
        Assert.assertTrue(enuObject instanceof String);
        Assert.assertEquals("value0", (String)enuObject);

        enuObject = objectEnumerator.enumerate(objectExample, this.enumerationContext);
        Assert.assertNotNull(enuObject);
        Assert.assertTrue(enuObject instanceof JSONObject);

        JSONObject jsonObject = (JSONObject) enuObject;
        Assert.assertTrue(jsonObject.has("key0"));
        Assert.assertEquals("value0", jsonObject.get("key0"));
        Assert.assertTrue(jsonObject.has("key1"));
        Assert.assertEquals("value1", jsonObject.get("key1"));
    }
}
