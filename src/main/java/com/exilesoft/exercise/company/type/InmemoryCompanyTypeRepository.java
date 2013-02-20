package com.exilesoft.exercise.company.type;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.exilesoft.exercise.infrastructure.AbstractInmemoryRepository;

@Repository
public class InmemoryCompanyTypeRepository extends AbstractInmemoryRepository implements CompanyTypeRepository {

    private final List<CompanyType> types = new ArrayList<>();

    @Override
    public List<CompanyType> list() {
        return types;
    }

    @Override
    public void create(CompanyType object) {
        types.add(object);
    }

    @Override
    public CompanyType find(Long id) {
        for (CompanyType companyType : types) {
            if (companyType.getId().equals(id)) return clone(companyType);
        }
        return null;
    }

}
