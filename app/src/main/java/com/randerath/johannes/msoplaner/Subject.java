package com.randerath.johannes.msoplaner;

import android.graphics.Color;


class Subject {

    private int id;
    private String name, abbreviation, place, teacher;
    private Color color;

    public Subject(int pId, String pName, String pAbbreviation, String pPlace, String pTeacher) {

        id = pId;
        name = pName;
        //color = pColor;
        abbreviation = pAbbreviation;
        place = pPlace;
        teacher = pTeacher;

    }

    public int getId() {
        return id;
    }

    public void setName(String pValue) {
        name = pValue;
    }

    public String getName() {
        return name;
    }

    public void setAbbreviation(String pValue) {
        abbreviation = pValue;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setPlace(String pValue) {
        place = pValue;
    }

    public String getPlace() {
        return place;
    }

    public void setTeacher(String pValue) {
        teacher = pValue;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setColor(Color pValue) {
        color = pValue;
    }

    public Color getColor() {
        return color;
    }

}
