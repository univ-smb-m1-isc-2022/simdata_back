package com.durbo.simData.core.datas;

import com.durbo.simData.core.SimData;
import com.durbo.simData.core.TYPE;
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
            super(TYPE.INTEGER);
            this.value = 0;
        }

        public IntegerData(Integer value) {
            super(TYPE.INTEGER);
            this.value = value;
        }
}
