package com.exilesoft.exercise.person;




public class InmemoryPersonRepositoryTest extends AbstractPersonRepositoryTest {

    private final InmemoryPersonRepository repository = new InmemoryPersonRepository();

    @Override
    protected PersonRepository getRepository() {
        return repository;
    }

}
