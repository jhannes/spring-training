package com.exilesoft.exercise.person;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.exilesoft.exercise.company.AbstractJpaRepository;

@Repository
public class JpaPersonRepository extends AbstractJpaRepository<Person> implements PersonRepository {

	public JpaPersonRepository(EntityManager entityManager) {
		super(entityManager, Person.class);
	}

	public JpaPersonRepository() {
		super(Person.class);
	}

}
