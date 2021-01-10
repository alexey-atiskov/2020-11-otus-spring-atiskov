package ru.atiskov.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import ru.atiskov.domain.Comment;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class CommentDaoJdbc implements CommentDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public CommentDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int count() {
        return namedParameterJdbcOperations.queryForObject("select count(*) from comments", Collections.emptyMap(), Integer.class);
    }

    @Override
    public void insert(Comment comment) {
        namedParameterJdbcOperations.update(
                "insert into comments (`value`) " +
                        "values (:value)",
                Map.of("value", comment.getValue()));
    }

    @Override
    public Comment getById(long id) {
        Map<String, Object> params = Collections.singletonMap("comment_id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select comment_id, value from comments where comment_id = :comment_id", params, new CommentMapper()
        );
    }

    @Override
    public List<Comment> getAll() {
        return namedParameterJdbcOperations.query("select comment_id, value from comments", new CommentMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("auth_id", id);
        namedParameterJdbcOperations.update(
                "delete from comments where auth_id = :auth_id", params
        );
    }

    @Override
    public void addCommentToBookMappingById(long id_book, long id_comment) {
        namedParameterJdbcOperations.update(
                "insert into comments_info (`id_book`, `id_comment`) " +
                        "values (:id_book, :id_comment)",
                Map.of("id_book", id_book,
                        "id_comment", id_comment));
    }

    @Override
    public List<Comment> getCommentsToBookById(long id_book) {
        Map<String, Object> params = Collections.singletonMap("book_id", id_book);
        return namedParameterJdbcOperations.query(
                "SELECT c.comment_id, c.value " +
                        "FROM comments AS c " +
                        "INNER JOIN books AS b " +
                        "INNER JOIN comments_info AS ci " +
                        "ON b.book_id = ci.id_book " +
                        "AND c.comment_id = ci.id_comment " +
                        "WHERE b.book_id = :book_id"
                , params, new CommentDaoJdbc.CommentMapper()
        );
    }

    private static class CommentMapper implements RowMapper<Comment> {

        @Override
        public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("comment_id");
            String value = resultSet.getString("value");
            Comment comment = new Comment(value);
            comment.setCommentId(id);
            return comment;
        }
    }
}
