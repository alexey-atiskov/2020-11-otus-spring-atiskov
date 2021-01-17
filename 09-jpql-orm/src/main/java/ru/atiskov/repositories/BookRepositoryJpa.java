package ru.atiskov.repositories;

import java.util.List;
import java.util.Optional;

import ru.atiskov.models.Book;

public interface BookRepositoryJpa {
    Book save(Book student);
    Optional<Book> findById(long id);

    List<Book> findAll();
    List<Book> findByName(String name);

    void deleteById(long id);
    void remove(Book book);

    List<Book> getBookInfos();
}
