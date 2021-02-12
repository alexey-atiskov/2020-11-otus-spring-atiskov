package ru.atiskov.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import ru.atiskov.domain.Book;

public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findAll();

    Book findByAuthors_firstName(String name);

    Book findByName(String name);

    Book save(Book entity);
}
