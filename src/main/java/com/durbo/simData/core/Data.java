package com.durbo.simData.core;

import jakarta.persistence.*;

@lombok.Data
@Entity(name = "datas")
public abstract class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true)
    private Attribute attribute;

    @Column
    private TYPE type;


}
