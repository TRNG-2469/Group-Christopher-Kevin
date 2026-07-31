package com.rev.ers.enums;

public enum Type {
    TRAVEL("travel"),
    FOOD("food"),
    LODGING("lodging"),
    OTHER("other");

    private final String dbValue;

    Type(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue() {
        return dbValue;
    }
}
