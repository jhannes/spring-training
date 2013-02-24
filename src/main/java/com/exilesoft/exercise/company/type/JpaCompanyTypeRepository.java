package com.exilesoft.exercise.company.type;


import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.exilesoft.exercise.infrastructure.AbstractJpaRepository;

@Repository
@Transactional(propagation=Propagation.REQUIRED)
public class JpaCompanyTypeRepository extends AbstractJpaRepository<CompanyType> implements CompanyTypeRepository {

    public JpaCompanyTypeRepository(EntityManager entityManager) {
        super(CompanyType.class);
		this.entityManager = entityManager;
    }

    public JpaCompanyTypeRepository() {
        super(CompanyType.class);
    }
}
