package com.durbo.simData.location;

import com.durbo.simData.Location.Location;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Location.class)
public class LocationTests {

    @Test
    public void testLocation() {
        Location location = new Location();
        location.setCity("test");
        location.setCountry("test");
        location.setRegion("test");
        assert location.getCity().equals("test");
        assert location.getCountry().equals("test");
        assert location.getRegion().equals("test");
    }

    @Test
    public void testLocationGetZone() {
        Location location = new Location();
        location.setCity("test");
        location.setCountry("test");
        location.setRegion("test");
        assert location.getZone("city").equals("test");
        assert location.getZone("country").equals("test");
        assert location.getZone("region").equals("test");
        assert location.getZone("test") == null;
    }
}
