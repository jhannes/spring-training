package com.exilesoft.exercise.person;

import java.io.Serializable;

public class Person implements Serializable {

    private String personName;

    private String emailAddress;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }




}
