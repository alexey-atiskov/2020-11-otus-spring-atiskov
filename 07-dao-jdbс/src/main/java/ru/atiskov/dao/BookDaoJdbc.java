package ru.atiskov.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import ru.atiskov.domain.Book;
import ru.atiskov.domain.BookInfo;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int count() {
        return namedParameterJdbcOperations.queryForObject("select count(*) from books", Collections.emptyMap(), Integer.class);
    }

    @Override
    public void insert(Book book) {
        namedParameterJdbcOperations.update(
                "insert into books (`name`, `id_author`, `id_genre`) " +
                        "values (:name, :idAuthor, :idGenre)",
                Map.of("id", book.getBookId(),
                        "name", book.getName(),
                        "idAuthor",  book.getIdAuthor(),
                        "idGenre", book.getIdGenre()));
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("book_id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select book_id, name, id_author, id_genre from books where book_id = :book_id", params, new BookMapper()
        );
    }

    @Override
    public BookInfo getBookInfoById(long id) {
        Map<String, Object> params = Collections.singletonMap("book_id", id);
        return namedParameterJdbcOperations.queryForObject(
                "SELECT b.name, a.firstname, a.secondaryname, g.name " +
                        "FROM books AS b " +
                        "INNER JOIN authors AS a " +
                        "INNER JOIN genres AS g " +
                        "ON b.id_author = a.auth_id " +
                        "AND b.id_genre = g.gen_id " +
                        "WHERE b.book_id = :book_id"
                , params, new BookInfoMapper()
        );
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query("select book_id, name, id_author, id_genre from books", new BookMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("book_id", id);
        namedParameterJdbcOperations.update(
                "delete from books where book_id = :book_id", params
        );
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("book_id");
            String name = resultSet.getString("name");
            long idAuthor = resultSet.getLong("id_author");
            long idGenre = resultSet.getLong("id_genre");
            Book book = new Book(idAuthor, idGenre, name);
            book.setBookId(id);
            return book;
        }
    }

    private static class BookInfoMapper implements RowMapper<BookInfo> {

        @Override
        public BookInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            String name = resultSet.getString("name");
            String firstname = resultSet.getString("firstname");
            String secondaryname = resultSet.getString("secondaryname");
//            String genre = resultSet.getString("g.name");
            BookInfo bookInfo = new BookInfo(name, firstname, secondaryname);
            return bookInfo;
        }
    }
}
