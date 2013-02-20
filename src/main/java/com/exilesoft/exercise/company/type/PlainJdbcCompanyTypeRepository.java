package com.exilesoft.exercise.company.type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED)
public class PlainJdbcCompanyTypeRepository implements CompanyTypeRepository {


    private static final String CREATE_TABLE = "create table CompanyType (id bigint generated by default as identity, typeName varchar(255), primary key (id))";
    private static final String SELECT_ALL = "select * from CompanyType";
    private static final String INSERT_SQL = "insert into CompanyType (id, typeName) values (null, ?)";
    private static final String SELECT_BY_ID = "select * from CompanyType where id = ?";
    private final DataSource dataSource;

    public PlainJdbcCompanyTypeRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<CompanyType> list() {
        try (Connection connection = getConnection()) {
            try (Statement stmt = connection.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(SELECT_ALL)) {
                    List<CompanyType> result = new ArrayList<>();
                    while (rs.next()) {
                        CompanyType companyType = new CompanyType(rs.getString("typeName"));
                        companyType.setId(rs.getLong("id"));
                        result.add(companyType);
                    }
                    return result;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(CompanyType object) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement stmt = connection.prepareCall(INSERT_SQL)) {
                stmt.setString(1, object.getTypeName());
                stmt.execute();
            }
            try (Statement stmt = connection.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("call identity()")) {
                   if (rs.next()) object.setId(rs.getLong(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CompanyType find(Long id) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID)) {
                stmt.setLong(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        CompanyType companyType = new CompanyType(rs.getString("typeName"));
                        companyType.setId(rs.getLong("id"));
                        return companyType;
                    } else {
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void createTable(Statement statement) throws SQLException {
        statement.executeUpdate(CREATE_TABLE);
    }
}