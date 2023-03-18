package com.durbo.simData.core.values;

import com.durbo.simData.core.TYPE;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class IntegerValue extends Value {

        //override value column
        @Column
        private Integer value;

        private TYPE type = TYPE.INTEGER;

        public IntegerValue() {
            this.value = 0;
        }

        public IntegerValue(Integer value) {
            this.value = value;
        }
}
