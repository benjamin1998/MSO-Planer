package com.randerath.johannes.msoplaner;

class Day {

    private int index;
    private String name;
    private List<Lesson> lessons;

    Day(int pIndex, String pName) {
        index = pIndex;
        name = pName;
        lessons = new List<Lesson>();
    }

    public void addLesson(Subject pSubject, /*int pStartH, int pStartM, int pEndH, int pEndM*/ int pTime) throws IllegalArgumentException {


        Lesson newLesson = new Lesson(pSubject, pTime);
        lessons.toLast();
        Lesson l = lessons.getContent();
        if(l.getTime() < pTime) {
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
        /*while(lessons.hasAccess() && !(l.getStartHour() > newLesson.getEndHour() || (l.getStartHour() == newLesson.getEndHour() && l.getStartMin() > l.getEndMin()))) {
            l = lessons.getContent();
            if((l.getEndHour() < newLesson.getStartHour() || (l.getEndHour() == newLesson.getStartHour() && l.getEndMin() < l.getEndHour()))) {
                lessons.next();
            }else {
                throw new Error("existing lesson at that time");
            }
        }
        l = null;
        lessons.toFirst();
        lessons.insert(newLesson);*/
        bubbleSortLessonsByTime();

    }

    private void bubbleSortLessonsByTime() {

        Lesson[] temp = new Lesson[lessons.getSize()];
        lessons.toFirst();
        for(int i = 0; i < temp.length; i++) {
            temp[i] = lessons.getContent();
        }

        boolean changed = true;
        int done = 0;
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
    }

    public Lesson[] getLessons() {
        bubbleSortLessonsByTime();
        Lesson[] l = new Lesson[lessons.getSize()];
        lessons.toFirst();
        for(int i = 0; i < lessons.getSize(); i++) {
            l[i] = lessons.getContent();
            lessons.next();
        }
        return l;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }


}
