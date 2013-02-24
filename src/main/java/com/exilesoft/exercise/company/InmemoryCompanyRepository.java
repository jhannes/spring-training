package com.exilesoft.exercise.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.exilesoft.exercise.infrastructure.AbstractInmemoryRepository;

@Repository
public class InmemoryCompanyRepository extends AbstractInmemoryRepository implements CompanyRepository {

	private final Map<Long, Company> entities = new HashMap<>();

	@Override
	public void create(Company newObject) {
	    entities.put(generateId(newObject), newObject);
	}

	@Override
	public List<Company> list() {
	    return new ArrayList<>(entities.values());
	}

	@Override
	public Company find(Long id) {
	    return clone(entities.get(id));
	}

	@Override
    public List<Company> findByNameLike(String nameQuery) {
	    if (nameQuery == null) return list();
        List<Company> result = new ArrayList<>();
        for (Company company : list()) {
            if (company.getCompanyName().toLowerCase().contains(nameQuery.toLowerCase())) {
                result.add(company);
            }
        }
        return result;
    }

	@Override
	public void update(Company company) {
	    entities.put(company.getId(), company);
	}

}
