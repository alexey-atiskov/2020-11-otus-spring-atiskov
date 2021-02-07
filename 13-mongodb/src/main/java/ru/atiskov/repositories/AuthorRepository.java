package ru.atiskov.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import ru.atiskov.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, String>, QuerydslPredicateExecutor<Author> {
    Author findByFirstName(String pushkin);
}
