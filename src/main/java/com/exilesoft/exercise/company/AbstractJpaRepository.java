package com.exilesoft.exercise.company;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class AbstractJpaRepository<T> {

	@PersistenceContext
	private EntityManager entityManager;
	private final Class<T> entityType;

	public AbstractJpaRepository(EntityManager entityManager, Class<T> entityType) {
		this.entityManager = entityManager;
		this.entityType = entityType;
	}

	public AbstractJpaRepository(Class<T> entityType) {
		this.entityType = entityType;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public List<T> list() {
	    CriteriaQuery<T> query = getEntityManager().getCriteriaBuilder().createQuery(getEntityType());
	    return getEntityManager().createQuery(query.select(query.from(getEntityType()))).getResultList();
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void create(T object) {
	    getEntityManager().persist(object);
	    getEntityManager().flush();
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public T find(Long id) {
	    return getEntityManager().find(getEntityType(), id);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void update(T object) {
	    getEntityManager().merge(object);
	    getEntityManager().flush();
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	protected Class<T> getEntityType() {
		return entityType;
	}

}