package com.exilesoft.exercise.company;



public class InmemoryCompanyRepositoryTest extends AbstractCompanyRepositoryTest {

    private final InmemoryCompanyRepository repository = new InmemoryCompanyRepository();

    @Override
    protected CompanyRepository createRepository() {
        return repository;
    }

}
