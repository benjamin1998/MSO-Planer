package com.randerath.johannes.msoplaner;

class Task {

    private Subject subject;
    private String dueDate;
    private String description;

    public Task(Subject pSubject, String pDueDate, String pDescription) {
        subject = pSubject;
        dueDate = pDueDate;
        description = pDescription;
    }

    public Subject getSubject() {
        return subject;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getDescription(){
        return description;
    }
}
