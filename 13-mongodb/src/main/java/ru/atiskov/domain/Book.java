package ru.atiskov.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "books")
public class Book {
    @Id
    private String bookId;

    @DBRef//@ManyToMany(targetEntity = Author.class, fetch = FetchType.EAGER)
    private List<Author> authors;

    @DBRef//@OneToOne(targetEntity = Genre.class, fetch = FetchType.EAGER)
    private Genre genre;

    @DBRef//@OneToMany(targetEntity = Comment.class, fetch = FetchType.LAZY)
    private List<Comment> comments;

    private String name;

    public Book(Collection<Author> authors, Genre genre, String name) {
        this.authors = new ArrayList<>(authors);
        this.genre = genre;
        this.name = name;
    }

    public Book(Author author, Genre genre, String name) {
        this.authors = List.of(author);
        this.genre = genre;
        this.name = name;
    }

    public Book(Author author, Genre genre, Comment comment, String name) {
        this.authors = List.of(author);
        this.genre = genre;
        this.name = name;
        this.comments = List.of(comment);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", authors='" + authors + '\'' +
                '}';
    }
}
