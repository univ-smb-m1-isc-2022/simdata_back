package com.durbo.simData.core.datas.object;

import com.durbo.simData.core.datas.SimData;
import com.durbo.simData.core.TYPE;
import com.durbo.simData.core.attributes.Attribute;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@EqualsAndHashCode(callSuper = true)
@lombok.Data
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class ObjectData extends SimData {

    @OneToMany(cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    private List<Attribute> attributes;

    public ObjectData(TYPE type, List<Attribute> attributes) {
        super(type);
        this.setAttributes(attributes);
    }

    public ObjectData() {
        super();
        this.setAttributes(null);
    }

    public Optional<Attribute> getAttribute(String name) {
        for (Attribute attribute : attributes) {
            if (attribute.getName().equals(name)) {
                return Optional.of(attribute);
            }
        }
        return Optional.empty();
    }

    /***
     * For each attribute add a field to an Object, then return the object
     * @return the object
     */
    @Override
    public Object getValue(){
        Object object = null;
        //get instance associated with the type, then set the value of the attribute to the field
        try {
            object = TYPE.getClazz(this.getType()).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        Object finalObject = object;
        this.getAttributes().forEach(attribute -> {
            try {
                assert finalObject != null;
                finalObject.getClass().getDeclaredField(attribute.getName()).set(finalObject, attribute.getValidData().getValue());
            } catch (IllegalAccessException | NoSuchFieldException | InstantiationException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("ObjectData.getValue() = " + object);
        System.out.println(object);
        return object;
    }
}
