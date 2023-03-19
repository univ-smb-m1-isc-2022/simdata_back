package com.durbo.simData.core;

import com.durbo.simData.core.attributes.Attribute;
import com.durbo.simData.core.datas.ObjectData;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ObjectDataTest {

    @Test
    public void testSimDataObjectConstructor() {
        ObjectData objectData = new ObjectData(TYPE.TEST,List.of());
        assert objectData.getAttributes().size() == 0;
    }

    @Test
    public void testSimDataObjectWithAttribute() {
        ObjectData objectData = new ObjectData(
            TYPE.TRACK,
            List.of(
                new Attribute("test", TYPE.STRING),
                new Attribute("test2", TYPE.INTEGER)
            )
        );
        assert objectData.getAttributes().size() == 2;
    }
}
