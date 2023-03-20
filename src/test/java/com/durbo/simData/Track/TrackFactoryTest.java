package com.durbo.simData.Track;

import com.durbo.simData.core.object.ObjectData;
import com.durbo.simData.core.object.ObjectDataFactory;
import com.durbo.simData.layout.Layout;
import org.junit.jupiter.api.Test;

public class TrackFactoryTest {

    @Test
    public void testCreate() throws InstantiationException, IllegalAccessException {
        Track track = new Track();
        track.setName("Test Track");
        track.setCountry("Test Country");
        track.setLatitude(0.0);
        track.setLongitude(0.0);
        Layout layout = new Layout();
        layout.setGrade(1);
        layout.setLength(1.0);
        track.setLayout(layout);

        ObjectData track2 = new ObjectDataFactory<Track>().create(track);

        assert track2.getAttribute("name").orElseThrow().getValidData().getValue().equals(track.name);
        assert track2.getAttribute("country").orElseThrow().getValidData().getValue().equals(track.country);
        assert (double) track2.getAttribute("latitude").orElseThrow().getValidData().getValue() == track.latitude;
        assert (double) track2.getAttribute("longitude").orElseThrow().getValidData().getValue() == track.longitude;
        assert track2.getAttribute("layout").orElseThrow().getValidData().getValue() instanceof Layout;
    }
}
