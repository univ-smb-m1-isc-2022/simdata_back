package com.durbo.simData.Location;

import lombok.Data;

@Data
public class Location {
    public Coordinates coordinates;
    public String city;
    public String country;
    public String region;

    public Location() {
        this.coordinates = new Coordinates();
        this.city = "";
        this.country = "";
        this.region = "";
    }

    public Object getZone(String zone) {
        switch (zone) {
            case "city" -> {
                return this.city;
            }
            case "country" -> {
                return this.country;
            }
            case "region" -> {
                return this.region;
            }
            default -> {
                return null;
            }
        }
    }
}
