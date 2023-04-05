package com.durbo.simData.Track;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Layout {
    public String name;
    public Integer grade;
    //public ArrayList<String> tags;
    //public ArrayList<String> surface;
    public double length;
    public Integer turns;
    public Integer constructionYear;
    public Integer demolitionYear;
}
