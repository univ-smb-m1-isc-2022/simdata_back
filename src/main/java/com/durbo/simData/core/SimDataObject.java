package com.durbo.simData.core;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@lombok.Data
@Entity(name = "objects")
public class SimDataObject extends SimData {

    @Column
    private String name;

    @Column
    private TYPE type;

    @OneToMany(mappedBy = "object", cascade = jakarta.persistence.CascadeType.ALL)
    private List<Attribute> attributes;


    public SimDataObject(String name, TYPE type, List<Attribute> attributes) {
        this.name = name;
        this.setType(type);
        this.attributes = attributes;
        for (Attribute attribute : attributes) {
            attribute.setObject(this);
        }
    }

    public SimDataObject() {
        this.name = "";
        this.attributes = null;
    }

}
