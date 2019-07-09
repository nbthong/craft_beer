package com.craftbeer.common;

public enum BeerStatus {
    ARCHIVE("archive"),
    AVAILABLE("available");

    private String value;

    BeerStatus(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}