package ru.atiskov.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import ru.atiskov.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {
    Genre findByName(String poem);
}
