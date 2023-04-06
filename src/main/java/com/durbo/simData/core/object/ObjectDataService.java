package com.durbo.simData.core.object;


import com.durbo.simData.User.User;
import com.durbo.simData.core.simdata.SimData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ObjectDataService<T> {

    @Autowired
    private ObjectDataRepository objectDataRepository;

    @Autowired
    private ObjectDataFactory<T> trackFactory = new ObjectDataFactory<>();//TODO: remove constructor


    public ArrayList<Object> getAll(String type) {
        ArrayList<Object> objs = new ArrayList<>();
        objectDataRepository.findByType(type).forEach(
                objectData -> objs.add(objectData.getValue())
        );
        System.out.println(objs);
        return objs;
    }

    public ArrayList<Object> getBy(String type, String key, String value) {
        ArrayList<Object> objs = new ArrayList<>();
        objectDataRepository.findBy(type,key, value).forEach(
                objectData -> objs.add(objectData.getValue())
        );
        return objs;
    }

    public T create(T obj, User user) {
        ObjectData objectData = trackFactory.create(obj);
        System.out.println("obj: " + objectData.getValue());
        objectData.setCreator(user);
        objectDataRepository.save(objectData);
        //get the object from the database
        //return (T) objectDataRepository.findBy(objectData.getType(), "name", objectData.getAttribute("name").get().getValue()).get(0).getValue();
        return (T) objectData.getValue();
    }

    public ArrayList<Object> getAttributeDatas(String type, String name, String attribute) {
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

    public Long getId(String type, String name) {
        return objectDataRepository.findBy(type, "name", name).get(0).getId();
    }

    public ObjectData getObject(Long id) {
        return objectDataRepository.findById(id).get();
    }

    public void delete(String type, String name) {
        objectDataRepository.deleteBy(type, "name", name);
    }
}
