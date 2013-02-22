package com.exilesoft.exercise.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.exilesoft.exercise.infrastructure.AbstractInmemoryRepository;

@Repository
public class InmemoryCompanyRepository extends AbstractInmemoryRepository implements CompanyRepository {

    private final Map<Long, Company> companies = new HashMap<>();

    @Override
    public void create(Company newObject) {
        companies.put(generateId(newObject), newObject);
    }

    @Override
    public List<Company> list() {
        return new ArrayList<>(companies.values());
    }

    @Override
    public Company find(Long id) {
        return clone(companies.get(id));
    }

}
