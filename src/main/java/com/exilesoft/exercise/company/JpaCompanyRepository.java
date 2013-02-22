package com.exilesoft.exercise.company;

import javax.persistence.EntityManager;

public class JpaCompanyRepository extends AbstractJpaRepository<Company> implements CompanyRepository {

	public JpaCompanyRepository(EntityManager entityManager) {
		super(entityManager, Company.class);
	}

	public JpaCompanyRepository() {
		super(Company.class);
	}

}
