package com.durbo.simData.core;

import com.durbo.simData.core.values.IntegerValue;
import com.durbo.simData.core.values.StringValue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
public class AttributeTest {

    @Test
    public void testStringAttribute(){
        Attribute attribute = new Attribute("test", TYPE.STRING);
        StringValue stringValue = new StringValue("test");
        attribute.addData(stringValue);
        Assert.isTrue(attribute.getDatas().size() == 1, "Attribute.addData() failed");
    }

    @Test
    public void testIntegerAttribute(){
        Attribute attribute = new Attribute("test", TYPE.INTEGER);
        IntegerValue integerValue = new IntegerValue(1);
        attribute.addData(integerValue);
        Assert.isTrue(attribute.getDatas().size() == 1, "Attribute.addData() failed");
    }

    @Test
    public void testIntegerAttributeWrongType(){
        Attribute attribute = new Attribute("test", TYPE.INTEGER);
        StringValue stringValue = new StringValue("test");
        attribute.addData(stringValue);
        Assert.isTrue(attribute.getDatas().size() == 0, "Attribute.addData() failed");
    }
}
