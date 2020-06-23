package org.wfchiang.wid.core.enumeration.string;

import io.swagger.v3.oas.models.media.StringSchema;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wfchiang.wid.core.enumeration.EnumerationContext;
import org.wfchiang.wid.core.enumeration.EnumerationHistory;
import org.wfchiang.wid.core.enumeration.string.DefaultStringEnumerator;

import java.util.Set;

public class DefaultStringEnumeratorTest {

    EnumerationHistory enumerationHistory;
    EnumerationContext enumerationContext;

    @Before
    public void init() {
        this.enumerationHistory = new EnumerationHistory();
        this.enumerationContext = new EnumerationContext();
    }

    @Test
    public void enumerate_0 () {
        StringSchema stringSchema = new StringSchema();

        StringEnumerator stringEnumerator = new DefaultStringEnumerator();
        Set<String> enuStringSet = stringEnumerator.enumerate(stringSchema, this.enumerationContext);
        Assert.assertNotNull(enuStringSet);
        Assert.assertEquals(1, enuStringSet.size());

        Object enuObject = enuStringSet.iterator().next();
        Assert.assertNotNull(enuObject);
        Assert.assertTrue(enuObject instanceof String);

        String enuString = (String) enuObject;
        Assert.assertEquals("", enuString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void enumerate_e0 () {
        StringEnumerator stringEnumerator = new DefaultStringEnumerator();
        stringEnumerator.enumerate(null, this.enumerationContext);
    }

    @Test(expected = IllegalArgumentException.class)
    public void enumerate_e1 () {
        StringEnumerator stringEnumerator = new DefaultStringEnumerator();
        stringEnumerator.enumerate(new StringSchema(), null);
    }
}
