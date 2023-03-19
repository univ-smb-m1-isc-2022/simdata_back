package com.durbo.simData.core;

import com.durbo.simData.core.attributes.Attribute;
import com.durbo.simData.core.datas.IntegerData;
import com.durbo.simData.core.datas.StringData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest(classes = {Attribute.class})
public class AttributeTest {

    @Test
    public void testStringAttribute(){
        Attribute attribute = new Attribute("test", TYPE.STRING);
        StringData stringData = new StringData("test");
        attribute.addData(stringData);
        Assert.isTrue(attribute.getDatas().size() == 1, "Attribute.addData() failed");
    }

    @Test
    public void testIntegerAttribute(){
        Attribute attribute = new Attribute("test", TYPE.INTEGER);
        IntegerData integerData = new IntegerData(1);
        attribute.addData(integerData);
        Assert.isTrue(attribute.getDatas().size() == 1, "Attribute.addData() failed");
    }

    @Test
    public void testIntegerAttributeWrongType(){
        Attribute attribute = new Attribute("test", TYPE.INTEGER);
        StringData stringData = new StringData("test");
        attribute.addData(stringData);
        Assert.isTrue(attribute.getDatas().size() == 0, "Attribute.addData() failed");
    }
}
