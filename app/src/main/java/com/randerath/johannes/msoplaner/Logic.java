package com.randerath.johannes.msoplaner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Application logic.
 * Stores all data as custom data types in memory.
 * Performs all background action.
 * Passed with every Intent.
 */

public class Logic {

    private Stack<Subject> subjects;
    private Day[] days;
    private Stack<Exam> exams;
    private Stack<Task> tasks;
    private DateFormat dateFormat;

    public Logic() {

        subjects = new Stack<>();
        days = new Day[] {new Day(0, "Monday"), new Day(1, "Tuesday"), new Day(2, "Wednesday"), new Day(3, "Thursday"), new Day(4, "Friday"), new Day(5, "Saturday"), new Day(6, "Sunday"), };
        exams = new Stack<>();
        tasks = new Stack<>();
        dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    }

    /**
     * Add a new subject.
     * @param pName Name of the Subject. E.g. Mathematics.
     * @param pAbbreviation Short name. E.g. Maths
     * @param pPlace Room or facility
     * @param pTeacher Name of teacher.
     * @throws IllegalArgumentException if no name is given or a subject by that name is already existing.
     */

    public void addSubject(String pName, String pAbbreviation, String pPlace, String pTeacher) throws IllegalArgumentException{

        //Set variables from parameters or defaults.
        int id;
        String name, abbreviation, place, type, teacher;

        if(!subjects.isEmpty()) id = subjects.peek().getId() + 1;
        else id = 0;

        if(pName.equals("") || pName.equals(" ")) throw new IllegalArgumentException("empty name string");
        else name = pName;

        if(pAbbreviation.equals("") || pAbbreviation.equals(" ")) abbreviation = pName;
        else abbreviation = pAbbreviation;

        if(pPlace.equals("") || pPlace.equals(" ")) place = "";
        else place = pPlace;

        if(pTeacher.equals("") || pTeacher.equals(" ")) teacher = "";
        else teacher = pTeacher;

        //Insert subject at correct position (alphabetically)
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

    /**
     * @return all subjects as Stack
     */
    public Stack<Subject> getSubjects() {
        return subjects;
    }

    /**
     * @param index Day with 0 = Monday, 1 = Tuesday, ...
     * @return Day at index
     * @throws ArrayIndexOutOfBoundsException if index > than number of days - 1
     */
    public Day getDay(int index) throws ArrayIndexOutOfBoundsException{
        if(index < days.length) {
            return days[index];
        }else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * @param s Subject to delete
     */
    public void removeSubject(Subject s) {
        subjects.remove(s);
    }

    /**
     * @param e Exam to delete
     */
    public void removeExam(Exam e) {
        exams.remove(e);
    }

    /**
     * @param t Homework to delete
     */
    public void removeTask(Task t) {
        tasks.remove(t);
    }

    /**
     * Find specific subject by name
     * @param name to find subject by
     * @return Subject with that name
     */
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

    /**
     * Create new Exam
     * @param date as dd.mm.yyyy
     * @param subject from Stack subjects
     * @param notes as String
     */
    public void addExam(String date, Subject subject, String notes) {
        exams.add(new Exam(date, subject, notes));
    }

    /**
     * @return all exams as Array
     */
    public Exam[] getExams() {
        return exams.toArray(new Exam[exams.size()]);
    }

    /**
     * Create new homework
     * @param subject from Stack subjects
     * @param dueDate as dd.mm.yyyy
     * @param description as String
     */
    public void addTask(Subject subject, String dueDate, String description) { tasks.add(new Task(subject, dueDate, description));}

    /**
     * @return all homework as Array
     */
    public Task[] getTasks() {
        return tasks.toArray(new Task[tasks.size()]);
    }

    /**
     * @return lesson at vector(day, index)
     */
    public Lesson getLesson(int day, int index){
        return getDay(day).getLessons()[index];
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

}
