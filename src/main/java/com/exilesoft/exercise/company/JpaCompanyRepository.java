package com.exilesoft.exercise.company;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.exilesoft.exercise.infrastructure.AbstractJpaRepository;

@Repository
public class JpaCompanyRepository extends AbstractJpaRepository<Company> implements CompanyRepository {

    public JpaCompanyRepository(EntityManager entityManager) {
        super(Company.class);
        this.entityManager = entityManager;
    }

    public JpaCompanyRepository() {
        super(Company.class);
    }

    @Override
    public List<Company> findByNameLike(String nameQuery) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Company> query = cb.createQuery(Company.class);
        Root<Company> from = query.from(Company.class);
        query.where(cb.like(cb.lower(from.<String>get("companyName")), "%" + nameQuery.toLowerCase() + "%"));
        return getEntityManager().createQuery(query.select(from)).getResultList();
    }

    @Override
    public void update(Company company) {
        entityManager.merge(company);
    }
}
