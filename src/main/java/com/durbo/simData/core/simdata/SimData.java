package com.durbo.simData.core.simdata;

import com.durbo.simData.Location.Location;
import com.durbo.simData.Track.Track;
import com.durbo.simData.core.attributes.Attribute;
import com.durbo.simData.Track.Layout;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

@lombok.Data
//this class have Column annotation, but it is not used
@Entity
@Table(name = "SD")
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@DiscriminatorValue("SD")
abstract public class SimData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;

    public SimData() {
        this.type = "NULL";
    }

    public SimData(String type) {
        this.type = type;
    }

    public SimData(String type, Attribute attribute) {
        this.type = type;
        this.attribute = attribute;
    }

    public Class<?> getrealType() {
        return switch (this.getType()) {
            case "Integer" -> Integer.class;
            case "double" -> double.class;
            case "String" -> String.class;
            case "Track" -> Track.class;
            case "Layout" -> Layout.class;
            case "Location" -> Location.class;
            default -> throw new RuntimeException("ObjectData.stringToObject() - type not found");
        };
    }

    public void setValue(Object value) {
    }

    public Object getValue(){
        return null;
    }
}
