package com.exilesoft.exercise.company;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.Test;

import com.exilesoft.exercise.RandomData;
import com.exilesoft.exercise.company.type.AbstractCompanyTypeRepositoryTest;
import com.exilesoft.exercise.company.type.CompanyType;
import com.exilesoft.exercise.company.type.CompanyTypeRepository;

public abstract class AbstractCompanyRepositoryTest {

    private static CompanyType companyType = AbstractCompanyTypeRepositoryTest.randomCompanyType();

	@Test
    public void shouldPersistCompany() throws Exception {
        Company company = randomCompany();
        createRepository().create(company);

        assertThat(createRepository().find(company.getId()))
            .isNotSameAs(company)
            .isEqualTo(company)
            .isLenientEqualsToByIgnoringFields(company, "people");
        assertThat(new ArrayList<>(createRepository().find(company.getId()).getPeople()))
	        .isEqualTo(new ArrayList<>(company.getPeople()));
    }

    @Test
	public void shouldListAllCompanies() throws Exception {
        Company company1 = randomCompany(), company2 = randomCompany();
        createRepository().create(company1);
        createRepository().create(company2);

        assertThat(createRepository().list())
        	.contains(company1, company2);
	}

    @Test
	public void shouldSearchByNameLike() throws Exception {
		Company matchingCompany = randomCompany();
		matchingCompany.setCompanyName("Exilesoft");
		Company nonMatchingCompany = randomCompany();

		createRepository().create(matchingCompany);
		createRepository().create(nonMatchingCompany);

		assertThat(createRepository().findByName("XILE"))
			.contains(matchingCompany)
			.doesNotContain(nonMatchingCompany);
	}


    @Test
	public void shouldBeAssociatedWithType() throws Exception {
        Company company = randomCompany();
        createRepository().create(company);

        company.getCompanyType().setTypeName(RandomData.randomWord());
        createTypeRepository().update(company.getCompanyType());
	}


    public static Company randomCompany() {
		Company company = new Company();
		company.setCompanyName(RandomData.randomWord() + ", Inc");
		company.setCompanyUrl(RandomData.randomUrl());
		company.setCompanyType(companyType);
		return company;
	}

    protected abstract CompanyTypeRepository createTypeRepository();

	protected abstract CompanyRepository createRepository();
}