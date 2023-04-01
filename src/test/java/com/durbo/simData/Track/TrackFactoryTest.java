package com.durbo.simData.Track;

import com.durbo.simData.core.object.ObjectData;
import com.durbo.simData.core.object.ObjectDataFactory;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class TrackFactoryTest {

    @Test
    public void testCreate() throws InstantiationException, IllegalAccessException {
        Track track = new Track("test", "test", 0, 0, new ArrayList<>());

        ObjectData track2 = new ObjectDataFactory<Track>().create(track);

        assert track2.getAttribute("name").orElseThrow().getValue().equals(track.name);
        assert track2.getAttribute("country").orElseThrow().getValue().equals(track.country);
        assert (double) track2.getAttribute("latitude").orElseThrow().getValue() == track.latitude;
        assert (double) track2.getAttribute("longitude").orElseThrow().getValue() == track.longitude;
        assert track2.getAttribute("layouts").orElseThrow().getValue().equals(track.layouts);
    }
}
