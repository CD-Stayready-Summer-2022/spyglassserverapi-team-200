package com.team200.spyglassserver.domain.core.enums;

public enum CompletionStatus {
    COMPLETE("Complete"),
    IN_PROGRESS("In Progress"),
    NOT_STARTED("Not Started");

    private String value;
    CompletionStatus(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
