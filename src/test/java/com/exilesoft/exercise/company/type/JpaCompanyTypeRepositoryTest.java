package com.exilesoft.exercise.company.type;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

public class JpaCompanyTypeRepositoryTest extends AbstractCompanyTypeRepositoryTest {

    private static EntityManagerFactory entityManagerFactory = createEntityManagerFactory();

    private static EntityManagerFactory createEntityManagerFactory() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        dataSource.setUser("sa");

        DefaultPersistenceUnitManager persistenceUnitManager = new DefaultPersistenceUnitManager();
        persistenceUnitManager.setPersistenceXmlLocation("test-persistence.xml");
        persistenceUnitManager.setDefaultDataSource(dataSource);
        persistenceUnitManager.afterPropertiesSet();

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setPersistenceUnitManager(persistenceUnitManager);
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactory.afterPropertiesSet();
        return entityManagerFactory.getObject();
    }

    @Override
    protected CompanyTypeRepository getRepository() {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        final JpaCompanyTypeRepository repo = new JpaCompanyTypeRepository(entityManager);

        return (CompanyTypeRepository) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[] { CompanyTypeRepository.class }, new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                entityManager.getTransaction().begin();
                boolean commit = false;
                try {
                    Object result = method.invoke(repo, args);
                    commit = true;
                    return result;
                } finally {
                    if (commit)
                        entityManager.getTransaction().commit();
                    else
                        entityManager.getTransaction().rollback();
                }
            }
        });

    }
}
