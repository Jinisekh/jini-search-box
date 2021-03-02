package com.jini.zendesk.model;

public enum DataResourceEnum {

    ORGANIZATION("organizations.json"),
    TICKET("tickets.json"),
    USERS("users.json");

    public final String fileName;

    DataResourceEnum(String fileName) {
        this.fileName = fileName;
    }

}
