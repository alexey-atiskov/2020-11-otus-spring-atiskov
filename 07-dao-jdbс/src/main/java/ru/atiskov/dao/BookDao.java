package ru.atiskov.dao;

import java.util.List;

import ru.atiskov.domain.Book;

public interface BookDao {
    int count();

    void insert(Book book);

    Book getById(long id);

    List<Book> getAll();

    void deleteById(long id);
}
