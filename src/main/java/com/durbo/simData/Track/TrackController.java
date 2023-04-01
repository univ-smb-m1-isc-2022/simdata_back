package com.durbo.simData.Track;

import com.durbo.simData.core.object.ObjectDataService;
import com.durbo.simData.country.Country;
import com.durbo.simData.country.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TrackController {

    @Autowired
    private ObjectDataService<Track> trackService;

    @Autowired
    private CountryService countryService;

    @GetMapping("/tracks")
    public ArrayList<Object> getTracks() {
        log.info("Getting tracks");
        return trackService.getAll("Track");
    }

    @GetMapping("/tracks/{name}")
    public Track getTrack(@PathVariable String name) {
        log.info("Getting track");
        return (Track) trackService.getBy("Track","name", name).get(0);
    }

    @GetMapping("/tracks/region/{value}")
    public ArrayList<Object> getTracksByRegion(@PathVariable String value) throws MalformedURLException {
        log.info("Getting tracks by region");
        ArrayList<Country> countriesOfRegion = countryService.getCountriesByRegion(value);
        ArrayList<Object> tracks = new ArrayList<>();
        for(Country country : countriesOfRegion) {
            tracks.addAll(trackService.getBy("Track", "country", country.getName()));
        }
        return tracks;
    }

    @GetMapping("/tracks/{attribute}/{value}")
    public ArrayList<Object> getTracksByAttribute(@PathVariable String attribute, @PathVariable String value) {
        log.info("Getting tracks by attribute");
        return trackService.getBy("Track", attribute, value);
    }

    @PostMapping("/track")
    public Track createTrack(@RequestBody Track track) {
        log.info("Creating track");
        return trackService.create(track);
    }

}
