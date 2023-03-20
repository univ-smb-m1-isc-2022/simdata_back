package com.durbo.simData.core.datas;

import com.durbo.simData.core.SimData;
import com.durbo.simData.core.TYPE;
import com.durbo.simData.core.attributes.Attribute;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

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
}
