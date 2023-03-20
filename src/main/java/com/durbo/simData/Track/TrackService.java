package com.durbo.simData.Track;

import com.durbo.simData.core.ObjectDataRepository;
import com.durbo.simData.core.TYPE;
import com.durbo.simData.core.datas.DoubleData;
import com.durbo.simData.core.datas.IntegerData;
import com.durbo.simData.core.datas.ObjectData;
import com.durbo.simData.core.datas.StringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Service
public class TrackService {

    @Autowired
    private ObjectDataRepository objectDataRepository;

    @Autowired
    private ObjectFactory<Track> trackFactory = new ObjectFactory<Track>();

    public Track convert(ObjectData objectData){
        Track track = new Track();
        //TODO: simplify this
        Field[] fields = track.getClass().getFields();
        System.out.println("fields: " + fields.length);
        for(Field field:fields){
            System.out.println("field: " + field.getName());
            switch (field.getType().getSimpleName()){
                case "String" -> {
                    StringData stringData = (StringData) objectData.getAttribute(field.getName()).orElseThrow().getValidData();
                    try {
                        field.set(track, stringData.getValue());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                case "double" -> {
                    DoubleData doubleData = (DoubleData) objectData.getAttribute(field.getName()).orElseThrow().getValidData();
                    try {
                        field.set(track, doubleData.getValue());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                case "Integer" -> {
                    IntegerData doubleData = (IntegerData) objectData.getAttribute(field.getName()).orElseThrow().getValidData();
                    try {
                        field.set(track, doubleData.getValue());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                default -> {
                }
            }
        }
        /*
        track.name = ((StringData) objectData.getAttribute("name").getValidData()).getValue();
        track.country = ((StringData) objectData.getAttribute("country").getValidData()).getValue();
        track.latitude = ((DoubleData) objectData.getAttribute("latitude").getValidData()).getValue();
        track.longitude = ((DoubleData) objectData.getAttribute("longitude").getValidData()).getValue();
         */
        return track;
    }


    public ArrayList<Track> getAll() {
        ArrayList<Track> tracks = new ArrayList<>();
        objectDataRepository.findByType(TYPE.TRACK)
                .ifPresent(objectData -> {
                    tracks.add(convert(objectData));
                });
        return tracks;
    }

    public Track create(Track track) {
        ObjectData objectData = trackFactory.create(track);
        objectDataRepository.save(objectData);
        return track;
    }
}
