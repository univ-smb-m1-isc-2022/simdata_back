package com.durbo.simData.Track;

import com.durbo.simData.core.Factory;
import com.durbo.simData.core.TYPE;
import com.durbo.simData.core.attributes.Attribute;
import com.durbo.simData.core.datas.DoubleData;
import com.durbo.simData.core.datas.ObjectData;
import com.durbo.simData.core.datas.StringData;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TrackFactory{


    private ArrayList<Attribute> getAttributes(Track track){
        ArrayList<Attribute> attributes = new ArrayList<>();
        Field[] fields = track.getClass().getDeclaredFields();
        for (Field field : fields) {
            switch (field.getType().getSimpleName()) {
                case "String" -> attributes.add(new Attribute(field.getName(), TYPE.STRING));
                case "double" -> attributes.add(new Attribute(field.getName(), TYPE.DOUBLE));
                case "Integer" -> attributes.add(new Attribute(field.getName(), TYPE.INTEGER));
                case "Boolean" -> attributes.add(new Attribute(field.getName(), TYPE.BOOLEAN));
                default -> {
                }
            }
        }
        return attributes;
    }

    private void setDatas(ObjectData objectData, Track track) throws IllegalAccessException {
        Field[] fields = track.getClass().getDeclaredFields();
        for (Field field : fields) {
            switch (field.getType().getSimpleName()) {
                case "String" -> {
                    objectData.getAttribute(field.getName()).addData(new StringData((String) field.get(track)));
                }
                case "double" -> {
                    objectData.getAttribute(field.getName()).addData(new DoubleData((double) field.get(track)));
                }
                case "Integer" -> {
                    objectData.getAttribute(field.getName()).addData(new DoubleData((Integer) field.get(track)));
                }
                default -> {
                }
            }
        }
    }

    public ObjectData create(Track track) {
        ObjectData object = new ObjectData(
                TYPE.TRACK,
                getAttributes(track)
        );
        try {
            setDatas(object, track);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;
    }

    public Track convert(ObjectData objectData){
        Track track = new Track();
        track.name = ((StringData) objectData.getAttribute("name").getValidData()).getValue();
        track.country = ((StringData) objectData.getAttribute("country").getValidData()).getValue();
        track.latitude = ((DoubleData) objectData.getAttribute("latitude").getValidData()).getValue();
        track.longitude = ((DoubleData) objectData.getAttribute("longitude").getValidData()).getValue();
        return track;
    }
}
