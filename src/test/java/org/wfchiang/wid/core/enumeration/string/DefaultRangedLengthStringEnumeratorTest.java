package org.wfchiang.wid.core.enumeration.string;

import io.swagger.v3.oas.models.media.StringSchema;
import org.junit.Assert;
import org.junit.Test;
import org.wfchiang.wid.core.enumeration.EnumerationContext;
import org.wfchiang.wid.core.enumeration.string.DefaultRangedLengthStringEnumerator;

import java.util.Set;

public class DefaultRangedLengthStringEnumeratorTest {

    @Test
    public void enumerate_0 () {
        DefaultRangedLengthStringEnumerator defaultRangedLengthStringEnumerator =
                new DefaultRangedLengthStringEnumerator('J', 2, 5);
        Set<String> enuSet = defaultRangedLengthStringEnumerator.enumerate(new StringSchema(), new EnumerationContext());
        Assert.assertNotNull(enuSet);
        Assert.assertEquals(3, enuSet.size());
        Assert.assertFalse(enuSet.contains("J"));
        Assert.assertTrue(enuSet.contains("JJ"));
        Assert.assertTrue(enuSet.contains("JJJ"));
        Assert.assertTrue(enuSet.contains("JJJJ"));
        Assert.assertFalse(enuSet.contains("JJJJJ"));
    }
}
