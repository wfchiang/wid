package org.wfchiang.wid.fuzzing;

import org.json.JSONObject;
import org.wfchiang.wid.core.testing.TestCase;

import java.util.Collection;

public class ExceedMaxStringLengthSeededFuzzer extends SeededFuzzer {

    public ExceedMaxStringLengthSeededFuzzer(JSONObject seedObject) {
        super(seedObject);
    }

    @Override
    public Collection<TestCase> fuzz() {
        return null;
    }
}
