package org.wfchiang.wid.core.exception;

import io.swagger.oas.inflector.examples.models.StringExample;
import org.junit.Assert;
import org.junit.Test;
import org.wfchiang.wid.core.model.ExampleHistory;

public class ExampleHistoryTest {

    @Test
    public void addExample_0 () {
        ExampleHistory exampleHistory = new ExampleHistory();
        Assert.assertEquals(0, exampleHistory.size());
        exampleHistory.addExample(new StringExample());
        Assert.assertEquals(1, exampleHistory.size());
    }
}
