package com.durbo.simData.core;

import org.springframework.boot.Banner;

public enum TYPE
{
    STRING,
    INTEGER,
    DOUBLE,
    BOOLEAN,
    DATE,
    TIME,
    DATETIME,
    NULL,
    ABSTRACT,
    TRACK,
    TEST,
    LAYOUT;

    public static TYPE get(String s){
        switch (s) {
            case "Track" -> {
                return TYPE.TRACK;
            }
            case "Layout" -> {
                return TYPE.LAYOUT;
            }
            case "Test" -> {
                return TYPE.TEST;
            }
            case "String" -> {
                return TYPE.STRING;
            }
            case "Integer" -> {
                return TYPE.INTEGER;
            }
        }
        System.out.println("ERROR: ObjectBuilder.getType() returned NULL");
        return TYPE.NULL;
    }
}

