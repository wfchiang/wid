package org.wfchiang.wid.core.enumeration;

import io.swagger.v3.oas.models.media.StringSchema;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class DefaultCharRangedLengthStringEnumeratorTest {

    @Test
    public void enumerate_0 () {
        DefaultCharRangedLengthStringEnumerator defaultCharRangedLengthStringEnumerator =
                new DefaultCharRangedLengthStringEnumerator("J", 2, 5);
        Set<String> enuSet = defaultCharRangedLengthStringEnumerator.enumerate(new StringSchema(), new EnumerationContext());
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
        new DefaultCharRangedLengthStringEnumerator(null, 1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void enumerate_e1 () {
        new DefaultCharRangedLengthStringEnumerator("", 1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void enumerate_e2 () {
        new DefaultCharRangedLengthStringEnumerator("", -1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void enumerate_e3 () {
        new DefaultCharRangedLengthStringEnumerator("", 2, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void enumerate_e4 () {
        new DefaultCharRangedLengthStringEnumerator("", 3, 2);
    }
}
