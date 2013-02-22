package com.exilesoft.exercise.person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.exilesoft.exercise.infrastructure.AbstractInmemoryRepository;

public class InmemoryPersonRepository extends AbstractInmemoryRepository implements PersonRepository {

	private final Map<Long, Person> entities = new HashMap<>();

	@Override
	public void create(Person newObject) {
	    entities.put(generateId(newObject), newObject);
	}

	@Override
	public List<Person> list() {
	    return new ArrayList<>(entities.values());
	}

}
