package com.durbo.simData.core.attributes;
import com.durbo.simData.core.datas.SimData;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity(name = "attributes")
@Data
public class Attribute{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String type;

    //list of datas that are associated with this attribute
    @OneToMany(cascade = CascadeType.ALL)
    private ArrayList<SimData> datas;


    public Attribute(String name, String type) {
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
        if (Objects.equals(data.getType(), this.type)) {
            this.datas.add(data);
        }
    }

    public void removeData(SimData data) {
        this.datas.remove(data);
    }

    public SimData getValidData() {
        //TODO: update this method to return the most noteworthy value
        if (this.datas.size() > 0) {
            return this.datas.get(0);
        }
        return null;
    }

    public Object getValue(){
        SimData data = this.getValidData();
        if (data != null) {
            return data.getValue();
        }
        return null;
    }
}
