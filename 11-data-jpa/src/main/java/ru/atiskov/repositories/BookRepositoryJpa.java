package ru.atiskov.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import ru.atiskov.models.Book;

public interface BookRepositoryJpa extends CrudRepository<Book, Long> {

    List<Book> findAll();

    @Query("select new ru.atiskov.models.Book(b.authors, b.genre, b.name) from Book b")
    List<Book> getBookInfos();

    Book findByAuthors_firstName(String name);

    Book findByName(String borodino);
}
