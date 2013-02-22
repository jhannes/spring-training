package com.exilesoft.exercise.person;

import org.springframework.stereotype.Repository;

import com.exilesoft.exercise.infrastructure.AbstractInmemoryRepository;

@Repository
public class InmemoryPersonRepository extends AbstractInmemoryRepository<Person> implements PersonRepository {

}
