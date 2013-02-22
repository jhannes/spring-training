package com.exilesoft.exercise.person;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

import com.exilesoft.exercise.RandomData;
import com.exilesoft.exercise.company.Company;
import com.exilesoft.exercise.company.type.CompanyType;

public abstract class AbstractPersonRepositoryTest {

    @Test
    public void shouldPersistPerson() throws Exception {
        Person person = randomPerson();
        getRepository().create(person);

        assertThat(getRepository().find(person.getId()))
            .isNotSameAs(person)
            .isEqualTo(person)
            .isEqualsToByComparingFields(person);
    }

    private Person randomPerson() {
		Person person = new Person(randomCompany());
		person.setPersonName(RandomData.randomWord() + " " + RandomData.randomWord() + "son");
		person.setEmailAddress(RandomData.randomEmail());
		return person;
	}


    private Company randomCompany() {
		Company company = new Company();
		company.setCompanyName(RandomData.randomWord() + ", Inc");
		company.setCompanyUrl(RandomData.randomUrl());
		company.setCompanyType(randomCompanyType());
		return company;
	}

    private CompanyType randomCompanyType() {
        return new CompanyType(RandomData.randomWord());
    }

    protected abstract PersonRepository getRepository();

}