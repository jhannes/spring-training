package com.exilesoft.exercise.person;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.exilesoft.exercise.infrastructure.AbstractInmemoryRepository;

@Repository
public class InmemoryPersonRepository extends AbstractInmemoryRepository implements PersonRepository {

    private final List<Person> people = new ArrayList<>();

    @Override
    public List<Person> list() {
        return people;
    }

    @Override
    public Person find(Long id) {
        for (Person person : people) {
            if (person.getId().equals(id)) return clone(person);
        }
    	return null;
    }

    @Override
    public void create(Person object) {
        people.add(object);
    }

}
