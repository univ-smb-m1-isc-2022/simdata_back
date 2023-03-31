package com.durbo.simData.core;

import com.durbo.simData.core.attributes.Attribute;
import com.durbo.simData.core.subdata.IntegerData;
import com.durbo.simData.core.subdata.StringData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest(classes = {Attribute.class})
public class AttributeTest {

    @Test
    public void testStringAttribute(){
        Attribute attribute = new Attribute(null,"test", "String");
        StringData stringData = new StringData("test");
        attribute.addData(stringData);
        Assert.isTrue(attribute.getDatas().size() == 1, "Attribute.addData() failed");
    }

    @Test
    public void testIntegerAttribute(){
        Attribute attribute = new Attribute(null,"test", "Integer");
        IntegerData integerData = new IntegerData(1);
        attribute.addData(integerData);
        Assert.isTrue(attribute.getDatas().size() == 1, "Attribute.addData() failed");
    }

    @Test
    public void testIntegerAttributeWrongType(){
        Attribute attribute = new Attribute(null,"test", "Integer");
        StringData stringData = new StringData("test");
        attribute.addData(stringData);
        Assert.isTrue(attribute.getDatas().size() == 0, "Attribute.addData() failed");
    }
}
