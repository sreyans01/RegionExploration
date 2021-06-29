package com.app.regionexploration.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Region{

    @PrimaryKey(autoGenerate = true)
    public int uid;
    private String name;
    private String capital;
    private String flag;
    private String region;
    private String subRegion;
    private long population;
    private List<String> borders;
    private List<String> languages;
    //Alternatively, we can use Lombok Plugin also, but I have created simple getter and setter in this example
    public Region(String name, String capital, String flag, String region, String subRegion, long population, List<String> borders, List<String> languages) {
        this.name = name;
        this.capital = capital;
        this.flag = flag;
        this.region = region;
        this.subRegion = subRegion;
        this.population = population;
        this.borders = borders;
        this.languages = languages;
    }

    public Region() {
        this.name = "";
        this.capital = "";
        this.flag = "";
        this.region = "";
        this.subRegion = "";
        this.population = -1;
        this.borders = new ArrayList<>();
        this.languages = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubRegion() {
        return subRegion;
    }

    public void setSubRegion(String subRegion) {
        this.subRegion = subRegion;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public List<String> getBorders() {
        return borders;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }
}
