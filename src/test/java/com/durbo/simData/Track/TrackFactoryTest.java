package com.durbo.simData.Track;

import com.durbo.simData.core.datas.DoubleData;
import com.durbo.simData.core.datas.ObjectData;
import com.durbo.simData.core.datas.StringData;
import org.junit.jupiter.api.Test;

public class TrackFactoryTest {

    ObjectFactory<Track> trackFactory = new ObjectFactory<>();

    @Test
    public void testCreate() {
        Track track = new Track();
        track.setName("Test Track");
        track.setCountry("Test Country");
        track.setLatitude(0.0);
        track.setLongitude(0.0);


        ObjectData track2 = trackFactory.create(track);

        assert ((StringData) track2.getAttribute("name").orElseThrow().getValidData()).getValue().equals(track.name);
        assert ((StringData) track2.getAttribute("country").orElseThrow().getValidData()).getValue().equals(track.country);
        assert ((DoubleData) track2.getAttribute("latitude").orElseThrow().getValidData()).getValue() == track.latitude;
        assert ((DoubleData) track2.getAttribute("longitude").orElseThrow().getValidData()).getValue() == track.longitude;
    }
}
