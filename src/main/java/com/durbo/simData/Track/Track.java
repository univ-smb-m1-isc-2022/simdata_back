package com.durbo.simData.Track;

import com.durbo.simData.layout.Layout;
import lombok.Data;

@Data
public class Track {
    public String name;
    public String country;
    public double latitude;
    public double longitude;
    public Layout layout;
}
