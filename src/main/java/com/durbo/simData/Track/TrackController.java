package com.durbo.simData.Track;

import com.durbo.simData.core.TYPE;
import com.durbo.simData.core.object.ObjectDataService;
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
        return trackService.getBy(TYPE.TRACK,"name", name);
    }

    @PostMapping("/track")
    public Track createTrack(@RequestBody Track track) {
        log.info("Creating track");
        return trackService.create(track);
    }

}
