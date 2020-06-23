package org.wfchiang.wid.core.enumeration.string;

import io.swagger.v3.oas.models.media.StringSchema;
import org.wfchiang.wid.core.enumeration.EnumerationContext;
import org.wfchiang.wid.core.validation.InstanceValidator;
import org.wfchiang.wid.core.validation.ValidationResult;
import org.wfchiang.wid.core.validation.ValidationStatus;

import java.util.HashSet;
import java.util.Set;

public class DefaultValidStringEnumerator implements StringEnumerator{

    private InstanceValidator instanceValidator = new InstanceValidator();

    private char defaultChar = 'a';

    @Override
    public Set<String> enumerate(StringSchema stringSchema, EnumerationContext enumerationContext) {
        Set<String> candidateSet;
        Set<String> enuSet = new HashSet<>();

        // Try matching the min length
        Integer minLength = stringSchema.getMinLength();
        if (minLength == null || minLength == 0) {
            candidateSet = new HashSet<>();
            candidateSet.add(null);
        }
        else {
            candidateSet = new DefaultFixedLengthStringEnumerator(minLength, this.defaultChar)
                    .enumerate(stringSchema, enumerationContext);
        }
        enuSet.addAll(this.filterOutInvalidString(candidateSet, stringSchema, enumerationContext));

        return enuSet;
    }

    private Set<String> filterOutInvalidString (Set<String> enuSet, StringSchema stringSchema, EnumerationContext enumerationContext) {
        Set<String> filteredSet = new HashSet<>();
        for (String enuS : enuSet) {
            ValidationResult validationResult = this.instanceValidator.validate(enumerationContext.getOpenAPI(), enuS, stringSchema);
            if (validationResult.getValidationStatus() == ValidationStatus.PASS) {
                filteredSet.add(enuS);
            }
        }
        return filteredSet;
    }

    /*
    Getters and Setters
     */

    public char getDefaultChar() {
        return defaultChar;
    }

    public void setDefaultChar(char defaultChar) {
        this.defaultChar = defaultChar;
    }
}
