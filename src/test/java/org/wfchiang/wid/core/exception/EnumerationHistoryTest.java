package org.wfchiang.wid.core.exception;

import org.junit.Assert;
import org.junit.Test;
import org.wfchiang.wid.core.model.EnumerationHistory;

public class EnumerationHistoryTest {

    @Test
    public void addExample_0 () {
        EnumerationHistory enumerationHistory = new EnumerationHistory();
        Assert.assertEquals(0, enumerationHistory.size());
        enumerationHistory.addEnumeration("123");
        Assert.assertEquals(1, enumerationHistory.size());
        enumerationHistory.addEnumeration("123");
        Assert.assertEquals(1, enumerationHistory.size());
        enumerationHistory.addEnumeration("456");
        Assert.assertEquals(2, enumerationHistory.size());
    }
}
