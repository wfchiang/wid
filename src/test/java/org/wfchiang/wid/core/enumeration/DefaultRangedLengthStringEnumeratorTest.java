package org.wfchiang.wid.core.enumeration;

import io.swagger.v3.oas.models.media.StringSchema;
import org.junit.Assert;
import org.junit.Test;
import org.wfchiang.wid.core.enumeration.string.DefaultRangedLengthStringEnumerator;

import java.util.Set;

public class DefaultRangedLengthStringEnumeratorTest {

    @Test
    public void enumerate_0 () {
        DefaultRangedLengthStringEnumerator defaultRangedLengthStringEnumerator =
                new DefaultRangedLengthStringEnumerator("J", 2, 5);
        Set<String> enuSet = defaultRangedLengthStringEnumerator.enumerate(new StringSchema(), new EnumerationContext());
        Assert.assertNotNull(enuSet);
        Assert.assertEquals(3, enuSet.size());
        Assert.assertFalse(enuSet.contains("J"));
        Assert.assertTrue(enuSet.contains("JJ"));
        Assert.assertTrue(enuSet.contains("JJJ"));
        Assert.assertTrue(enuSet.contains("JJJJ"));
        Assert.assertFalse(enuSet.contains("JJJJJ"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void enumerate_e0 () {
        new DefaultRangedLengthStringEnumerator(null, 1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void enumerate_e1 () {
        new DefaultRangedLengthStringEnumerator("", 1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void enumerate_e2 () {
        new DefaultRangedLengthStringEnumerator("", -1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void enumerate_e3 () {
        new DefaultRangedLengthStringEnumerator("", 2, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void enumerate_e4 () {
        new DefaultRangedLengthStringEnumerator("", 3, 2);
    }
}
