package org.wfchiang.wid.core.enumeration.string;

import io.swagger.v3.oas.models.media.StringSchema;
import org.junit.Assert;
import org.junit.Test;
import org.wfchiang.wid.core.enumeration.EnumerationContext;

import java.util.Collection;

public class DefaultFixedLengthStringEnumeratorTest {

    @Test
    public void enumerate_0 () {
        EnumerationContext enumerationContext = new EnumerationContext();
        StringSchema stringSchema = new StringSchema();

        DefaultFixedLengthStringEnumerator defaultFixedLengthStringEnumerator = new DefaultFixedLengthStringEnumerator(0);
        Collection<String> enuSet = defaultFixedLengthStringEnumerator.enumerate(stringSchema, enumerationContext);
        Assert.assertNotNull(enuSet);
        Assert.assertEquals(1, enuSet.size());
        Assert.assertEquals("", enuSet.iterator().next());

        defaultFixedLengthStringEnumerator.setDefaultChar('J');
        defaultFixedLengthStringEnumerator.setStringLength(2);
        enuSet = defaultFixedLengthStringEnumerator.enumerate(stringSchema, enumerationContext);
        Assert.assertNotNull(enuSet);
        Assert.assertEquals(1, enuSet.size());
        Assert.assertEquals("JJ", enuSet.iterator().next());
    }

    @Test(expected = IllegalArgumentException.class)
    public void enumerate_e0 () {
        new DefaultFixedLengthStringEnumerator(-1);
    }
}
