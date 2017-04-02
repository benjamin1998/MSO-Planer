package com.randerath.johannes.msoplaner;

import java.util.Date;

class Exam {
    private String date;
    private Subject subject;
    private String notes;

    Exam(String pDate, Subject pSubject, String notes) {
        date = pDate;
        subject = pSubject;
    }

    Subject getSubject() {
        return subject;
    }

    String getDate() {
        return date;
    }

    String getNotes() {
        return notes;
    }

    void setSubject(Subject pSubject) {
        if(pSubject != null) {
            subject = pSubject;
        }
    }

    void setDate(String pDate) {
        if(date != null && !date.equals("") && !date.equals(" ")) {
            date = pDate;
        }
    }

    void setNotes(String pNotes) {
        if(pNotes != null) {
            notes = pNotes;
        }
    }

}
