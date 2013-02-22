package com.exilesoft.exercise.company;

import org.springframework.stereotype.Repository;

import com.exilesoft.exercise.infrastructure.AbstractInmemoryRepository;

@Repository
public class InmemoryCompanyRepository extends AbstractInmemoryRepository<Company> implements CompanyRepository {


}
