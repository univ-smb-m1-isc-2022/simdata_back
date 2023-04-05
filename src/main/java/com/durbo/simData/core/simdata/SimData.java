package com.durbo.simData.core.simdata;

import com.durbo.simData.Location.Coordinates;
import com.durbo.simData.Location.Location;
import com.durbo.simData.Track.Track;
import com.durbo.simData.User.User;
import com.durbo.simData.core.attributes.Attribute;
import com.durbo.simData.Track.Layout;
import com.durbo.simData.core.metadata.MetaData;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.ToString;

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

    @ToString.Exclude
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private MetaData metaData;

    public SimData() {
        this.type = "NULL";
    }

    public SimData(String type)
    {
        this.type = type;
        this.metaData = new MetaData();
    }

    public void setCreator(User creator)
    {
        this.metaData.setCreator(creator);
    }

    public void setValid(Boolean valid)
    {
        this.metaData.setValid(valid);
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
            case "Coordinates" -> Coordinates.class;
            default -> throw new RuntimeException("ObjectData.stringToObject() - type not found");
        };
    }

    public void setValue(Object value) {
    }

    public Object getValue(){
        return null;
    }
}
