package com.durbo.simData.Track;


import com.durbo.simData.Location.Coordinates;
import com.durbo.simData.Location.Location;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {Track.class, Layout.class, Location.class})
public class TrackTest {


    @Test
    public void testTrack() {
        Track track = new Track();
        track.setName("Test");
        track.setCapacity(100);
        Location location = new Location();
        location.setCity("Test");
        location.setCountry("Test");
        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude(0.0);
        coordinates.setLongitude(0.0);
        location.setCoordinates(coordinates);
        track.setLocation(location);
        Layout layout = new Layout();
        layout.setName("Test");
        layout.setGrade(1);
        layout.setLength(100);
        layout.setTurns(10);
        layout.setConstructionYear(2000);
        layout.setDemolitionYear(2001);
        track.getLayouts().add(layout);
        assert track.getName().equals("Test");
        assert track.getCapacity() == 100;
        assert track.getLocation() != null;
        assert track.getLayouts().size() == 1;
        assert track.getLayouts().get(0).getName().equals("Test");
        assert track.getLayouts().get(0).getGrade() == 1;
        assert track.getLayouts().get(0).getLength() == 100;
        assert track.getLayouts().get(0).getTurns() == 10;
        assert track.getLayouts().get(0).getConstructionYear() == 2000;
        assert track.getLayouts().get(0).getDemolitionYear() == 2001;
    }

}
