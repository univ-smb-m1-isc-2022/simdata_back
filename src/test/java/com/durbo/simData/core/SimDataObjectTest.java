package com.durbo.simData.core;

import org.junit.jupiter.api.Test;

import java.util.List;

public class SimDataObjectTest {

    @Test
    public void testSimDataObjectConstructor() {
        SimDataObject simDataObject = new SimDataObject();
        assert simDataObject.getAttributes().size() == 0;
    }

    @Test
    public void testSimDataObjectWithAttribute() {
        SimDataObject simDataObject = new SimDataObject(
            "test",
            TYPE.TRACK,
            List.of(
                new Attribute("test", TYPE.STRING),
                new Attribute("test2", TYPE.INTEGER)
            )
        );
        assert simDataObject.getAttributes().size() == 2;
    }
}
