package com.exilesoft.exercise.company.type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.exilesoft.exercise.infrastructure.AbstractInmemoryRepository;

public class InmemoryCompanyTypeRepository extends AbstractInmemoryRepository implements CompanyTypeRepository {

	private final Map<Long, CompanyType> entities = new HashMap<>();

	@Override
	public void create(CompanyType newObject) {
	    entities.put(generateId(newObject), newObject);
	}

	@Override
	public List<CompanyType> list() {
	    return new ArrayList<>(entities.values());
	}

	@Override
	public void update(CompanyType object) {
		entities.put(getId(object), object);
	}

	@Override
	public CompanyType find(Long id) {
	    return clone(entities.get(id));
	}

}
