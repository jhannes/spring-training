package com.exilesoft.exercise.person;

import javax.persistence.EntityManager;

import com.exilesoft.exercise.company.CompanyRepository;
import com.exilesoft.exercise.company.JpaCompanyRepository;
import com.exilesoft.exercise.infrastructure.EntityManagerFactories;
import com.exilesoft.exercise.infrastructure.TransactionProxy;

public class JpaPersonRepositoryTest extends AbstractPersonRepositoryTest {

    @Override
    protected PersonRepository getRepository() {
        final EntityManager entityManager = EntityManagerFactories.createEntityManager();
        return TransactionProxy.createProxy(entityManager,
                new JpaPersonRepository(entityManager),
                PersonRepository.class);
    }

    @Override
    protected CompanyRepository getCompanyRepository() {
        final EntityManager entityManager = EntityManagerFactories.createEntityManager();
        return TransactionProxy.createProxy(entityManager,
                new JpaCompanyRepository(entityManager),
                CompanyRepository.class);
    }
}
