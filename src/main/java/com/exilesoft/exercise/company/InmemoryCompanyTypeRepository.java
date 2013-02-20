package com.exilesoft.exercise.company;

import java.util.Arrays;
import java.util.List;

public class InmemoryCompanyTypeRepository implements CompanyTypeRepository {

    @Override
    public List<CompanyType> list() {
        return Arrays.asList(new CompanyType("Software"), new CompanyType("Offshoring"));
    }

}
