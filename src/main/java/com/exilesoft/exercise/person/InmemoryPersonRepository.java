package com.exilesoft.exercise.person;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class InmemoryPersonRepository implements PersonRepository {

    private static List<Person> people = new ArrayList<>();

    @Override
    public List<Person> list() {
        return people;
    }

    @Override
    public void create(Person object) {
        people.add(object);
    }

}
