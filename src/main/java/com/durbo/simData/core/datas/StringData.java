package com.durbo.simData.core.datas;

import com.durbo.simData.core.TYPE;
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
        super(TYPE.STRING);
        this.value = "";
    }

    public StringData(String value) {
        super(TYPE.STRING);
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
