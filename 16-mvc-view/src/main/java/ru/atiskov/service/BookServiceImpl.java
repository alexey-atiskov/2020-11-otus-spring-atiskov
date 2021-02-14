package ru.atiskov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ru.atiskov.models.Book;
import ru.atiskov.repositories.BookRepositoryJpa;

@Service
public class BookServiceImpl implements BookService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private BookRepositoryJpa bookRepositoryJpa;

    @Transactional
    public void updateNameById(long id, String name) {
        Optional<Book> bookOptional = bookRepositoryJpa.findById(id);
        bookOptional.ifPresent(book -> book.setName(name));
    }
}
