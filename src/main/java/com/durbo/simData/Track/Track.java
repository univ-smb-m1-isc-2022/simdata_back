package com.durbo.simData.Track;

import com.durbo.simData.layout.Layout;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Track {
    public String name;
    public String country;
    public double latitude;
    public double longitude;
    public ArrayList<Layout> layouts;

    public Track() {
    }

    public Track(String name, String country, double latitude, double longitude, ArrayList<Layout> layouts) {
        this.name = name;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.layouts = layouts;
    }
}
