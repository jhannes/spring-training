package com.exilesoft.exercise.company;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

import com.exilesoft.exercise.RandomData;
import com.exilesoft.exercise.company.type.CompanyType;

public abstract class AbstractCompanyRepositoryTest {

    @Test
    public void shouldPersistCompany() throws Exception {
        Company company = randomCompany();
        createRepository().create(company);

        assertThat(createRepository().find(company.getId()))
            .isNotSameAs(company)
            .isEqualTo(company)
            .isEqualsToByComparingFields(company);
    }

    @Test
	public void shouldListAllCompanies() throws Exception {
        Company company1 = randomCompany(), company2 = randomCompany();
        createRepository().create(company1);
        createRepository().create(company2);

        assertThat(createRepository().list())
        	.contains(company1, company2);
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

    protected abstract CompanyRepository createRepository();

}