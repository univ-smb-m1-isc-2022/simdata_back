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

    @GetMapping("/tracks/{name}/id")
    public Long getTrackId(@PathVariable String name) {
        log.info("Getting track id");
        return trackService.getId("Track", name);
    }

    @GetMapping("/tracks/location/{zone}/{value}")
    public ArrayList<Object> getTracksByZone(@PathVariable String zone ,@PathVariable String value) throws MalformedURLException {
        log.info("Getting tracks by zone");
        /*ArrayList<Object> tracks = new ArrayList<>();
        for(Country country : countriesOfZone) {
            tracks.addAll(trackService.getBy("Track", "country", country.getName()));
        }*/
        ArrayList<Object> tracks = this.trackService.getAll("Track");
        ArrayList<Object> result = new ArrayList<>();
        for (Object o : tracks) {
            Track track = (Track) o;
            if (track.getLocation().getZone(zone).equals(value)) {
                result.add(track);
            }
        }
        return result;
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
