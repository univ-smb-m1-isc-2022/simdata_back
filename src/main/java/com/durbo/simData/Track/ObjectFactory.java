package com.durbo.simData.Track;

import com.durbo.simData.core.Factory;
import com.durbo.simData.core.TYPE;
import com.durbo.simData.core.attributes.Attribute;
import com.durbo.simData.core.datas.DoubleData;
import com.durbo.simData.core.datas.ObjectData;
import com.durbo.simData.core.datas.StringData;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
public class ObjectFactory<T>{


    private ArrayList<Attribute> getAttributes(T dictionary) {
        ArrayList<Attribute> attributes = new ArrayList<>();
        Field[] fields = dictionary.getClass().getDeclaredFields();
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

    private void setDatas(ObjectData objectData, T dictionary) throws IllegalAccessException {
        Field[] fields = dictionary.getClass().getDeclaredFields();
        for (Field field : fields) {
            switch (field.getType().getSimpleName()) {
                case "String" -> {
                    objectData.getAttribute(field.getName()).orElseThrow().addData(new StringData((String) field.get(dictionary)));
                }
                case "double" -> {
                    objectData.getAttribute(field.getName()).orElseThrow().addData(new DoubleData((double) field.get(dictionary)));
                }
                case "Integer" -> {
                    objectData.getAttribute(field.getName()).orElseThrow().addData(new DoubleData((Integer) field.get(dictionary)));
                }
                default -> {
                }
            }
        }
    }

    public ObjectData create(T dictionary){
        ObjectData object = new ObjectData(
                TYPE.TRACK,
                getAttributes(dictionary)
        );
        try {
            setDatas(object, dictionary);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;
    }
}
