package com.durbo.simData.core.datas;

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
    private String type;

    public SimData() {
        this.type = "NULL";
    }

    public SimData(String type) {
        this.type = type;
    }

    public void setValue(Object value) {
    }

    public Object getValue(){
        return null;
    }
}
