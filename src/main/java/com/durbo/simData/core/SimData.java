package com.durbo.simData.core;

import jakarta.persistence.*;

@lombok.Data
@Entity(name = "datas")
public abstract class SimData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true)
    private Attribute attribute;

    @Column
    private TYPE type;


}
