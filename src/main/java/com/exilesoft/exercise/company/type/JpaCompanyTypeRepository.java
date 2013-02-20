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

    public JpaCompanyTypeRepository() {
    }

    public JpaCompanyTypeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<CompanyType> list() {
        CriteriaQuery<CompanyType> query = entityManager.getCriteriaBuilder().createQuery(CompanyType.class);
        return entityManager.createQuery(query.select(query.from(CompanyType.class))).getResultList();
    }

    @Override
    public void create(CompanyType object) {
        entityManager.persist(object);
        entityManager.flush();
    }

    @Override
    public CompanyType find(Long id) {
        return entityManager.find(CompanyType.class, id);
    }

}
