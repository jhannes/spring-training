package com.exilesoft.exercise.company.type;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation=Propagation.REQUIRED)
public class JpaCompanyTypeRepository implements CompanyTypeRepository {

    @PersistenceContext
	private EntityManager entityManager;

	public JpaCompanyTypeRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
    }

    public JpaCompanyTypeRepository() {
    }

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CompanyType> list() {
	    CriteriaQuery<CompanyType> query = getEntityManager().getCriteriaBuilder().createQuery(CompanyType.class);
	    return getEntityManager().createQuery(query.select(query.from(CompanyType.class))).getResultList();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(CompanyType object) {
	    getEntityManager().persist(object);
	    getEntityManager().flush();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public CompanyType find(Long id) {
	    return getEntityManager().find(CompanyType.class, id);
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}
}
