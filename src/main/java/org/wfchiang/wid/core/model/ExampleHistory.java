package org.wfchiang.wid.core.model;

import io.swagger.oas.inflector.examples.models.Example;

import java.util.HashSet;
import java.util.Set;

public class ExampleHistory {
    private Set<Example> exampleSet = new HashSet<Example>();

    public void addExample (Example example) {
        this.exampleSet.add(example);
    }

    public int size () {
        return this.exampleSet.size();
    }
}
