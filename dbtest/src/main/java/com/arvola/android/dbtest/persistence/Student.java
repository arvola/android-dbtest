package com.arvola.android.dbtest.persistence;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.List;

public class Student {
    long id;
    String firstName;
    String lastName;
    String socialSecurityNumber;
    String race;
    Calendar dateOfBirth;
    Gender gender;
    List<Subject> subjects;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public static enum Gender {
        @SerializedName("Male")
        MALE,
        @SerializedName("Female")
        FEMALE
    }
}
