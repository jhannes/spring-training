package com.exilesoft.exercise.company;

import javax.persistence.EntityManager;

import com.exilesoft.exercise.company.type.CompanyTypeRepository;
import com.exilesoft.exercise.company.type.JpaCompanyTypeRepository;
import com.exilesoft.exercise.infrastructure.EntityManagerFactories;
import com.exilesoft.exercise.infrastructure.TransactionProxy;



public class JpaCompanyRepositoryTest extends AbstractCompanyRepositoryTest {

    @Override
    protected CompanyRepository createRepository() {
    	EntityManager entityManager = EntityManagerFactories.createEntityManager();
        return TransactionProxy.createProxy(
        		entityManager, new JpaCompanyRepository(entityManager), CompanyRepository.class);
    }

    @Override
    protected CompanyTypeRepository createTypeRepository() {
    	EntityManager entityManager = EntityManagerFactories.createEntityManager();
        return TransactionProxy.createProxy(
        		entityManager, new JpaCompanyTypeRepository(entityManager), CompanyTypeRepository.class);
    }

}
