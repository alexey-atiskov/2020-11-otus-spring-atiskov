package ru.atiskov;

import org.h2.tools.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

import ru.atiskov.models.Author;
import ru.atiskov.models.Book;
import ru.atiskov.models.Comment;
import ru.atiskov.models.Genre;
import ru.atiskov.repositories.AuthorRepositoryJpa;
import ru.atiskov.repositories.BookRepositoryJpa;
import ru.atiskov.repositories.CommentRepositoryJpa;
import ru.atiskov.repositories.GenreRepositoryJpa;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(Main.class);
//        Console.main(args);
    }

    @Autowired
    private GenreRepositoryJpa genreRepository;
    @Autowired
    private BookRepositoryJpa bookRepository;
    @Autowired
    private AuthorRepositoryJpa authorRepository;
    @Autowired
    private CommentRepositoryJpa commentRepository;

    @PostConstruct
    @Transactional
    public void init() {
        Author author1 = new Author("Pushkin");
        authorRepository.save(author1);
        Author author2 = new Author("Lermontov");
        authorRepository.save(author2);
        Genre genre = new Genre("poem");
        genreRepository.save(genre);
        Comment comment = new Comment("com1");
        commentRepository.save(comment);

        bookRepository.save(new Book(author1, genre, "Onegin"));
        bookRepository.save(new Book(author2, genre, comment, "Borodino"));

        System.out.println("--------");
        System.out.println(authorRepository.findByFirstName("Pushkin"));
        System.out.println("--------");
        System.out.println(genreRepository.findAll());
        System.out.println("--------");
        System.out.println(genreRepository.findByName("poem"));
        System.out.println("--------");

        System.out.println(bookRepository.findByName("Borodino"));
        System.out.println("--------");

        Book bookPushkin = bookRepository.findByAuthors_firstName("Pushkin");
        System.out.println(bookPushkin);
        System.out.println("--------");
        System.out.println(bookRepository.findById(bookPushkin.getBookId()));
        System.out.println("--------");
    }
}
