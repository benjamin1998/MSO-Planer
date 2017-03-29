package com.randerath.johannes.msoplaner;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

class Day {

    private int index;
    private String name;
    private List<Lesson> lessons;

    Day(int pIndex, String pName) {
        index = pIndex;
        name = pName;
        lessons = new ArrayList<Lesson>();
    }

    /*public void addLesson(Subject pSubject, int pTime) throws IllegalArgumentException {


        Lesson newLesson = new Lesson(pSubject, pTime);
        Lesson l = lessons.getContent();
        if(!lessons.hasAccess() || l.getTime() < pTime) {
            lessons.append(newLesson);
        }else {
            lessons.toFirst();
            while(l.getTime() < pTime && lessons.hasAccess()) {
                lessons.next();
            }
            if(l.getTime() == pTime) {
                throw new IllegalArgumentException("existing lesson at that time!");
            }else {
                if(lessons.hasAccess()) {
                    lessons.next();
                    lessons.insert(newLesson);
                }else {
                    lessons.append(newLesson);
                }
            }
        }

        bubbleSortLessonsByTime();

    }*/

    public void addLesson(Subject pSubject, int pTime) throws IllegalArgumentException {
        if(lessons.isEmpty() || lessons.get(lessons.size()-1).getTime() < pTime) {
            lessons.add(new Lesson(pSubject, pTime));
        }else {
            int i = 0;
            while((i < lessons.size()) && (lessons.get(i).getTime() < pTime)) {
                i++;
            }
            if(lessons.get(i).getTime() == pTime) {
                throw new IllegalArgumentException("existing lesson at that time!");
            }else {
                lessons.add(i, new Lesson(pSubject, pTime));
            }
        }
    }

    /*private void bubbleSortLessonsByTime() {

        Lesson[] temp = new Lesson[lessons.getSize()];
        lessons.toFirst();
        for(int i = 0; i < temp.length; i++) {
            temp[i] = lessons.getContent();
        }

        boolean changed = true;
        int done = 1;
        while(changed) {
            changed = false;
            for(int i = 0; i < (temp.length - done); i++) {
                //if(temp[i].getStartHour() > temp[i+1].getStartHour() && (temp[i].getStartHour() == temp[i+1].getStartHour() && temp[i].getStartMin() > temp[i+1].getStartHour())) {
                if(temp[i].getTime() > temp[i+1].getTime()) {
                    Lesson mem = temp[i];
                    temp[i] = temp[i+1];
                    temp[i+1] = mem;
                    changed = true;
                }
            }
        }
        lessons = new List<Lesson>();
        for (Lesson aTemp : temp) {
            lessons.append(aTemp);
        }
    }*/

    public Lesson[] getLessons() {
        /*bubbleSortLessonsByTime();
        Lesson[] l = new Lesson[lessons.getSize()];
        lessons.toFirst();
        for(int i = 0; i < lessons.getSize(); i++) {
            Log.i("", "" + lessons.getContent().getClass());
            l[i] = lessons.getContent();
            lessons.next();
        }*/
        return lessons.toArray(new Lesson[lessons.size()]);
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }


}
