package ru.atiskov.repositories;

import org.springframework.data.repository.CrudRepository;

import ru.atiskov.domain.Genre;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    Genre findByName(String poem);
}
