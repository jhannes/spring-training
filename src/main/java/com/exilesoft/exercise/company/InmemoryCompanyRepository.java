package com.exilesoft.exercise.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.exilesoft.exercise.infrastructure.AbstractInmemoryRepository;

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

}
