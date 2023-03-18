package com.durbo.simData.core;

import com.durbo.simData.core.values.IntegerValue;
import com.durbo.simData.core.values.StringValue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class ValueTest {

        @Test
        public void testStringValue() {
            StringValue stringValue = new StringValue("test");
            Assert.isTrue(stringValue.getValue().equals("test"), "StringValue.getValue() failed");
        }

        @Test
        public void testIntegerValue() {
            IntegerValue integerValue = new IntegerValue(1);
            Assert.isTrue(integerValue.getValue().equals(1), "IntegerValue.getValue() failed");
        }
}
