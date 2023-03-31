package com.durbo.simData.Track;

import com.durbo.simData.core.object.ObjectDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TrackController {

    @Autowired
    private ObjectDataService<Track> trackService;

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
        //first get all countries in region
        //then get all tracks in those countries
        //api : https://restcountries.com/v3.1/
        try {
            URL url = new URL("https://restcountries.com/v3.1/region/" + value);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }



        return trackService.getBy("Track", "region", value);
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
