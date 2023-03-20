package com.durbo.simData.core;

import com.durbo.simData.core.datas.ObjectData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface ObjectDataRepository extends CrudRepository<ObjectData, Long> {

    Optional<ObjectData> findByType(TYPE type);

}
