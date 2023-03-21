package com.durbo.simData.Track;

import com.durbo.simData.core.TYPE;
import com.durbo.simData.core.object.ObjectDataService;
import com.durbo.simData.layout.Layout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return trackService.getAll(TYPE.TRACK);
    }

    @GetMapping("/tracks/{name}")
    public Track getTrack(@PathVariable String name) {
        log.info("Getting track");
        return (Track) trackService.getBy(TYPE.TRACK,"name", name).get(0);
    }

    @GetMapping("/tracks/{name}/{attribute}")
    public ArrayList<Object> getTrackAttributeDatas(@PathVariable String name, @PathVariable String attribute) {
        log.info("Getting track attributes values for " + attribute);
        return trackService.getAttributeDatas(TYPE.TRACK, name, attribute);
    }

    @PostMapping("/track")
    public Track createTrack(@RequestBody Track track) {
        log.info("Creating track");
        return trackService.create(track);
    }

}
