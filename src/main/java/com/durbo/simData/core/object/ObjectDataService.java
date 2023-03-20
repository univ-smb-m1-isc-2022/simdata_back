package com.durbo.simData.core.object;

import com.durbo.simData.Track.Track;
import com.durbo.simData.core.TYPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ObjectDataService<T> {

    @Autowired
    private ObjectDataRepository objectDataRepository;

    @Autowired
    private ObjectDataFactory<T> trackFactory = new ObjectDataFactory<>();//TODO: remove constructor


    public ArrayList<Object> getAll(TYPE type) {
        ArrayList<Object> objs = new ArrayList<>();
        objectDataRepository.findByType(type)
                .ifPresent(objectData -> {
                    objs.add(objectData.getValue());
                });
        System.out.println(objs);
        return objs;
    }

    public Track getBy(TYPE type, String key, String value) {
        return (Track) objectDataRepository.findBy(type,key, value)
                .orElseThrow()
                .getValue();
    }

    public T create(T obj) {
        ObjectData objectData = trackFactory.create(obj);
        objectDataRepository.save(objectData);
        return obj;
    }
}
