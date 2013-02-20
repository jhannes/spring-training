package com.exilesoft.exercise.company;

import java.util.ArrayList;
import java.util.List;

public class InmemoryCompanyRepository implements CompanyRepository {

    private static List<Company> companies = new ArrayList<>();

    @Override
    public void create(Company newObject) {
        companies.add(newObject);
    }

    @Override
    public List<Company> list() {
        return companies;
    }

    @Override
    public Company find(Long id) {
        for (Company company : companies) {
            if (company.getId().equals(id)) return company;
        }
        return null;
    }

}
