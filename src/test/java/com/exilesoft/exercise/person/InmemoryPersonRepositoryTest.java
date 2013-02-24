package com.exilesoft.exercise.person;

import com.exilesoft.exercise.company.CompanyRepository;
import com.exilesoft.exercise.company.InmemoryCompanyRepository;



public class InmemoryPersonRepositoryTest extends AbstractPersonRepositoryTest {

    private final InmemoryPersonRepository repository = new InmemoryPersonRepository();

    @Override
    protected PersonRepository getRepository() {
        return repository;
    }

    private final InmemoryCompanyRepository companyRepository = new InmemoryCompanyRepository();

    @Override
    protected CompanyRepository getCompanyRepository() {
        return companyRepository;
    }


}
