package com.exilesoft.exercise.company.type;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Transactional(propagation=Propagation.REQUIRED)
public class JdbcCompanyTypeRepository extends NamedParameterJdbcDaoSupport implements CompanyTypeRepository {

    private static final String CREATE_TABLE = "create table CompanyType (id bigint generated by default as identity, typeName varchar(255), primary key (id))";
    private static final String SELECT_ALL = "select * from CompanyType";
    private static final String SELECT_BY_ID = "select * from CompanyType where id = :id";

    private final SimpleJdbcInsert insertCommand;

    @Autowired
    public JdbcCompanyTypeRepository(DataSource dataSource) {
        setDataSource(dataSource);
        insertCommand = new SimpleJdbcInsert(getDataSource())
            .withTableName("CompanyType")
            .usingColumns("typeName")
            .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<CompanyType> list() {
        return getJdbcTemplate().query(SELECT_ALL, mapRowToEntity());
    }

    @Override
    public void create(CompanyType object) {
        Map<String, Object> args = new HashMap<>();
        args.put("typeName", object.getTypeName());
        Number id = insertCommand.executeAndReturnKey(args);
        object.setId(id.longValue());
    }

    @Override
    public CompanyType find(Long id) {
        SqlParameterSource paramMap = new MapSqlParameterSource()
            .addValue("id", id);
        return getNamedParameterJdbcTemplate().queryForObject(SELECT_BY_ID, paramMap, mapRowToEntity());
    }

    private RowMapper<CompanyType> mapRowToEntity() {
        return new RowMapper<CompanyType>() {
            @Override
            public CompanyType mapRow(ResultSet rs, int rowNum) throws SQLException {
                CompanyType companyType = new CompanyType(rs.getString("typeName"));
                companyType.setId(rs.getLong("id"));
                return companyType;
            }
        };
    }

    public static void createTable(Statement statement) throws SQLException {
        statement.execute(CREATE_TABLE);
    }

}
