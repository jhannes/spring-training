package com.exilesoft.exercise.company.type;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class InmemoryCompanyTypeRepository implements CompanyTypeRepository {

    @Override
    public List<CompanyType> list() {
        return Arrays.asList(new CompanyType("Software"), new CompanyType("Offshoring"));
    }

}
