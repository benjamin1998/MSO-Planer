package com.randerath.johannes.msoplaner;

import android.support.annotation.NonNull;

/**
 * Datatype for storing Lesson objects.
 * Objects are held in the Application logic.
 * Content is used in LessonFragments to visualize lessons.
 */

class Lesson {

    private Subject sbjct;
    private int time;

    Lesson(@NonNull Subject pSbjct, int pTime) {

        sbjct = pSbjct;

        if(pTime > 0) {
            time = pTime;
        }

    }

    /**
     * @return subject taught
     */

    public Subject getSubject() {
        return sbjct;
    }

    /**
     * @param pValue new subject
     */
    void setSbjct(Subject pValue) {
        sbjct = pValue;
    }

    /**
     * @return Time of lesson as index 1..12
     */
    int getTime() {
        return time;
    }

    /**
     * @param pTime new time as index 1..12
     */
    void setTime(int pTime) {
        if(pTime > 0) {
            time = pTime;
        }
    }

}