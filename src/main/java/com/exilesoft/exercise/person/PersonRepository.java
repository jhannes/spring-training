package com.exilesoft.exercise.person;

import java.util.List;


public interface PersonRepository {

	List<Person> list();

	void create(Person person);

    Person find(Long id);

}
