package com.exilesoft.exercise.company.type;

import static org.fest.assertions.api.Assertions.assertThat;
import org.junit.Test;

import com.exilesoft.exercise.RandomData;

public abstract class AbstractCompanyTypeRepositoryTest {

    @Test
    public void shouldPersistCompanyType() throws Exception {
        CompanyType companyType = randomCompanyType();
        getRepository().create(companyType);

        assertThat(getRepository().find(companyType.getId()))
            .isNotSameAs(companyType)
            .isEqualTo(companyType)
            .isEqualsToByComparingFields(companyType);
    }

    private CompanyType randomCompanyType() {
        return new CompanyType(RandomData.randomWord());
    }

    protected abstract CompanyTypeRepository getRepository();

}