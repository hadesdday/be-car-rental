package com.carrental.enums;

public enum Gender {
    MALE("Nam"),
    FEMALE("Nữ");
    private final String gender;

    private Gender(String value) {
        this.gender = value;
    }

    public String getGender() {
        return gender;
    }
}
