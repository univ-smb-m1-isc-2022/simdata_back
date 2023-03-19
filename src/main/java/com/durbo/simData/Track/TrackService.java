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

    public ArrayList<ObjectData> getAll() {
        return objectDataRepository.findByType(TYPE.TRACK);
    }

    public ObjectData create(Track track) {
        TrackFactory trackFactory = new TrackFactory();
        ObjectData objectData = trackFactory.create(track);
        objectDataRepository.save(objectData);
        return objectData;
    }
}
