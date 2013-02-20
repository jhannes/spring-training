package com.exilesoft.exercise.company.type;

import java.util.ArrayList;
import java.util.List;

import com.exilesoft.exercise.infrastructure.AbstractInmemoryRepository;

public class InmemoryCompanyTypeRepository extends AbstractInmemoryRepository implements CompanyTypeRepository {

    private final List<CompanyType> types = new ArrayList<>();

    @Override
    public List<CompanyType> list() {
        return types;
    }

    @Override
    public void create(CompanyType object) {
        types.add(object);
        generateId(object);
    }

    @Override
    public CompanyType find(Long id) {
        for (CompanyType companyType : types) {
            if (companyType.getId().equals(id)) return clone(companyType);
        }
        return null;
    }

}
