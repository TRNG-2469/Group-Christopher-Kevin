package com.rev.ers.enums;

public enum Status {
    PENDING("pending"),
    APPROVED("approved"),
    DENIED("denied");

    private final String dbValue;

    Status(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue() {
        return dbValue;
    }
}
