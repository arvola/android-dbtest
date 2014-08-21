package com.arvola.android.dbtest.persistence;

import java.util.ArrayList;
import java.util.List;

public class School {
    long id;
    String name;
    List<Grade> grades = new ArrayList<Grade>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
}
