package com.durbo.simData.core.subdata;

import com.durbo.simData.core.simdata.SimData;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@PrimaryKeyJoinColumn(name = "id")
public class StringData extends SimData {

    @Column
    private String value;


    public StringData() {
        super("String");
        this.value = "";
    }

    public StringData(String value) {
        super("String");
        this.value = value;
    }

    @Override
    public void setValue(Object value) {
        this.value = (String) value;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

}
