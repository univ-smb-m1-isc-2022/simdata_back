package com.durbo.simData.cors.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "attributes")
public class Attribute <T> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
