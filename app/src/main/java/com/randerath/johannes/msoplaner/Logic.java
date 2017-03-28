package com.randerath.johannes.msoplaner;

class Logic {

    private Stack<Subject> subjects;
    private Day[] days;

    Logic() {

        subjects = new Stack<Subject>();
        days = new Day[] {new Day(0, "Monday"), new Day(1, "Tuesday"), new Day(2, "Wednesday"), new Day(3, "Thursday"), new Day(4, "Friday"), new Day(5, "Saturday"), new Day(6, "Sunday"), };

    }

    void addSubject(String pName, String pAbbreviation, String pPlace, String pType, String pTeacher) {

        int id;
        String name, abbreviation, place, type, teacher;

        if(!subjects.isEmpty()) id = subjects.top().getId() + 1;
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

}
