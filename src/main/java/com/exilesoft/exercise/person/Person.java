package com.exilesoft.exercise.person;

import java.io.Serializable;

import com.exilesoft.exercise.company.Company;

public class Person implements Serializable {

    private String personName;

    private String emailAddress;

    private Company company;

	private Long id;

	Person() {
	}

    public Person(Company company) {
        this.company = company;
    }

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

    public Company getCompany() {
        return company;
    }

	public Long getId() {
		return id;
	}

}
