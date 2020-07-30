package org.wfchiang.wid.core.testing;

import org.json.JSONObject;

public class TestCase {
    JSONObject inputInstance;
    TestStatus expectedStatus;

    public TestCase (TestStatus testStatus, JSONObject jsonObject) {
        this.expectedStatus = testStatus;
        this.inputInstance = jsonObject;
    }

    public JSONObject getInputInstance() {
        return inputInstance;
    }

    public void setInputInstance(JSONObject inputInstance) {
        this.inputInstance = inputInstance;
    }

    public TestStatus getExpectedStatus() {
        return expectedStatus;
    }

    public void setExpectedStatus(TestStatus expectedStatus) {
        this.expectedStatus = expectedStatus;
    }
}
