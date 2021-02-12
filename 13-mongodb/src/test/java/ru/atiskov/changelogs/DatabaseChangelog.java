package ru.atiskov.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import ru.atiskov.domain.Author;
import ru.atiskov.domain.Book;
import ru.atiskov.domain.Comment;
import ru.atiskov.domain.Genre;
import ru.atiskov.repositories.AuthorRepository;
import ru.atiskov.repositories.BookRepository;
import ru.atiskov.repositories.CommentRepository;
import ru.atiskov.repositories.GenreRepository;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "atiskov", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertRepin", author = "atiskov2")
    public void insertRepin(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("authors");
        var doc = new Document().append("firstName", "Repin");
        myCollection.insertOne(doc);
    }

    @ChangeSet(order = "003", id = "insertTolkien", author = "atiskov")
    public void insertTolkien(AuthorRepository repository) {
        repository.save(new Author("Tolkien"));
    }

    @ChangeSet(order = "004", id = "insertBook", author = "atiskov")
    public void insertTolkien(GenreRepository genreRepository,
                              BookRepository bookRepository,
                              AuthorRepository authorRepository,
                              CommentRepository commentRepository) {
        Author author1 = new Author("Pushkin");
        authorRepository.save(author1);
        Genre genre = new Genre("poem");
        genreRepository.save(genre);
        Comment comment = new Comment("com1");
        commentRepository.save(comment);
        bookRepository.save(new Book(author1, genre, comment, "Borodino"));
    }
}

