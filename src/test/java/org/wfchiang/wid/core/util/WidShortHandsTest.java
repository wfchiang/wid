package org.wfchiang.wid.core.util;

import org.junit.Assert;
import org.junit.Test;

public class WidShortHandsTest {

    @Test
    public void getClassName_0 () {
        Integer integer = new Integer(0);
        String className = integer.getClass().getName();
        Assert.assertEquals(className, WidShortHands.getClassName(integer));
    }

    @Test
    public void getClassName_1 () {
        Assert.assertEquals(WidShortHands.NULL_CLASS_NAME, WidShortHands.getClassName(null));
    }
}
