package ru.atiskov.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import ru.atiskov.domain.Author;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int count() {
        return namedParameterJdbcOperations.queryForObject("select count(*) from authors", Collections.emptyMap(), Integer.class);
    }

    @Override
    public void insert(Author author) {
        namedParameterJdbcOperations.update(
                "insert into authors (`firstname`, `lastname`, `secondaryname`) " +
                        "values (:firstname, :lastname, :secondaryname)",
                Map.of("id", author.getAuthId(),
                        "firstname", author.getFirstName(),
                        "lastname",  author.getLastName(),
                        "secondaryname", author.getSecondaryName()));
    }

    @Override
    public Author getById(long id) {
        Map<String, Object> params = Collections.singletonMap("auth_id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select auth_id, firstname, lastname, secondaryname from authors where auth_id = :auth_id", params, new AuthorMapper()
        );
    }

    @Override
    public List<Author> getAll() {
        return namedParameterJdbcOperations.query("select auth_id, firstname, lastname, secondaryname from authors", new AuthorMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("auth_id", id);
        namedParameterJdbcOperations.update(
                "delete from authors where auth_id = :auth_id", params
        );
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("auth_id");
            String firstName = resultSet.getString("firstname");
            String lastName = resultSet.getString("lastname");
            String secondaryName = resultSet.getString("secondaryname");
            Author author = new Author(firstName, lastName, secondaryName);
            author.setAuthId(id);
            return author;
        }
    }
}
