package com.durbo.simData.core.attributes;

import com.durbo.simData.core.datas.SimData;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "array_attributes")
@Data
public class ArrayAttribute extends Attribute {

    public ArrayAttribute(String name, String type) {
        super(name, type);
    }

    public ArrayAttribute() {
        super();
    }

    public ArrayList<SimData> validDatas(){
        return this.getDatas();
    }

    public ArrayList<Object> values(){
        ArrayList<Object> values = new ArrayList<>();
        ArrayList<SimData> data = this.validDatas();
        for (SimData datum : data) {
            values.add(datum.getValue());
        }
        return values;
    }
}
