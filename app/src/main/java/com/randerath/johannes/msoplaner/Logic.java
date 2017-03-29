package com.randerath.johannes.msoplaner;

import java.util.Stack;

class Logic {

    private Stack<Subject> subjects;
    private Day[] days;
    private Stack<Exam> exams;
    private Stack<Task> tasks;

    Logic() {

        subjects = new Stack<Subject>();
        days = new Day[] {new Day(0, "Monday"), new Day(1, "Tuesday"), new Day(2, "Wednesday"), new Day(3, "Thursday"), new Day(4, "Friday"), new Day(5, "Saturday"), new Day(6, "Sunday"), };
        exams = new Stack<Exam>();
        tasks = new Stack<Task>();

    }

    void addSubject(String pName, String pAbbreviation, String pPlace, String pType, String pTeacher) {

        int id;
        String name, abbreviation, place, type, teacher;

        if(!subjects.isEmpty()) id = subjects.peek().getId() + 1;
        else id = 0;

        if(pName.equals("") || pName.equals(" ")) name = null;
        else name = pName;

        if(pAbbreviation.equals("") || pAbbreviation.equals(" ")) abbreviation = null;
        else abbreviation = pAbbreviation;

        if(pPlace.equals("") || pPlace.equals(" ")) place = null;
        else place = pPlace;

        if(pType.equals("") || pType.equals(" ")) type = null;
        else type = pType;

        if(pTeacher.equals("") || pTeacher.equals(" ")) teacher = null;
        else teacher = pTeacher;

        Subject s = new Subject(id, name, abbreviation, place, type, teacher);
        subjects.push(s);
    }

    Stack<Subject> getSubjects() {
        return subjects;
    }

    Day getDay(int index) throws ArrayIndexOutOfBoundsException{
        if(index < days.length) {
            return days[index];
        }else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    void removeSubject(Subject s) {
        subjects.remove(s);
    }

    void removeExam(Exam e) {
        exams.remove(e);
    }

    void removeTask(Task t) {
        tasks.remove(t);
    }

    Subject findSubject(String name) {
        Stack<Subject> temp = (Stack<Subject>) subjects.clone();
        while (!temp.isEmpty()) {
            if (temp.peek().getName().equals(name)) {
                return temp.peek();
            } else {
                temp.pop();
            }
        }
        return null;
    }

    void addExam(String date, Subject subject) {
        exams.add(new Exam(date, subject));
    }

    Exam[] getExams() {
        return exams.toArray(new Exam[exams.size()]);
    }

    void addTask(Subject subject, String dueDate, String description) { tasks.add(new Task(subject, dueDate, description));}

    Task[] getTasks() {
        return tasks.toArray(new Task[tasks.size()]);
    }

}
