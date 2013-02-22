package com.exilesoft.exercise.company.type;

import javax.persistence.EntityManager;

import com.exilesoft.exercise.infrastructure.EntityManagerFactories;
import com.exilesoft.exercise.infrastructure.TransactionProxy;

public class JpaCompanyTypeRepositoryTest extends AbstractCompanyTypeRepositoryTest {

    @Override
    protected CompanyTypeRepository getRepository() {
        final EntityManager entityManager = EntityManagerFactories.createEntityManager();
        return TransactionProxy.createProxy(entityManager,
                new JpaCompanyTypeRepository(entityManager),
                CompanyTypeRepository.class);
    }
}
