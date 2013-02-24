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
    public void shouldPersistCompany() throws Exception {
        Person person = randomPerson(company);
        getRepository().create(person);

        assertThat(getRepository().find(person.getId()))
            .isNotSameAs(person)
            .isEqualTo(person)
            .isEqualsToByComparingFields(person);
    }

    private Person randomPerson(Company company) {
        Person person = new Person(company);
        person.setPersonName(RandomData.randomWord());
        person.setEmailAddress(RandomData.randomEmail());
        return person;
    }

    @Test
    public void shouldListAll() throws Exception {
        Person p1 = randomPerson(company);
        Person p2 = randomPerson(company);
        getRepository().create(p1);
        getRepository().create(p2);

        assertThat(getRepository().list())
            .contains(p1).contains(p2);
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

    protected abstract PersonRepository getRepository();

    protected CompanyRepository getCompanyRepository() {
        // TODO Auto-generated method stub
        return null;
    }

}