package ru.atiskov.dao;

import java.util.List;

import ru.atiskov.domain.Comment;

public interface CommentDao {
    int count();

    void insert(Comment book);

    Comment getById(long id);

    List<Comment> getAll();

    void deleteById(long id);

    void addCommentToBookMappingById(long id_book, long id_comment);

    List<Comment> getCommentsToBookById(long id_book);
}
