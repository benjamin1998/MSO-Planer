package com.example.benjaminsalamon.msoplaner;

import android.support.annotation.NonNull;

/**
 * Created by johannes on 02/02/2017.
 */

class Lesson {

    private Subject sbjct;
    private int startHour;
    private int startMin;
    private int endHour;
    private int endMin;

    public Lesson(@NonNull Subject pSbjct, int pStartH, int pStartM, int pEndH, int pEndM) {

        sbjct = pSbjct;

        if(pStartH < 24) startHour = pStartH;
        else startHour = 0;

        if(pStartM < 60) startMin = pStartM;
        else startMin = 0;

        if(pStartH < 24 && endHour < startHour) endHour = pEndH;
        else endHour = startHour;

        if(pEndM < 60 && (startHour < endHour || pStartM < startMin)) endMin = pEndM;
        else endMin = startMin;

    }

    public Subject getSubject() {
        return sbjct;
    }

    public void setSbjct(Subject pValue) {
        sbjct = pValue;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int pValue) {
        if(pValue < 24 && (pValue < endHour || (pValue == endHour && startMin < endMin))) {
            startHour = pValue;
        }
    }

    public int getStartMin() {
        return startMin;
    }

    public void setStartMin(int pValue) {
        if(pValue < 60 && (startHour < endHour || (startHour == endHour && pValue < endMin))) {
            startMin = pValue;
        }
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int pValue) {
        if(pValue < 24 && (pValue > startHour || (pValue == startHour && startMin < pValue))) {
            endHour = pValue;
        }
    }

    public int getEndMin() {
        return endMin;
    }

    public void setEndMin(int pValue) {
        if(pValue < 60 && (startHour < endHour || (startHour == endHour && startMin < pValue))) {
            endMin = pValue;
        }
    }

}
