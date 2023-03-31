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
}
