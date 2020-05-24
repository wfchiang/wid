package org.wfchiang.wid.core.exception;

import io.swagger.v3.oas.models.media.StringSchema;
import org.junit.Assert;
import org.junit.Test;
import org.wfchiang.wid.core.enumeration.EnumerationHistory;

public class EnumerationHistoryTest {

    @Test
    public void addExample_0 () {
        EnumerationHistory enumerationHistory = new EnumerationHistory();
        StringSchema stringSchema0 = new StringSchema();
        StringSchema stringSchema1 = new StringSchema();

        Assert.assertEquals(0, enumerationHistory.size(stringSchema0));

        enumerationHistory.add(stringSchema0, "123");
        Assert.assertEquals(1, enumerationHistory.size(stringSchema0));

        enumerationHistory.add(stringSchema0, "123");
        Assert.assertEquals(1, enumerationHistory.size(stringSchema0));

        enumerationHistory.add(stringSchema0, "456");
        Assert.assertEquals(2, enumerationHistory.size(stringSchema0));

        enumerationHistory.add(stringSchema1, "123");
        Assert.assertEquals(2, enumerationHistory.size(stringSchema0));
        // Even thought stringSchema0 and stringSchema1 are distinct objects, but they are considered the same schema...
        Assert.assertEquals(2, enumerationHistory.size(stringSchema1));
    }
}
