package com.team200.spyglassserver.domain.core.enums;

public enum Status {
    COMPLETE("Complete"),
    IN_PROGRESS("In Progress"),
    NOT_STARTED("Not Started");

    private String value;
    Status(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
