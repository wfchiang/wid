package org.wfchiang.wid.core.util;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class WidShortHandsTest {

    @Test
    public void getClassName_0 () {
        Integer integer = new Integer(0);
        String className = integer.getClass().getName();
        Assert.assertEquals(className, WidShortHands.getClassName(integer));
    }

    @Test
    public void getClassName_1 () {
        Assert.assertEquals(WidShortHands.NULL_CLASS_NAME, WidShortHands.getClassName(null));
    }

    @Test
    public void readJSONObject_0 () throws JSONException {
        String objString = "{\"aaa\": \"AAA\", \"xxx\": {\"yyy\": \"zzz\"}}";
        JSONObject obj = new JSONObject(objString);

        List<String> fieldKey = new ArrayList<>();

        Assert.assertEquals("AAA", ((JSONObject)WidShortHands.readJSONObject(obj, fieldKey)).get("aaa"));

        fieldKey.add("aaa");
        Assert.assertEquals("AAA", WidShortHands.readJSONObject(obj, fieldKey));

        fieldKey.clear();
        fieldKey.add("xxx");
        fieldKey.add("yyy");
        Assert.assertEquals("zzz", WidShortHands.readJSONObject(obj, fieldKey));
    }

    @Test(expected = NullPointerException.class)
    public void readJSONObject_e0 () throws JSONException {
        String objString = "{\"aaa\": \"AAA\", \"xxx\": {\"yyy\": \"zzz\"}}";
        JSONObject obj = new JSONObject(objString);
        WidShortHands.readJSONObject(obj, null);
    }

    @Test
    public void readJSONObject_1 () throws JSONException {
        List<String> fieldKey = new ArrayList<>();
        Assert.assertNull(WidShortHands.readJSONObject(null, fieldKey));
    }

    @Test
    public void writeJSONObject_0 () throws JSONException {
        String objString = "{\"aaa\": \"AAA\", \"xxx\": {\"yyy\": \"zzz\"}}";
        JSONObject obj = new JSONObject(objString);

        List<String> fieldKey = new ArrayList<>();

        WidShortHands.writeJSONObject(obj, fieldKey, null);
        Assert.assertEquals("AAA", obj.get("aaa"));

        fieldKey.add("aaa");
        WidShortHands.writeJSONObject(obj, fieldKey, "BBB");
        Assert.assertEquals("BBB", WidShortHands.readJSONObject(obj, fieldKey));

        fieldKey.clear();
        fieldKey.add("xxx");
        fieldKey.add("yyy");
        WidShortHands.writeJSONObject(obj, fieldKey, "ZZZ");
        Assert.assertEquals("ZZZ", WidShortHands.readJSONObject(obj, fieldKey));

        JSONObject sub = new JSONObject("{\"bbb\": \"ccc\"}");
        fieldKey.clear();
        fieldKey.add("aaa");
        WidShortHands.writeJSONObject(obj, fieldKey, sub);
        fieldKey.add("bbb");
        Assert.assertEquals("ccc", WidShortHands.readJSONObject(obj, fieldKey));
    }

    @Test
    public void writeJSONObject_1 () throws JSONException {
        List<String> fieldKey = new ArrayList<>();
        Assert.assertNull(WidShortHands.readJSONObject(null, fieldKey));
    }

    @Test(expected = NullPointerException.class)
    public void writeJSONObject_e0 () throws JSONException {
        String objString = "{\"aaa\": \"AAA\", \"xxx\": {\"yyy\": \"zzz\"}}";
        JSONObject obj = new JSONObject(objString);
        WidShortHands.writeJSONObject(obj, null, "test");
    }
}
