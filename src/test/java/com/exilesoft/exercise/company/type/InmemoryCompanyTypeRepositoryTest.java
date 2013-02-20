package com.exilesoft.exercise.company.type;



public class InmemoryCompanyTypeRepositoryTest extends AbstractCompanyTypeRepositoryTest {

    @Override
    protected CompanyTypeRepository createCompanyTypeRepository() {
        return new InmemoryCompanyTypeRepository();
    }


}
