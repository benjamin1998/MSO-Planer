package com.example.benjaminsalamon.msoplaner;

import android.graphics.Color;

/**
 * Created by johannes on 02/02/2017.
 */

class Subject {

    private int id;
    private String name, abbreviation, place, type, teacher;
    private Color color;

    public Subject(int pId, String pName, String pAbbreviation, String pPlace, String pType, String pTeacher) {

        id = pId;
        name = pName;
        //color = pColor;
        abbreviation = pAbbreviation;
        place = pPlace;
        teacher = pTeacher;
        type = pType;

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

    public void setType(String pValue) {
        type = pValue;
    }

    public String getType() {
        return type;
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
