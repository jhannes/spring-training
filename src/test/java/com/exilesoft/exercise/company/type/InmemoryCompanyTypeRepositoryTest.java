package com.exilesoft.exercise.company.type;


public class InmemoryCompanyTypeRepositoryTest extends AbstractCompanyTypeRepositoryTest {

    private final InmemoryCompanyTypeRepository repository = new InmemoryCompanyTypeRepository();

    @Override
    protected CompanyTypeRepository getRepository() {
        return repository;
    }

}
