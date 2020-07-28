package org.wfchiang.wid.fuzzing;

import io.swagger.v3.oas.models.OpenAPI;
import org.wfchiang.wid.core.testing.TestCase;

import java.util.Collection;

public abstract class Fuzzer {
    protected OpenAPI openAPI;

    abstract public Collection<TestCase> fuzz ();
}
