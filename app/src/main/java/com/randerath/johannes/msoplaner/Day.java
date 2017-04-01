package com.randerath.johannes.msoplaner;

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

    public Lesson[] getLessons() {
        return lessons.toArray(new Lesson[lessons.size()]);
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public void removeLesson(int lessonIndex) {
        lessons.remove(lessonIndex);
    }

    public void removeLesson(Lesson lesson) {
        lessons.remove(lesson);
    }
}