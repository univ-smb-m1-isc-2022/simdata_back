package com.durbo.simData.core.attributes;
import com.durbo.simData.core.object.ObjectData;
import com.durbo.simData.core.simdata.SimData;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity(name = "attributes")
@Data
@AllArgsConstructor
public class Attribute{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String type;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "object_id")
    private ObjectData objectData;
    //TODO: link not working properly

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "attribute",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SimData> datas;


    public Attribute(ObjectData objectData,String name, String type) {
        this.objectData = objectData;
        this.name = name;
        this.type = type;
        this.datas = new ArrayList<>();
    }

    public Attribute() {
        this.name = "";
        this.type = "NULL";
        this.datas = new ArrayList<>();
    }

    public void addData(SimData data) {
        //check if data is of the correct type
        //separe le type avec un split sur le point
        String type = this.type.split("\\.")[this.type.split("\\.").length-1];
        //si le dernier caractère est un >, alors on enlève le dernier caractère
        if (type.charAt(type.length()-1) == '>'){
            type = type.substring(0, type.length()-1);
        }
        if (Objects.equals(data.getType(), type)) {
            this.datas.add(data);
        }else{
            throw new RuntimeException("Attribute.addData() - data type does not match attribute type");
        }
    }

    public void removeData(SimData data) {
        this.datas.remove(data);
    }

    public Object getValue(){
        if (this.datas.size() > 0){
            //if type array
            if (this.type.contains("<")){
                ArrayList<Object> values = new ArrayList<>();
                for (SimData data : this.datas) {
                    values.add(data.getValue());
                }
                return values;
            }
            return this.datas.get(0).getValue();
        }
        //return default value depending on type
        return switch (this.type) {
            case "Integer" -> 0;
            case "double" -> 0.0;
            case "String" -> "";
            case "Boolean" -> false;
            default -> {
                if (this.type.contains("<")) {
                    yield new ArrayList<>();
                } else {
                    yield null;
                }
            }
        };
    }
}
