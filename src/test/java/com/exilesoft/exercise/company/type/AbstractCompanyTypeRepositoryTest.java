package com.exilesoft.exercise.company.type;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

import com.exilesoft.exercise.RandomData;

public abstract class AbstractCompanyTypeRepositoryTest {

    protected abstract CompanyTypeRepository createCompanyTypeRepository();

    @Test
    public void shouldPersistCompanyType() throws Exception {
        CompanyType companyType = randomCompanyType();
        CompanyTypeRepository repository = createCompanyTypeRepository();

        repository.create(companyType);

        assertThat(repository.find(companyType.getId()))
            .isNotSameAs(companyType)
            .isEqualsToByComparingFields(companyType)
            .isEqualTo(companyType);
    }

    private CompanyType randomCompanyType() {
        return new CompanyType(RandomData.randomWord());
    }

}