package com.durbo.simData.Location;

import lombok.Data;

@Data
public class Coordinates {
    public double latitude;
    public double longitude;

    public Coordinates() {
        this.latitude = 0;
        this.longitude = 0;
    }
}
