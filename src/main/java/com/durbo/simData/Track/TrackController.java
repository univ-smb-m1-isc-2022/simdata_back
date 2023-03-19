package com.durbo.simData.Track;

import com.durbo.simData.core.datas.ObjectData;
import com.durbo.simData.core.TYPE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TrackController {

    @Autowired
    private TrackService trackService;

    @GetMapping("/tracks")
    public ArrayList<ObjectData> getTracks() {
        log.info("Getting tracks");
        return trackService.getAll();
    }

    @PostMapping("/track")
    public ObjectData createTrack(@RequestBody Track track) {
        log.info("Creating track");
        return trackService.create(track);
    }

}
