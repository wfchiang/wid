package org.wfchiang.wid.core.model;

import java.util.HashSet;
import java.util.Set;

public class EnumerationHistory {
    private Set<Object> previousEnumerations = new HashSet<Object>();

    public void addEnumeration (Object enu) {
        this.previousEnumerations.add(enu);
    }

    public int size () {
        return this.previousEnumerations.size();
    }
}
