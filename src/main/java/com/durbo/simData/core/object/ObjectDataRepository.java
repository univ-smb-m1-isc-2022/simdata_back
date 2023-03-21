package com.durbo.simData.core.object;

import com.durbo.simData.core.attributes.Attribute;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface ObjectDataRepository extends CrudRepository<ObjectData, Long> {

    ArrayList<ObjectData> findByType(String type);

    //find by attribute name and value
    default ArrayList<ObjectData> findBy(String type,String key, Object value) {
        ArrayList<ObjectData> objectDatas = this.findByType(type);
        ArrayList<ObjectData> found = new ArrayList<>();
        for (ObjectData objectData : objectDatas) {
            Optional<Attribute> attribute = objectData.getAttribute(key);
            if (attribute.isPresent()) {
                // TODO: check if attribute is an array
                if (attribute.get().getValue().equals(value)) {
                    found.add(objectData);
                }
            }
        }
        return found;
    }

}
