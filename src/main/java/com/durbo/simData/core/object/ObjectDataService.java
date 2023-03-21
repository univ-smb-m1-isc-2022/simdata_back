package com.durbo.simData.core.object;

import com.durbo.simData.Track.Track;
import com.durbo.simData.core.TYPE;
import com.durbo.simData.core.datas.SimData;
import com.durbo.simData.layout.Layout;
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
        objectDataRepository.findByType(type).forEach(
                objectData -> objs.add(objectData.getValue())
        );
        System.out.println(objs);
        return objs;
    }

    public ArrayList<Object> getBy(TYPE type, String key, String value) {
        ArrayList<Object> objs = new ArrayList<>();
        objectDataRepository.findBy(type,key, value).forEach(
                objectData -> objs.add(objectData.getValue())
        );
        return objs;
    }

    public T create(T obj) {
        ObjectData objectData = trackFactory.create(obj);
        objectDataRepository.save(objectData);
        return obj;
    }

    public ArrayList<Object> getAttributeDatas(TYPE type, String name, String attribute) {
        ArrayList<SimData> objs = new ArrayList<>();
        objectDataRepository.findBy(type, "name", name).forEach(
                objectData -> {
                    objectData.getAttribute(attribute).ifPresent(
                            attribute1 -> objs.addAll(attribute1.getDatas())
                    );
                }
        );
        ArrayList<Object> values = new ArrayList<>();
        objs.forEach(simData -> values.add(simData.getValue()));
        return values;
    }
}
