package com.durbo.simData.Track;

import com.durbo.simData.Location.Location;
import com.durbo.simData.core.object.ObjectData;
import com.durbo.simData.core.object.ObjectDataFactory;
import org.junit.jupiter.api.Test;
import com.durbo.simData.core.attributes.Attribute;

import java.util.ArrayList;

public class TrackFactoryTest {

    @Test
    public void testCreate(){
        Track track = new Track();


        ObjectData track2 = new ObjectDataFactory<Track>().create(track);

        assert track2.getAttributes().size() == 4;
    }
}
