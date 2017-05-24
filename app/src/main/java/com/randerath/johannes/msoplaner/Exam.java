package com.randerath.johannes.msoplaner;

/**
 * Datatype for storing Exam objects.
 * Objects are held in the Application logic.
 * Content is used in ExamFragments to visualize Exams.
 */
class Exam {
    private String date;
    private Subject subject;
    private String notes;

    Exam(String pDate, Subject pSubject, String notes) {
        date = pDate;
        subject = pSubject;
    }

    /**
     * @return Subject to write exam in.
     */
    Subject getSubject() {
        return subject;
    }

    /**
     * @return Date to write exam at.
     */
    String getDate() {
        return date;
    }

    /**
     * @return User definded notes to exam.
     */
    String getNotes() {
        return notes;
    }

    /**
     * @param pSubject changed subject to write subject in
     */
    void setSubject(Subject pSubject) {
        if(pSubject != null) {
            subject = pSubject;
        }
    }

    /**
     * @param pDate new date to write exam at.
     */

    void setDate(String pDate) {
        if(date != null && !date.equals("") && !date.equals(" ")) {
            date = pDate;
        }
    }

    /**
     * @param pNotes changed notes
     */
    void setNotes(String pNotes) {
        if(pNotes != null) {
            notes = pNotes;
        }
    }

}
