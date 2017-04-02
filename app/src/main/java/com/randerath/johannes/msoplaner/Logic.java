package com.randerath.johannes.msoplaner;

import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Stack;

public class Logic {

    private Stack<Subject> subjects;
    private Day[] days;
    private Stack<Exam> exams;
    private Stack<Task> tasks;
    private DateFormat dateFormat;

    public Logic() {

        subjects = new Stack<Subject>();
        days = new Day[] {new Day(0, "Monday"), new Day(1, "Tuesday"), new Day(2, "Wednesday"), new Day(3, "Thursday"), new Day(4, "Friday"), new Day(5, "Saturday"), new Day(6, "Sunday"), };
        exams = new Stack<Exam>();
        tasks = new Stack<Task>();
        dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    }

    public void addSubject(String pName, String pAbbreviation, String pPlace, String pTeacher) throws IllegalArgumentException{

        int id;
        String name, abbreviation, place, type, teacher;

        if(!subjects.isEmpty()) id = subjects.peek().getId() + 1;
        else id = 0;

        if(pName.equals("") || pName.equals(" ")) throw new Error("empty name string");
        else name = pName;

        if(pAbbreviation.equals("") || pAbbreviation.equals(" ")) abbreviation = pName;
        else abbreviation = pAbbreviation;

        if(pPlace.equals("") || pPlace.equals(" ")) place = "";
        else place = pPlace;

        if(pTeacher.equals("") || pTeacher.equals(" ")) teacher = "";
        else teacher = pTeacher;

        Subject s = new Subject(id, name, abbreviation, place, teacher);
        ArrayList<Subject> su = new ArrayList<>(subjects);
        if(subjects.empty()) {
            subjects.add(s);
        }else {
            boolean done = false;
            int i = 0;
            while (!done && i < su.size()) {
                if(su.get(i).getName().compareTo(pName) > 0) {
                    i++;
                }else if(su.get(i).getName().compareTo(pName) == 0) {
                    throw new IllegalArgumentException("Subject already exists!");
                }else {
                    su.add(i, s);
                    done = true;
                }
            }
            subjects = new Stack<Subject>();
            subjects.addAll(su);
        }
    }

    public Stack<Subject> getSubjects() {
        return subjects;
    }

    public Day getDay(int index) throws ArrayIndexOutOfBoundsException{
        if(index < days.length) {
            return days[index];
        }else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void removeSubject(Subject s) {
        subjects.remove(s);
    }

    public void removeExam(Exam e) {
        exams.remove(e);
    }

    public void removeTask(Task t) {
        tasks.remove(t);
    }

    public Subject findSubject(String name) {
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

    public void addExam(String date, Subject subject, String notes) {
        exams.add(new Exam(date, subject, notes));
    }

    public Exam[] getExams() {
        return exams.toArray(new Exam[exams.size()]);
    }

    public void addTask(Subject subject, String dueDate, String description) { tasks.add(new Task(subject, dueDate, description));}

    public Task[] getTasks() {
        return tasks.toArray(new Task[tasks.size()]);
    }

    public Lesson getLesson(int day, int index){
        return getDay(day).getLessons()[index];
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

}
