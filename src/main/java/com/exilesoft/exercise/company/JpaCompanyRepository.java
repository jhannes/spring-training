package com.exilesoft.exercise.company;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

@Repository
public class JpaCompanyRepository extends AbstractJpaRepository<Company> implements CompanyRepository {

	public JpaCompanyRepository(EntityManager entityManager) {
		super(entityManager, Company.class);
	}

	public JpaCompanyRepository() {
		super(Company.class);
	}

}
