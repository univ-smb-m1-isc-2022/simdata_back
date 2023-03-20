package com.durbo.simData.Track;

import com.durbo.simData.core.ObjectDataRepository;
import com.durbo.simData.core.TYPE;
import com.durbo.simData.core.datas.ObjectData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TrackService {

    @Autowired
    private ObjectDataRepository objectDataRepository;

    public ArrayList<Track> getAll() {
        ArrayList<Track> tracks = new ArrayList<>();
        objectDataRepository.findByType(TYPE.TRACK).forEach(objectData -> {
            TrackFactory trackFactory = new TrackFactory();
            tracks.add(trackFactory.convert(objectData));
        });
        return tracks;
    }

    public ObjectData create(Track track) {
        TrackFactory trackFactory = new TrackFactory();
        ObjectData objectData = trackFactory.create(track);
        objectDataRepository.save(objectData);
        return objectData;
    }
}
