package com.randerath.johannes.msoplaner;

/**
 * Datatype for storing homework objects.
 * Objects are held in the Application logic.
 * Content is used in HomeworkFragments to visualize homework.
 */

class Task {

    private Subject subject;
    //TODO: use actual Date objects
    private String dueDate;
    private String description;

    public Task(Subject pSubject, String pDueDate, String pDescription) {
        //assign variable values from parameters
        subject = pSubject;
        dueDate = pDueDate;
        description = pDescription;
    }

    /**
     * @return Subject as com.randerath.johannes.msoplaner.Subject
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * @return dueDate as String
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * @return description as String
     */
    public String getDescription(){
        return description;
    }
}
