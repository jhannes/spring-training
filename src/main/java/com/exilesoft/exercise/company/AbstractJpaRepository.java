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
	    CriteriaQuery<T> query = entityManager.getCriteriaBuilder().createQuery(entityType);
	    return entityManager.createQuery(query.select(query.from(entityType))).getResultList();
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void create(T object) {
	    entityManager.persist(object);
	    entityManager.flush();
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public T find(Long id) {
	    return entityManager.find(entityType, id);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void update(T object) {
	    entityManager.merge(object);
	    entityManager.flush();
	}

}