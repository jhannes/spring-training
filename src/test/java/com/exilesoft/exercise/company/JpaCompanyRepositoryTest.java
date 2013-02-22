package com.exilesoft.exercise.company;

import javax.persistence.EntityManager;

import com.exilesoft.exercise.infrastructure.EntityManagerFactories;
import com.exilesoft.exercise.infrastructure.TransactionProxy;



public class JpaCompanyRepositoryTest extends AbstractCompanyRepositoryTest {

    @Override
    protected CompanyRepository createRepository() {
    	EntityManager entityManager = EntityManagerFactories.createEntityManager();
        return TransactionProxy.createProxy(
        		entityManager, new JpaCompanyRepository(entityManager), CompanyRepository.class);
    }

}
