package com.durbo.simData.core.values;

import com.durbo.simData.core.SimData;
import com.durbo.simData.core.TYPE;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
abstract public class Value extends SimData {

    //value will be overridden by subclasses, need to be abstract
    private Object value;

    @Column
    private TYPE type = TYPE.NULL;


    public Value() {
        this.value = null;
    }

    public Value(Object value){
        this.value = value;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}