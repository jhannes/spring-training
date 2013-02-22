package com.exilesoft.exercise.company;

import com.exilesoft.exercise.company.type.CompanyTypeRepository;
import com.exilesoft.exercise.company.type.InmemoryCompanyTypeRepository;



public class InmemoryCompanyRepositoryTest extends AbstractCompanyRepositoryTest {

    private final InmemoryCompanyRepository repository = new InmemoryCompanyRepository();
	private final CompanyTypeRepository companyTypeRepository = new InmemoryCompanyTypeRepository();

    @Override
    protected CompanyRepository createRepository() {
        return repository;
    }

    @Override
    protected CompanyTypeRepository createTypeRepository() {
    	return companyTypeRepository;
    }
}
