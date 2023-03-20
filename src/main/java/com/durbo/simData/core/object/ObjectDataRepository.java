package com.durbo.simData.core.object;

import com.durbo.simData.core.TYPE;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository
public interface ObjectDataRepository extends CrudRepository<ObjectData, Long> {

    Optional<ObjectData> findByType(TYPE type);

    //find by attribute name and value
    default Optional<ObjectData> findBy(TYPE type,String key, Object value) {
        Optional<ObjectData> objectDatas = this.findByType(type);
        return
        Optional
                .of(objectDatas.get())
                .filter(objectData ->
                        Optional
                                .of(objectData.getAttributes())
                                .filter(attributes ->
                                        attributes
                                                .stream()
                                                .anyMatch(attribute ->
                                                        Objects.equals(attribute.getName(), key) &&
                                                                Objects.equals(attribute.getValidData().getValue(), value)
                                                )
                                )
                                .isPresent()
                );
    }

}
