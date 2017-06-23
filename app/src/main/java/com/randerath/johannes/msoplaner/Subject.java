package com.randerath.johannes.msoplaner;

import android.graphics.Color;

/**
 * Datatype for storing subject objects.
 * Objects are held in the Application logic.
 * Content is used in other Datatypes.
 */

//TODO: use colors (as Color objects)

class Subject {

    private int id;
    private String name, abbreviation, place, teacher;
    private Color color;

    public Subject(int pId, String pName, String pAbbreviation, String pPlace, String pTeacher) {

        //assign variable values from parameters
        id = pId;
        name = pName;
        abbreviation = pAbbreviation;
        place = pPlace;
        teacher = pTeacher;

    }

    /**
     * @return primary key
     */
    public int getId() {
        return id;
    }

    /**
     * @param pValue new Name as String
     */
    public void setName(String pValue) {
        name = pValue;
    }

    /**
     * @return subject's full name e.g. mathematics
     */
    public String getName() {
        return name;
    }

    /**
     * @param pValue new abbreviation as String
     */
    public void setAbbreviation(String pValue) {
        abbreviation = pValue;
    }

    /**
     * @return subject's short name e.g. maths
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * @param pValue new room or facility
     */
    public void setPlace(String pValue) {
        place = pValue;
    }

    /**
     * @return subjects room or facility
     */
    public String getPlace() {
        return place;
    }

    /**
     * @param pValue new teacher's name
     */
    public void setTeacher(String pValue) {
        teacher = pValue;
    }

    /**
     * @return teacher's name
     */
    public String getTeacher() {
        return teacher;
    }

    @Deprecated
    public void setColor(Color pValue) {
        color = pValue;
    }

    @Deprecated
    public Color getColor() {
        return color;
    }

}
