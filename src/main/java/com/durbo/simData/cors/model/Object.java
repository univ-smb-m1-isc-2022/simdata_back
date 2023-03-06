package com.durbo.simData.cors.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;


@Entity(name = "objects")
public class Object extends Data {

    @Column
    private String name;


    public Object(String name) {
        this.name = name;
    }

    public Object() {
        this.name = "";
    }
}
