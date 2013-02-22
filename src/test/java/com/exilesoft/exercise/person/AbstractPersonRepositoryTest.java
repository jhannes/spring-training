package com.exilesoft.exercise.person;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.exilesoft.exercise.RandomData;
import com.exilesoft.exercise.company.AbstractCompanyRepositoryTest;
import com.exilesoft.exercise.company.Company;
import com.exilesoft.exercise.company.CompanyRepository;

public abstract class AbstractPersonRepositoryTest {

    private Company company = AbstractCompanyRepositoryTest.randomCompany();

    @Before
    public void saveCompany() {
    	getCompanyRepository().create(company);
    	company = getCompanyRepository().find(company.getId());
    }

	@Test
    public void shouldPersistPerson() throws Exception {
        Person person = randomPerson(company);
        getRepository().create(person);

        assertThat(getRepository().find(person.getId()))
            .isNotSameAs(person)
            .isEqualTo(person)
            .isEqualsToByComparingFields(person);
    }

	@Test
	public void shouldBeAssociatedWithCompany() throws Exception {
		Person person = randomPerson(company);
		getRepository().create(person);

		person.getCompany().setCompanyName(RandomData.randomWord());
		getCompanyRepository().update(person.getCompany());

		assertThat(getRepository().find(person.getId()).getCompany().getCompanyName())
			.isEqualTo(person.getCompany().getCompanyName());
	}

	@Test
	public void shouldFindByCompany() throws Exception {
		Person person1 = randomPerson(company);
		Person person2 = randomPerson(company);
		getRepository().create(person1);
		getRepository().create(person2);

		Company otherCompany = AbstractCompanyRepositoryTest.randomCompany();
		getCompanyRepository().create(otherCompany);
		Person personInOtherCompany = randomPerson(otherCompany);
		getRepository().create(personInOtherCompany);

		assertThat(getCompanyRepository().find(company.getId()).getPeople())
			.contains(person1, person2)
			.doesNotContain(personInOtherCompany);
	}


    private Person randomPerson(Company company) {
		Person person = company.createPerson();
		person.setPersonName(RandomData.randomWord() + " " + RandomData.randomWord() + "son");
		person.setEmailAddress(RandomData.randomEmail());
		getCompanyRepository().update(company);
		return person;
	}

    protected abstract PersonRepository getRepository();

    protected abstract CompanyRepository getCompanyRepository();

}