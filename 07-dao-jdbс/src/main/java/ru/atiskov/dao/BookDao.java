package ru.atiskov.dao;

import java.util.List;

import ru.atiskov.domain.Book;
import ru.atiskov.domain.BookInfo;

public interface BookDao {
    int count();

    void insert(Book book);

    Book getById(long id);

    BookInfo getBookInfoById(long id);

    List<Book> getAll();

    void deleteById(long id);
}
