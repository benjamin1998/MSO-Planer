package com.example.benjaminsalamon.msoplaner;

class Day {

    private int index;
    private String name;
    private List<Lesson> lessons;

    public Day(int pIndex, String pName) {
        index = pIndex;
        name = pName;
    }

    public void addLesson(Subject pSubject, int pStartH, int pStartM, int pEndH, int pEndM) throws Error {

        Lesson newLesson = new Lesson(pSubject, pStartH, pStartM, pEndH, pEndM);
        lessons.toFirst();
        Lesson l = lessons.getContent();
        while(lessons.hasAccess() && !(l.getStartHour() > newLesson.getEndHour() || (l.getStartHour() == newLesson.getEndHour() && l.getStartMin() > l.getEndMin()))) {
            l = lessons.getContent();
            if((l.getEndHour() < newLesson.getStartHour() || (l.getEndHour() == newLesson.getStartHour() && l.getEndMin() < l.getEndHour()))) {
                lessons.next();
            }else {
                throw new Error("existing lesson at that time");
            }
        }
        l = null;
        lessons.toFirst();
        lessons.insert(newLesson);
        bubbleSortLessonsByTime();

    }

    public void ejdfhs() {
        try {
            addLesson(null, 0, 0, 0, 0);
        }catch (Error e) {

        }
    }

    public void bubbleSortLessonsByTime() {

        Lesson[] temp = new Lesson[lessons.getSize()];
        lessons.toFirst();
        for(int i = 0; i < temp.length; i++) {
            temp[i] = lessons.getContent();
        }

        boolean changed = true;
        int done = 0;
        while(changed) {
            for(int i = 0; i < (temp.length - done); i++) {
                if(temp[i].getStartHour() > temp[i+1].getStartHour() && (temp[i].getStartHour() == temp[i+1].getStartHour() && temp[i].getStartMin() > temp[i+1].getStartHour())) {
                    Lesson mem = temp[i];
                    temp[i] = temp[i+1];
                    temp[i+1] = mem;
                    changed = true;
                }
            }
        }
    }

}
