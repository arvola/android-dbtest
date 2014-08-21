package com.arvola.android.dbtest.persistence;

public class Subject {
    long id;
    String name;
    int credits;
    Category category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public static enum Category {
        SCIENCE, MATH, SOFT
    }
}
