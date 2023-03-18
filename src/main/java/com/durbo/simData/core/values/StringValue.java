package com.durbo.simData.core.values;

import com.durbo.simData.core.TYPE;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
public class StringValue extends Value {

    @Column
    private String value;


    private TYPE type = TYPE.STRING;

    public StringValue() {
        this.value = "";
    }

    public StringValue(String value) {
        this.value = value;
    }

}
