package ru.atiskov;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

import ru.atiskov.domain.Author;
import ru.atiskov.domain.Book;
import ru.atiskov.domain.Comment;
import ru.atiskov.domain.Genre;
import ru.atiskov.repositories.AuthorRepository;
import ru.atiskov.repositories.BookRepository;
import ru.atiskov.repositories.CommentRepository;
import ru.atiskov.repositories.GenreRepository;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(Main.class);
    }

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CommentRepository commentRepository;

    @PostConstruct
    public void init() throws InterruptedException {
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

        Book borodino = bookRepository.findByName("Borodino");
        System.out.println(borodino);
        System.out.println("--------");

//        Book bookPushkin = bookRepository.findByAuthors_firstName("Pushkin");
//        System.out.println(bookPushkin);
//        System.out.println("--------");
//        System.out.println(bookRepository.findById(Long.valueOf(borodino.getBookId())));
//        System.out.println("--------");


        System.out.println("\n\n\n----------------------------------------------\n\n");
        System.out.println("Авторы в БД:");
        authorRepository.findAll().forEach(p -> System.out.println(p.getFirstName()));
        System.out.println("\n\n----------------------------------------------\n\n\n");
    }
}
