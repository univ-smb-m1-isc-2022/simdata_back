package com.durbo.simData.core;

import com.durbo.simData.Track.Track;
import com.durbo.simData.core.attributes.Attribute;
import com.durbo.simData.core.datas.object.ObjectData;
import com.durbo.simData.core.datas.object.ObjectDataFactory;
import org.hibernate.engine.transaction.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
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

    @Test
    public void testGetValue(){
        Track track = new Track();
        track.setName("Test Track");
        track.setCountry("Test Country");
        track.setLatitude(0.0);
        track.setLongitude(0.0);
        ObjectData objectData = new ObjectDataFactory<Track>().create(track);
        Object obj = objectData.getValue();
        assert obj instanceof Track;
    }
}
