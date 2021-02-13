package ru.atiskov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.atiskov.models.Genre;

public interface GenreRepositoryJpa extends JpaRepository<Genre, Long> {
    Genre findByName(String poem);
}
