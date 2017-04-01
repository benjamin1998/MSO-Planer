package com.randerath.johannes.msoplaner;

import java.util.Date;

class Exam {
    private String date;
    private Subject subject;

    Exam(String pDate, Subject pSubject) {
        date = pDate;
        subject = pSubject;
    }

    Subject getSubject() {
        return subject;
    }

    String getDate() {
        return date;
    }

}
