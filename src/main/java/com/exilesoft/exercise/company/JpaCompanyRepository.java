package com.exilesoft.exercise.company;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository
public class JpaCompanyRepository extends AbstractJpaRepository<Company> implements CompanyRepository {

	public JpaCompanyRepository(EntityManager entityManager) {
		super(entityManager, Company.class);
	}

	public JpaCompanyRepository() {
		super(Company.class);
	}

	@Override
	public List<Company> findByName(String nameQuery) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Company> query = builder.createQuery(getEntityType());

		Root<Company> from = query.from(getEntityType());
		query.select(from);
		query.where(builder.like(builder.lower(getCompanyNameField(from)), "%" + nameQuery.toLowerCase() + "%"));

		return getEntityManager().createQuery(query).getResultList();
	}

	private Path<String> getCompanyNameField(Root<Company> from) {
		return from.<String>get("companyName");
	}

}
