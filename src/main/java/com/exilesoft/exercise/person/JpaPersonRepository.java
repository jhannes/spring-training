package com.exilesoft.exercise.person;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.exilesoft.exercise.infrastructure.AbstractJpaRepository;

@Repository
public class JpaPersonRepository extends AbstractJpaRepository<Person> implements
        PersonRepository {

    public JpaPersonRepository(EntityManager entityManager) {
        super(Person.class);
        this.entityManager = entityManager;
    }

    public JpaPersonRepository() {
        super(Person.class);
    }

}
