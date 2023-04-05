package com.durbo.simData.core.object;

import com.durbo.simData.core.attributes.Attribute;
import com.durbo.simData.core.attributes.AttributeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ObjectDataController {

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private ObjectDataService objectDataService;


    @GetMapping("/objects/{id}/{name}")
    public Attribute getAttribute(@PathVariable Long id, @PathVariable String name) {
        return attributeService.getAttribute(id, name);
    }

    @GetMapping("/objects/{id}")
    public ObjectData getObject(@PathVariable Long id) {
        return objectDataService.getObject(id);
    }
}
