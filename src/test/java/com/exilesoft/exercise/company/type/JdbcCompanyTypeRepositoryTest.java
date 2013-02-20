package com.exilesoft.exercise.company.type;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.BeforeClass;

public class JdbcCompanyTypeRepositoryTest extends AbstractCompanyTypeRepositoryTest {


    private static DataSource dataSource;
    private final CompanyTypeRepository repository = new JdbcCompanyTypeRepository(dataSource);

    @Override
    protected CompanyTypeRepository getRepository() {
        return repository;
    }

    @BeforeClass
    public static void createDataSource() throws SQLException {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:jdbc;DB_CLOSE_DELAY=-1");
        dataSource.setUser("sa");

        try (Connection connection = dataSource.getConnection()) {
            try(Statement statement = connection.createStatement()) {
                JdbcCompanyTypeRepository.createTable(statement);
            }
        }

        JdbcCompanyTypeRepositoryTest.dataSource = dataSource;
    }

}
