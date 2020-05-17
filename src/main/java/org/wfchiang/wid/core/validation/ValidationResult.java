package org.wfchiang.wid.core.validation;

import com.atlassian.oai.validator.report.EmptyValidationReport;
import com.atlassian.oai.validator.report.ValidationReport;

public class ValidationResult {
    private ValidationStatus validationStatus;
    private ValidationReport validationReport;

    public ValidationResult (ValidationStatus validationStatus) {
        this.validationStatus = validationStatus;
    }

    public ValidationResult (ValidationStatus validationStatus, ValidationReport validationReport) {
        this(validationStatus);
        this.validationReport = validationReport;
    }

    public ValidationStatus getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(ValidationStatus validationStatus) {
        this.validationStatus = validationStatus;
    }

    public ValidationReport getValidationReport() {
        return validationReport;
    }

    public void setValidationReport(ValidationReport validationReport) {
        this.validationReport = validationReport;
    }
}
