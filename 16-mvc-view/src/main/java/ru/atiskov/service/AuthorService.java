package ru.atiskov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import ru.atiskov.models.Author;
import ru.atiskov.repositories.AuthorRepositoryJpa;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepositoryJpa repository;

    public Author save(Author author) {
        return repository.save(author);
    }
    public Optional<Author> getById(long id) {
        return repository.findById(id);
    }

    public List<Author> getAll() {
        return repository.findAll();
    }
}
