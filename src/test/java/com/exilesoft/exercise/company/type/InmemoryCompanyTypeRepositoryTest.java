package com.exilesoft.exercise.company.type;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

import com.exilesoft.exercise.RandomData;

public class InmemoryCompanyTypeRepositoryTest {

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

    private CompanyTypeRepository createCompanyTypeRepository() {
        return new InmemoryCompanyTypeRepository();
    }

    private CompanyType randomCompanyType() {
        return new CompanyType(RandomData.randomWord());
    }


}
