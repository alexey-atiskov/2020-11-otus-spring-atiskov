package ru.atiskov.repositories;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import ru.atiskov.domain.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAll();

    Book findByAuthors_firstName(String name);

    Book findByName(String name);

    Book save(Book entity);
}
