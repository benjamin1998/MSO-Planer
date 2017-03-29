package com.randerath.johannes.msoplaner;

import java.util.Date;

class Exam {
    private Date date;
    private Subject subject;

    Exam(Date pDate, Subject pSubject) {
        date = pDate;
        subject = pSubject;
    }

    Subject getSubject() {
        return subject;
    }

    Date getDate() {
        return date;
    }

}
