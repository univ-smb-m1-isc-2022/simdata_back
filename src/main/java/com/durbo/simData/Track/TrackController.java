package com.durbo.simData.Track;

import com.durbo.simData.User.User;
import com.durbo.simData.authentification.TokenService;
import com.durbo.simData.core.object.ObjectDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.ArrayList;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TrackController {

    static class MyTest{
        public Track track;
        public String token;
    }

    @Autowired
    private ObjectDataService<Track> trackService;

    @Autowired
    private TokenService tokenService;

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
    public Track createTrack(@RequestBody MyTest obj) {
        log.info("Creating track");

        User user = tokenService.getUserFromToken(obj.token);
        //check if token is valid
        //check if user has permission to create track
        return trackService.create(obj.track,user);
    }

    //remove track
    @DeleteMapping("/track/{name}")
    public void deleteTrack(@PathVariable String name) {
        log.info("Deleting track");
        //check if token is valid
        //check if user has permission to delete track
        trackService.delete("Track",name);
    }

}
