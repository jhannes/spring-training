package com.exilesoft.exercise.company;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

import com.exilesoft.exercise.RandomData;
import com.exilesoft.exercise.company.type.AbstractCompanyTypeRepositoryTest;

public abstract class AbstractCompanyRepositoryTest {

    @Test
    public void shouldPersistCompany() throws Exception {
        Company company = randomCompany();
        getRepository().create(company);

        assertThat(getRepository().find(company.getId()))
            .isNotSameAs(company)
            .isEqualTo(company)
            .isLenientEqualsToByIgnoringFields(company, "people");
    }

    public static Company randomCompany() {
        Company company = new Company();
        company.setCompanyName(RandomData.randomWord());
        company.setCompanyType(AbstractCompanyTypeRepositoryTest.randomCompanyType());
        return company;
    }

    @Test
    public void shouldListAllCompanyTypes() throws Exception {
        Company type1 = randomCompany();
        Company type2 = randomCompany();
        getRepository().create(type1);
        getRepository().create(type2);

        assertThat(getRepository().list())
            .contains(type1).contains(type2);
    }

    @Test
    public void shouldSearchForCompany() throws Exception {
        Company matchingCompany = randomCompany();
        Company nonMatchingCompany = randomCompany();

        matchingCompany.setCompanyName("Exilesoft");
        getRepository().create(matchingCompany);
        getRepository().create(nonMatchingCompany);

        assertThat(getRepository().findByNameLike("XILE"))
            .contains(matchingCompany)
            .doesNotContain(nonMatchingCompany);
        assertThat(getRepository().findByNameLike("exil"))
            .contains(matchingCompany)
            .doesNotContain(nonMatchingCompany);
    }

    protected abstract CompanyRepository getRepository();

}