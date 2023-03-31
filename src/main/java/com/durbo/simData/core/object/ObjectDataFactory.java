package com.durbo.simData.core.object;

import com.durbo.simData.core.attributes.Attribute;
import com.durbo.simData.core.simdata.SimDataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;

@Service
public class ObjectDataFactory<T>{

    @Autowired
    private SimDataFactory dataFactory = new SimDataFactory();

    @Autowired
    private ObjectDataRepository objectDataRepository;



    /***
     * Find the attributes associated with the dictionary
     * @param dictionary the dictionary to find the attributes for
     * @return the attributes associated with the dictionary
     */
    private ArrayList<Attribute> getAttributes(T dictionary) {
        ArrayList<Attribute> attributes = new ArrayList<>();
        Field[] fields = dictionary.getClass().getDeclaredFields();
        for (Field field : fields) {
            Attribute attribute;
            //si le type est une classe, on prend le nom de la classe
            String type = field.getType().getSimpleName();
            if (type.equals("ArrayList")) {
                type = field.getGenericType().getTypeName();
            }
            attribute = new Attribute(field.getName(), type);
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
            String fieldType = field.getType().getSimpleName();
            System.out.println(fieldName + " " + fieldType);
            Attribute attribute = objectData.getAttribute(fieldName).orElseThrow();
            if (fieldType.equals("ArrayList")) {
                for (Object o : (ArrayList) field.get(dictionary)) {
                    fieldType = o.getClass().getSimpleName();
                    attribute.addData(dataFactory.create(fieldType, o));
                }
            } else {
                attribute.addData(dataFactory.create(fieldType, field.get(dictionary)));
            }
            /*

            Attribute attribute = objectData.getAttribute(fieldName).orElseThrow();
            field.setAccessible(true);
            Object value = field.get(dictionary);
            if (value == null) {
                continue;
            }
            //if the type is ArrayList, get the type of the array
            if (fieldType.equals("ArrayList")) {
                for (Object o : (ArrayList) value) {
                    fieldType = o.getClass().getSimpleName();
                    attribute.addData(dataFactory.create(fieldType, o));
                }
            }else{
                attribute.addData(dataFactory.create(fieldType, value));
            }
             */

        }
        System.out.println(objectData);
    }

    private String getType(T dictionary){
        return dictionary.getClass().getSimpleName();
    }

    public ObjectData create(T dictionary){
        ObjectData object = new ObjectData(
                getType(dictionary));

        System.out.println(object);
        try {
            createDatas(object, dictionary);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;

    }
}
