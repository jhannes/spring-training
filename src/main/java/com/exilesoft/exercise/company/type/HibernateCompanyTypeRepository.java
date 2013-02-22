package com.exilesoft.exercise.company.type;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED)
@SuppressWarnings("unchecked")
public class HibernateCompanyTypeRepository implements CompanyTypeRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateCompanyTypeRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<CompanyType> list() {
        return getSession().createCriteria(CompanyType.class).list();
    }

    @Override
    public void create(CompanyType object) {
        getSession().save(object);
    }

    @Override
    public void update(CompanyType object) {
    	getSession().save(object);
    }

    @Override
    public CompanyType find(Long id) {
        return (CompanyType) getSession().get(CompanyType.class, id);
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}
