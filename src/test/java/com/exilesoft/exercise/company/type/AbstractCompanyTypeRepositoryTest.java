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

    @Test
    public void shouldListAllCompanyTypes() throws Exception {
        CompanyType type1 = randomCompanyType();
        CompanyType type2 = randomCompanyType();
        getRepository().create(type1);
        getRepository().create(type2);

        assertThat(getRepository().list())
            .contains(type1).contains(type2);
    }

    private CompanyType randomCompanyType() {
        return new CompanyType(RandomData.randomWord());
    }

    protected abstract CompanyTypeRepository getRepository();

}