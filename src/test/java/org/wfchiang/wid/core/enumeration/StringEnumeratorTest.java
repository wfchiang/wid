package org.wfchiang.wid.core.enumeration;

import io.swagger.oas.inflector.examples.models.DoubleExample;
import io.swagger.oas.inflector.examples.models.StringExample;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wfchiang.wid.core.exception.WidUnsupportedClassException;
import org.wfchiang.wid.core.model.EnumerationContext;
import org.wfchiang.wid.core.model.ExampleHistory;

import java.util.Set;

public class StringEnumeratorTest {

    ExampleHistory exampleHistory;
    EnumerationContext enumerationContext;

    @Before
    public void init() {
        this.exampleHistory = new ExampleHistory();
        this.enumerationContext = new EnumerationContext(this.exampleHistory);
    }

    @Test
    public void enumerate_0 () {
        StringExample stringExample = new StringExample();
        stringExample.setValue("123");

        StringEnumerator stringEnumerator = new StringEnumerator();
        Set<Object> enuObjectSet = stringEnumerator.enumerate(stringExample, this.enumerationContext);
        Assert.assertNotNull(enuObjectSet);
        Assert.assertEquals(1, enuObjectSet.size());

        Object enuObject = enuObjectSet.iterator().next();
        Assert.assertNotNull(enuObject);
        Assert.assertTrue(enuObject instanceof String);

        String enuString = (String) enuObject;
        Assert.assertEquals("123", enuString);
    }

    @Test(expected = WidUnsupportedClassException.class)
    public void enumerate_e0 () {
        DoubleExample doubleExample = new DoubleExample(1.23);
        StringEnumerator stringEnumerator = new StringEnumerator();
        stringEnumerator.enumerate(doubleExample, this.enumerationContext);
    }
}
