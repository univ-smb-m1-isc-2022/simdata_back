package com.durbo.simData.core.object;

import com.durbo.simData.User.User;
import com.durbo.simData.core.simdata.SimData;
import com.durbo.simData.core.attributes.Attribute;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Optional;

@EqualsAndHashCode(callSuper = true)
@lombok.Data
@Entity
@Table(name = "OBJ")
@DiscriminatorValue("OBJ")
@PrimaryKeyJoinColumn(name = "SD_id")
public class ObjectData extends SimData {

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "objectData", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attribute> attributes;

    public ObjectData(String type, List<Attribute> attributes) {
        super(type);
        this.setAttributes(attributes);
    }

    public ObjectData() {
        super();
        this.setAttributes(null);
    }

    public ObjectData(String type) {
        super(type);
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
            object = this.getrealType().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        Object finalObject = object;
        this.getAttributes().forEach(attribute -> {
            try {
                assert finalObject != null;
                finalObject.getClass().getDeclaredField(attribute.getName()).set(finalObject, attribute.getValue());
            } catch (IllegalAccessException | NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        });
        return finalObject;
    }

    @Override
    public void setCreator(User creator) {
        super.setCreator(creator);
        for (Attribute attribute : attributes) {
            for (SimData simData : attribute.getDatas()) {
                simData.setCreator(creator);
            }
        }
    }
}
