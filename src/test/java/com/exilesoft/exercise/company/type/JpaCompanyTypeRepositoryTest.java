package com.exilesoft.exercise.company.type;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import com.exilesoft.exercise.infrastructure.TransactionProxy;

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
        return TransactionProxy.createProxy(entityManager,
                new JpaCompanyTypeRepository(entityManager),
                CompanyTypeRepository.class);
    }
}
