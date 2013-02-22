package com.exilesoft.exercise.person;

import java.io.Serializable;

import com.exilesoft.exercise.company.Company;
import com.google.common.base.Objects;

public class Person implements Serializable {

    private String personName;

    private String emailAddress;

    private final Company company;

	private static long idSequence;

	private final Long id = idSequence++;

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


	@Override
	public int hashCode() {
		return Objects.hashCode(personName, emailAddress);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Person))
			return false;
		Person other = (Person) obj;
		return Objects.equal(this.personName, other.personName)
				&& Objects.equal(this.emailAddress, other.emailAddress);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("personName", personName).add("emailAddress", emailAddress)
				.toString();
	}



}
