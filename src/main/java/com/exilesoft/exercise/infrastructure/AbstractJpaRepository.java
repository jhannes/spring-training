package com.exilesoft.exercise.infrastructure;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class AbstractJpaRepository<T> {

    @PersistenceContext
    protected EntityManager entityManager;
    private final Class<T> entityType;

    public AbstractJpaRepository(Class<T> entityType) {
        this.entityType = entityType;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<T> list() {
        CriteriaQuery<T> query = getEntityManager().getCriteriaBuilder().createQuery(entityType);
        return getEntityManager().createQuery(query.select(query.from(entityType))).getResultList();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void create(T object) {
        getEntityManager().persist(object);
        getEntityManager().flush();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public T find(Long id) {
        return getEntityManager().find(entityType, id);
    }

    protected EntityManager getEntityManager() {
    	return entityManager;
    }

}