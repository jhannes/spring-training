package com.exilesoft.exercise.person;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.exilesoft.exercise.RandomData;
import com.exilesoft.exercise.company.AbstractCompanyRepositoryTest;
import com.exilesoft.exercise.company.Company;
import com.exilesoft.exercise.company.CompanyRepository;

public abstract class AbstractPersonRepositoryTest {

    private final Company company = AbstractCompanyRepositoryTest.randomCompany();

    @Before
    public void saveCompany() {
    	getCompanyRepository().create(company);
    }

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
		Person person = new Person(company);
		person.setPersonName(RandomData.randomWord() + " " + RandomData.randomWord() + "son");
		person.setEmailAddress(RandomData.randomEmail());
		return person;
	}

    protected abstract PersonRepository getRepository();

    protected abstract CompanyRepository getCompanyRepository();

}