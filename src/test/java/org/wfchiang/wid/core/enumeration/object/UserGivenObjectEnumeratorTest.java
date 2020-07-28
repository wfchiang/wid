package org.wfchiang.wid.core.enumeration.object;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wfchiang.wid.core.enumeration.EnumerationContext;
import org.wfchiang.wid.core.enumeration.EnumerationHistory;

import java.util.Collection;

public class UserGivenObjectEnumeratorTest {

    EnumerationHistory enumerationHistory;
    EnumerationContext enumerationContext;

    @Before
    public void init() {
        this.enumerationHistory = new EnumerationHistory();
        this.enumerationContext = new EnumerationContext();
    }

    @Test
    public void enumerate_0 () throws JSONException {
        UserGivenObjectEnumerator userGivenObjectEnumerator =
                new UserGivenObjectEnumerator();

        JSONObject jsonObject = new JSONObject("{\"key0\": \"value0\", \"key1\": {\"key10\": \"value10\"}}");
        userGivenObjectEnumerator.addObject(jsonObject);

        Collection<JSONObject> enuSet = userGivenObjectEnumerator.enumerate(null, null);
        Assert.assertNotNull(enuSet);
        Assert.assertEquals(1, enuSet.size());

        JSONObject root = enuSet.iterator().next();
        Assert.assertNotNull(root);
        Assert.assertTrue(root.has("key0"));
        Assert.assertTrue(root.has("key1"));

        Object object1 = root.get("key1");
        Assert.assertTrue(object1 instanceof JSONObject);

        JSONObject value1 = (JSONObject) object1;
        Assert.assertTrue(value1.has("key10"));
        Assert.assertEquals("value10", value1.get("key10"));
    }
}
