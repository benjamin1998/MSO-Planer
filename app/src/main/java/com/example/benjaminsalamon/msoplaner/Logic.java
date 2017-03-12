package com.example.benjaminsalamon.msoplaner;

class Logic {

    private Stack<Subject> subjects;

    public Logic() {

        subjects = new Stack<Subject>();

    }

    public void addSubject(String pName, String pAbbreviation, String pPlace, String pType, String pTeacher) {

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

    public Stack<Subject> getSubjects() {
        return subjects;
    }



}
