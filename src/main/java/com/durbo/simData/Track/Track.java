package com.durbo.simData.Track;

import com.durbo.simData.Location.Location;
import com.durbo.simData.SimClass;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@Data
public class Track {
    public String name;
    public Location location;
    public Integer capacity;
    public ArrayList<Layout> layouts;

    public Track() {
        super();
        this.layouts = new ArrayList<>();
        this.location = new Location();
        this.capacity = 0;
    }
}
