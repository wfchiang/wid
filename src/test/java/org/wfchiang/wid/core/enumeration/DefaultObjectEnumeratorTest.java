package org.wfchiang.wid.core.enumeration;

import org.junit.Before;
import org.wfchiang.wid.core.model.EnumerationContext;
import org.wfchiang.wid.core.model.EnumerationHistory;

import java.util.Set;

public class DefaultObjectEnumeratorTest {

    EnumerationHistory enumerationHistory;
    EnumerationContext enumerationContext;

    @Before
    public void init() {
        this.enumerationHistory = new EnumerationHistory();
        this.enumerationContext = new EnumerationContext();
    }
}
