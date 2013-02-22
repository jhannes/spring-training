package com.exilesoft.exercise.person;

import javax.persistence.EntityManager;

import com.exilesoft.exercise.infrastructure.EntityManagerFactories;
import com.exilesoft.exercise.infrastructure.TransactionProxy;



public abstract class JpaPersonRepositoryTest extends AbstractPersonRepositoryTest {

    //@Override
    protected PersonRepository createRepositoryss() {
    	EntityManager entityManager = EntityManagerFactories.createEntityManager();
        return TransactionProxy.createProxy(
        		entityManager, new JpaPersonRepository(entityManager), PersonRepository.class);
    }

}
