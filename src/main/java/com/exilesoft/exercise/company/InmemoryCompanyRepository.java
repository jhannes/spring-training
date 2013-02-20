package com.exilesoft.exercise.company;

import java.util.Arrays;
import java.util.List;

public class InmemoryCompanyRepository implements CompanyRepository {

    @Override
    public List<Company> list() {
        Company visma = new Company();
        visma.setCompanyName("Visma");
        visma.setCompanyUrl("http://Visma.com");
        visma.setCompanyType(new CompanyType("Type"));
        return Arrays.asList(visma);
    }

}
