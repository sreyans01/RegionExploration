package com.app.regionexploration.model;

import java.io.Serializable;

public class Language implements Serializable {

    private String name;
    private String nativeName;

    //Alternatively, we can use Lombok Plugin also, but I have created simple getter and setter in this example

    public Language() {
        this.name = "";
        this.nativeName = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }
}
