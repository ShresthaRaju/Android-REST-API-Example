package com.raju.androidrestapiexample.model;

public class Flag {

    private int id;
    private String country;
    private String file;

    public Flag(int id, String country, String file) {
        this.id = id;
        this.country = country;
        this.file = file;
    }

    public String getCountry() {
        return country;
    }

    public String getFile() {
        return file;
    }
}
