package com.durbo.simData.core;

import com.durbo.simData.core.datas.IntegerData;
import com.durbo.simData.core.object.ObjectData;
import com.durbo.simData.core.datas.StringData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest(classes = {StringData.class, IntegerData.class, ObjectData.class})
public class DataTest {

        @Test
        public void testStringData() {
            StringData stringData = new StringData("test");
            Assert.isTrue(stringData.getValue().equals("test"), "StringValue.getValue() failed");
        }

        @Test
        public void testIntegerData() {
            IntegerData integerData = new IntegerData(1);
            Assert.isTrue(integerData.getValue().equals(1), "IntegerValue.getValue() failed");
        }

        @Test
        public void testObjectData() {
            ObjectData objectData = new ObjectData("null", List.of());
            Assert.isTrue(objectData.getType().equals("null"), "ObjectData.getType() failed");
        }

}
