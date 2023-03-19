package com.durbo.simData.core;

import com.durbo.simData.core.attributes.Attribute;
import jakarta.persistence.*;

@lombok.Data
//this class have Column annotation, but it is not used
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
abstract public class SimData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private TYPE type;

    public SimData() {
        this.type = TYPE.NULL;
    }

    public SimData(TYPE type) {
        this.type = type;
    }
}
