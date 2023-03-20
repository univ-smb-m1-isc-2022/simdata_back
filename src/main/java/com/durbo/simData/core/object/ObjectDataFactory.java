package com.durbo.simData.core.object;


import com.durbo.simData.core.TYPE;
import com.durbo.simData.core.attributes.Attribute;
import com.durbo.simData.core.datas.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;

@Service
public class ObjectDataFactory<T>{

    @Autowired
    private DataFactory dataFactory = new DataFactory();


    /***
     * Find the attributes associated with the dictionary
     * @param dictionary the dictionary to find the attributes for
     * @return the attributes associated with the dictionary
     */
    private ArrayList<Attribute> getAttributes(T dictionary) {
        ArrayList<Attribute> attributes = new ArrayList<>();
        Field[] fields = dictionary.getClass().getDeclaredFields();
        for (Field field : fields) {
            // create a new attribute for each field
            //if the field is a dictionary, then create a new object data
            Attribute attribute = new Attribute(field.getName(), TYPE.get(field.getType().getSimpleName()));
            attributes.add(attribute);
        }
        return attributes;
    }

    /***
     * Set the data for the object
     * @param objectData the object to set the data for
     * @param dictionary the dictionary to get the data from
     */
    private void createDatas(ObjectData objectData, T dictionary) throws IllegalAccessException {
        Field[] fields = dictionary.getClass().getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            TYPE fieldType = TYPE.get(field.getType().getSimpleName());
            Attribute attribute = objectData.getAttribute(fieldName).orElseThrow();
            field.setAccessible(true);
            Object value = field.get(dictionary);
            if (value == null) {
                continue;
            }
            System.out.println("field : "+ fieldType +" value: " + value);
            attribute.addData(dataFactory.create(fieldType, value));
        }
    }

    public TYPE getType(T dictionary){
        return TYPE.get(dictionary.getClass().getSimpleName());
    }

    public ObjectData create(T dictionary){
        ObjectData object = new ObjectData(
                getType(dictionary),
                getAttributes(dictionary)
        );
        try {
            createDatas(object, dictionary);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;
    }
}
