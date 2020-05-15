package org.wfchiang.wid.core.util;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class WidIOUtilsTest {

    @Test
    public void cloneJSONObject_0 () throws JSONException {
        JSONObject jsonObject0 = new JSONObject();
        jsonObject0.put("key", "value0");

        JSONObject jsonObject1 = WidIOUtils.cloneJSONObject(jsonObject0);
        jsonObject1.put("key", "value1");

        Assert.assertEquals("value0", jsonObject0.get("key"));
        Assert.assertEquals("value1", jsonObject1.get("key"));
    }
}
