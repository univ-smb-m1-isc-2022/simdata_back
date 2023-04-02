package com.durbo.simData.core.object;

import com.durbo.simData.Track.Track;
import com.durbo.simData.core.attributes.Attribute;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ObjectDataTest {

    @Test
    public void testSimDataObjectConstructor() {
        ObjectData objectData = new ObjectData("Object",List.of());
        assert objectData.getAttributes().size() == 0;
    }

    @Test
    public void testSimDataObjectWithAttribute() {
        ObjectData objectData = new ObjectData(
            "Track",
            List.of(
                new Attribute(null,"test", "String"),
                new Attribute(null,"test2", "Integer")
            )
        );
        assert objectData.getAttributes().size() == 2;
    }

    @Test
    public void testGetValue(){
        Track track = new Track();
        ObjectData objectData = new ObjectDataFactory<Track>().create(track);
        Object obj = objectData.getValue();
        assert obj instanceof Track;
    }
}
