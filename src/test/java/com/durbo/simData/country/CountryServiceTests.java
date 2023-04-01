package com.durbo.simData.country;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class CountryServiceTests {

    @Test
    public void testGetCountriesByRegion() {
        CountryService countryService = new CountryService();
        ArrayList<Country> countries = countryService.getCountriesByRegion("europe");
    }
}
