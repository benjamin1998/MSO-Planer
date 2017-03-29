package com.randerath.johannes.msoplaner;

import android.support.annotation.NonNull;

class Lesson {

    private Subject sbjct;
    private int time;

    public Lesson(@NonNull Subject pSbjct, int pTime) {

        sbjct = pSbjct;

        if(pTime > 0) {
            time = pTime;
        }

    }

    public Subject getSubject() {
        return sbjct;
    }

    public void setSbjct(Subject pValue) {
        sbjct = pValue;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int pTime) {
        if(pTime > 0) {
            time = pTime;
        }
    }

}
