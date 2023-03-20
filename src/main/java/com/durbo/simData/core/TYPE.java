package com.durbo.simData.core;

import com.durbo.simData.Track.Track;
import com.durbo.simData.layout.Layout;
import org.springframework.boot.Banner;

public enum TYPE
{
    STRING,
    INTEGER,
    DOUBLE,
    NULL,
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
            case "double" -> {
                return TYPE.DOUBLE;
            }
        }
        System.out.println("ERROR: ObjectBuilder.getType() returned NULL");
        return TYPE.NULL;
    }

    public static Class<?> getClazz(TYPE type){
        switch (type) {
            case TRACK -> {
                return Track.class;
            }
            case LAYOUT -> {
                return Layout.class;
            }
            case TEST -> {
                return Banner.Mode.class;
            }
            case STRING -> {
                return String.class;
            }
            case INTEGER -> {
                return Integer.class;
            }
            case DOUBLE -> {
                return Double.class;
            }
        }
        System.out.println("ERROR: ObjectBuilder.getClass() returned NULL");
        return null;
    }
}

