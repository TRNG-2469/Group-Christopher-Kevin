package com.rev.ers.enums;

public enum Role {
    EMPLOYEE("employee"),
    MANAGER("manager");

    private final String dbValue;

    Role(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue() {
        return dbValue;
    }
}
