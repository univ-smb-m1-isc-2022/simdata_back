package com.durbo.simData.core;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity(name = "attributes")
@Data
public class Attribute{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private TYPE type;

    @ManyToOne
    private SimDataObject object;

    //list of proposals
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "attribute")
    private List<SimData> datas;


    public Attribute(String name, TYPE type) {
        this.name = name;
        this.type = type;
        this.datas = new ArrayList<>();
    }

    public Attribute() {
        this.name = "";
        this.datas = new ArrayList<>();
    }

    public void addData(SimData data) {
        //check if data is of the correct type
        if (data.getType() == this.type) {
            this.datas.add(data);
            data.setAttribute(this);
        }
    }

    public void removeData(SimData data) {
        this.datas.remove(data);
        data.setAttribute(null);
    }

}
