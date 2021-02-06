package ru.atiskov.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.util.Optional;

import ru.atiskov.domain.Book;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private BookRepository bookRepository;

    public void updateNameById(long id, String name) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        bookOptional.ifPresent(book -> book.setName(name));
    }
}
