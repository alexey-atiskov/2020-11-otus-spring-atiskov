package ru.atiskov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.atiskov.models.Author;

public interface AuthorRepositoryJpa extends JpaRepository<Author, Long> {
    Author findByFirstName(String pushkin);
}
