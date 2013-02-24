package com.exilesoft.exercise.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.exilesoft.exercise.company.type.CompanyType;
import com.exilesoft.exercise.person.Person;

public class Company implements Serializable {

    private String companyName;
    private String companyUrl;

    private CompanyType companyType;

    private Long id;

	private transient final List<Person> people = new ArrayList<>();

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public CompanyType getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

    public Long getId() {
        return id;
    }

    public int getEmployeeCount() {
        return 0;
    }

    public List<Person> getPeople() {
		return people;
	}

	public Person createPerson() {
		Person person = new Person(this);
		this.people.add(person);
		return person;
	}
}
