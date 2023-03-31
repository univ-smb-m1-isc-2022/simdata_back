package com.durbo.simData.core.subdata;

import com.durbo.simData.core.simdata.SimData;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class IntegerData extends SimData {
        @Column
        private Integer value;


        public IntegerData() {
            super("Integer");
            this.value = 0;
        }

        public IntegerData(Integer value) {
            super("Integer");
            this.value = value;
        }

        @Override
        public void setValue(Object value) {
            this.value = (Integer) value;
        }

        @Override
        public Object getValue() {
            return this.value;
        }
}
