package org.wfchiang.wid.fuzzing;

import org.json.JSONObject;
import org.wfchiang.wid.core.testing.TestCase;

import java.util.Collection;

public abstract class SeededFuzzer extends Fuzzer {

    protected JSONObject seedObject;

    public SeededFuzzer (JSONObject seedObject) {
        this.seedObject = seedObject;
    }
}
