package org.wfchiang.wid.core.util;

import io.swagger.v3.oas.models.media.ObjectSchema;
import org.json.JSONObject;

import java.util.List;

public class WidShortHands {

    public static final String NULL_CLASS_NAME = "(null)";

    public static String getClassName (Object object) {
        return (object == null ? NULL_CLASS_NAME : object.getClass().getName());
    }

    public static Object readJSONObject (JSONObject jsonObject, List<String> fieldKey) {
        Object pointer = jsonObject;
        for (String k : fieldKey) {
            pointer = ((JSONObject) pointer).get(k);
        }
        return pointer;
    }

    public static void writeJSONObject (JSONObject jsonObject, List<String> fieldKey, Object value) {
        if (fieldKey.size() > 0) {
            Object parent = jsonObject;
            String ckey = fieldKey.get(0);
            for (int i = 1 ; i < fieldKey.size() ; i++) {
                parent = ((JSONObject)parent).get(ckey);
                ckey = fieldKey.get(i);
            }
            ((JSONObject)parent).put(ckey, value);
        }
    }
}
