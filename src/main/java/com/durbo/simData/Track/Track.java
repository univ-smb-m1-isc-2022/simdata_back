package com.durbo.simData.Track;

import com.durbo.simData.Location.Location;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Track {
    public String name;
    public Location location;
    public Integer capacity;
    public ArrayList<Layout> layouts;

    public Track() {
        this.name = "";
        this.location = new Location();
        this.capacity = 0;
        this.layouts = new ArrayList<Layout>();
    }
}
