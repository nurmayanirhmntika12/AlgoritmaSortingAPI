package com.example.algoritmasortingapi;

import java.io.Serializable;

public class DataSorting implements Serializable {
    String name, official_web,  description, image;

    public DataSorting(String name, String official_web, String description, String image) {
        this.name = name;
        this.official_web = official_web;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getOfficial_web() {
        return official_web;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }
}
