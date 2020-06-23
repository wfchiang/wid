package org.wfchiang.wid.core.enumeration.string;

import io.swagger.v3.oas.models.media.StringSchema;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wfchiang.wid.core.enumeration.EnumerationContext;
import org.wfchiang.wid.core.enumeration.string.DictionaryFixedLengthStringEnumerator;
import org.wfchiang.wid.core.exception.WidEnumerationException;

import java.util.Arrays;
import java.util.List;

public class DictionaryFixedLengthStringEnumeratorTest {

    StringSchema stringSchema;
    EnumerationContext enumerationContext;
    List<String> abcList;

    DictionaryFixedLengthStringEnumerator defaultEnumerator;
    DictionaryFixedLengthStringEnumerator abcEnumerator;

    @Before
    public void init () {
        this.stringSchema = new StringSchema();
        this.enumerationContext = new EnumerationContext();
        abcList = Arrays.asList("a", "b", "c");
        defaultEnumerator = new DictionaryFixedLengthStringEnumerator(3);
        abcEnumerator = new DictionaryFixedLengthStringEnumerator(3, abcList);
    }

    @Test
    public void DictionaryFixedLengthStringEnumerator_0 () {
        Assert.assertEquals(3, defaultEnumerator.getStringLength());

        List<String> charList = defaultEnumerator.getChatList();
        Assert.assertNotNull(charList);
        Assert.assertEquals(10, charList.size());
        Assert.assertTrue(charList.contains("0"));
        Assert.assertTrue(charList.contains("1"));
        Assert.assertTrue(charList.contains("2"));
        Assert.assertTrue(charList.contains("3"));
        Assert.assertTrue(charList.contains("4"));
        Assert.assertTrue(charList.contains("5"));
        Assert.assertTrue(charList.contains("6"));
        Assert.assertTrue(charList.contains("7"));
        Assert.assertTrue(charList.contains("8"));
        Assert.assertTrue(charList.contains("9"));
    }

    @Test
    public void DictionaryFixedLengthStringEnumerator_1 () {
        Assert.assertEquals(3, abcEnumerator.getStringLength());

        List<String> charList = abcEnumerator.getChatList();
        Assert.assertNotNull(charList);
        Assert.assertEquals(3, charList.size());
        Assert.assertTrue(charList.contains("a"));
        Assert.assertTrue(charList.contains("b"));
        Assert.assertTrue(charList.contains("c"));
    }

    @Test
    public void getFirstInstance_0 () {
        Assert.assertEquals("000", defaultEnumerator.getFirstInstance());
    }

    @Test
    public void getLastInstance_0 () {
        Assert.assertEquals("999", defaultEnumerator.getLastInstance());
    }

    @Test
    public void isValidInstance_0 () {
        Assert.assertFalse(abcEnumerator.isValidInstance(null));
        Assert.assertFalse(abcEnumerator.isValidInstance(""));
        Assert.assertTrue(abcEnumerator.isValidInstance("aaa"));
        Assert.assertTrue(abcEnumerator.isValidInstance("abc"));
        Assert.assertTrue(abcEnumerator.isValidInstance("ccc"));
        Assert.assertFalse(abcEnumerator.isValidInstance("1aa"));
        Assert.assertFalse(abcEnumerator.isValidInstance("aa1"));
        Assert.assertFalse(abcEnumerator.isValidInstance("123"));
    }

    @Test
    public void setPrevInstance_0 () {
        Assert.assertNull(defaultEnumerator.getPrevInstance());
        defaultEnumerator.setPrevInstance("123");
        Assert.assertEquals("123", defaultEnumerator.getPrevInstance());
        defaultEnumerator.setPrevInstance(null);
        Assert.assertNull(defaultEnumerator.getPrevInstance());
    }

    @Test(expected = WidEnumerationException.class)
    public void setPrevInstance_e0 () {
        defaultEnumerator.setPrevInstance("abc");
    }

    @Test(expected = WidEnumerationException.class)
    public void setPrevInstance_e1 () {
        defaultEnumerator.setPrevInstance("");
    }

    @Test
    public void hasNext_0 () {
        Assert.assertTrue(defaultEnumerator.hasNext());

        defaultEnumerator.setPrevInstance("998");
        Assert.assertTrue(defaultEnumerator.hasNext());

        defaultEnumerator.setPrevInstance("999");
        Assert.assertFalse(defaultEnumerator.hasNext());

        defaultEnumerator.setPrevInstance(null);
        Assert.assertTrue(defaultEnumerator.hasNext());
    }

    @Test
    public void enumerate_0 () {
        Assert.assertEquals("000", defaultEnumerator.enumerate(stringSchema, enumerationContext).iterator().next());
        Assert.assertEquals("001", defaultEnumerator.enumerate(stringSchema, enumerationContext).iterator().next());

        defaultEnumerator.setPrevInstance("009");
        Assert.assertEquals("010", defaultEnumerator.enumerate(stringSchema, enumerationContext).iterator().next());

        defaultEnumerator.setPrevInstance("099");
        Assert.assertEquals("100", defaultEnumerator.enumerate(stringSchema, enumerationContext).iterator().next());

        defaultEnumerator.setPrevInstance("199");
        Assert.assertEquals("200", defaultEnumerator.enumerate(stringSchema, enumerationContext).iterator().next());

        defaultEnumerator.setPrevInstance("998");
        Assert.assertEquals("999", defaultEnumerator.enumerate(stringSchema, enumerationContext).iterator().next());
        Assert.assertNull(defaultEnumerator.enumerate(stringSchema, enumerationContext));
    }
}
