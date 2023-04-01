package com.durbo.simData.country;
import lombok.Data;

import java.util.HashMap;

@Data
public class Country {
    private String name;
    private String cca3;
    private String region;

    public Country(String name, String cca3, String region) {
        this.name = name;
        this.cca3 = cca3;
        this.region = region;
    }

    public Country() {
    }
}