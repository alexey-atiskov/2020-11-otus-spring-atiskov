package ru.atiskov.repositories;

import org.springframework.data.repository.CrudRepository;

import ru.atiskov.domain.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    Author findByFirstName(String pushkin);
}
