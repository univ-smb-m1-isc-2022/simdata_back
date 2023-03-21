package com.durbo.simData.core.datas;

import com.durbo.simData.Track.Track;
import com.durbo.simData.layout.Layout;
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

    public Class<?> getrealType() {
        return switch (this.getType()) {
            case "Integer" -> Integer.class;
            case "double" -> double.class;
            case "String" -> String.class;
            case "Track" -> Track.class;
            case "Layout" -> Layout.class;
            default -> throw new RuntimeException("ObjectData.stringToObject() - type not found");
        };
    }

    public void setValue(Object value) {
    }

    public Object getValue(){
        return null;
    }
}
