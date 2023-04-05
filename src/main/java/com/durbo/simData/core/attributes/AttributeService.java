package com.durbo.simData.core.attributes;

import com.durbo.simData.core.attributes.Attribute;
import com.durbo.simData.core.object.ObjectData;
import com.durbo.simData.core.object.ObjectDataRepository;
import com.durbo.simData.core.object.ObjectDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttributeService {

    @Autowired
    private ObjectDataRepository objectDataRepository;


    public Attribute getAttribute(Long parentId, String name) {
        ObjectData objectData = objectDataRepository.findById(parentId).get();
        return objectData.getAttribute(name).get();
    }

}
