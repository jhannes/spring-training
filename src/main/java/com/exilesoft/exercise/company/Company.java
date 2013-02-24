package com.exilesoft.exercise.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.exilesoft.exercise.company.type.CompanyType;
import com.exilesoft.exercise.person.Person;
import com.google.common.base.Objects;

@Entity
public class Company implements Serializable {

    private String companyName;
    private String companyUrl;

    private CompanyType companyType;

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy="company")
	private final List<Person> people = new ArrayList<>();

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

	@Override
    public int hashCode() {
        return Objects.hashCode(companyName, companyUrl);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Company))
            return false;
        Company other = (Company) obj;
        return Objects.equal(this.companyName, other.companyName)
                && Objects.equal(this.companyName, other.companyName);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("companyName", companyName)
                .add("companyUrl", companyUrl).toString();
    }


}
