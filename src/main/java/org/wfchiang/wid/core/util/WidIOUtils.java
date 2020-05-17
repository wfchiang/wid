package org.wfchiang.wid.core.util;

import com.google.common.io.CharStreams;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStreamReader;

public class WidIOUtils {

    public static String readClassPathResourceContent (String resourceClassPath) throws IOException {
        return CharStreams.toString(new InputStreamReader(new ClassPathResource(resourceClassPath).getInputStream()));
    }

    public static JSONObject cloneJSONObject (JSONObject jsonObject) {
        return new JSONObject(jsonObject.toString());
    }
}
