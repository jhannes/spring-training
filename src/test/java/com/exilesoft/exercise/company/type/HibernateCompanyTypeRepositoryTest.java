package com.exilesoft.exercise.company.type;

import java.io.IOException;
import java.util.Properties;

import org.h2.jdbcx.JdbcDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import com.exilesoft.exercise.company.Company;
import com.exilesoft.exercise.infrastructure.TransactionProxy;
import com.exilesoft.exercise.person.Person;

public class HibernateCompanyTypeRepositoryTest extends AbstractCompanyTypeRepositoryTest {

    private static SessionFactory sessionFactory = createSessionFactory();
    private final CompanyTypeRepository repository = new HibernateCompanyTypeRepository(sessionFactory);

    @Override
    protected CompanyTypeRepository getRepository() {
        return TransactionProxy.createProxy(sessionFactory, repository, CompanyTypeRepository.class);
    }

    private static SessionFactory createSessionFactory() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:integration;DB_CLOSE_DELAY=-1");
        dataSource.setUser("sa");

        Properties properties = new Properties();
        properties.setProperty(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.setProperty(AvailableSettings.HBM2DDL_AUTO, "create");

        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setHibernateProperties(properties);
        sessionFactoryBean.setAnnotatedClasses(new Class<?>[] {
                CompanyType.class, Company.class, Person.class
        });
        try {
            sessionFactoryBean.afterPropertiesSet();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sessionFactoryBean.getObject();
    }
}
