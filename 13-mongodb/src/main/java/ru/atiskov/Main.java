package ru.atiskov;

import com.github.cloudyrock.spring.v5.EnableMongock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

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
@EnableMongock
@EnableMongoRepositories(basePackages = "ru.atiskov.repositories")
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
    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void init() {
        mongoTemplate.getDb().drop();

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
        System.out.println("Find by firstName" + authorRepository.findByFirstName("Pushkin"));
        System.out.println("--------");
        System.out.println("Find by template = " + mongoTemplate.findOne(Query.query(Criteria.where("firstName").is("Pushkin")), Author.class));
        System.out.println("--------");
        System.out.println("Find by repository = " + authorRepository.findOne(Example.of(author1)));
        System.out.println("--------");
        System.out.println("All genres:" + genreRepository.findAll());
        System.out.println("--------");
        System.out.println("All comments:" + commentRepository.findAll());
        System.out.println("--------");
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.ASC, "name"));
        System.out.println("All genres by template:" + mongoTemplate.find(query, Genre.class));
        System.out.println("--------");
        System.out.println("Poem genre:" + genreRepository.findByName("poem"));
        System.out.println("--------");

        Book borodino = bookRepository.findByName("Borodino");
        System.out.println("Borodino book:" + borodino);
        System.out.println("--------");

        System.out.println("Borodino book by id:" + bookRepository.findById(borodino.getBookId()));
        System.out.println("--------");


        System.out.println("\n\n\n----------------------------------------------\n\n");
        System.out.println("Авторы в БД:");
        authorRepository.findAll().forEach(p -> System.out.println(p.getFirstName()));
        System.out.println("\n\n----------------------------------------------\n\n\n");
    }
}
