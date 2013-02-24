package com.exilesoft.exercise.person;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.exilesoft.exercise.company.Company;
import com.google.common.base.Objects;

@Entity
public class Person implements Serializable {

    private String personName;

    private String emailAddress;

    @ManyToOne
    private Company company;

    @Id
    @GeneratedValue
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
                && Objects.equal(this.personName, other.personName);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("personName", personName)
                .add("emailAddress", emailAddress).toString();
    }


}
