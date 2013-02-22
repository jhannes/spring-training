package com.exilesoft.exercise.infrastructure;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

public class EntityManagerFactories {

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

	private static EntityManagerFactory entityManagerFactory = createEntityManagerFactory();

	public static EntityManager createEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

}
