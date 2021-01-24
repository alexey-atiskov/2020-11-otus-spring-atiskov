package ru.atiskov.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Book {
    @Id
    @GeneratedValue
    private long bookId;

    @ManyToMany(targetEntity = Author.class, fetch = FetchType.EAGER)
    private List<Author> authors;

    @OneToOne(targetEntity = Genre.class, fetch = FetchType.EAGER)
    private Genre genre;

    @OneToMany(targetEntity = Comment.class, fetch = FetchType.LAZY)
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
                ", comments='" + comments + '\'' + // TODO comment this line to work (failed to lazily initialize a collection of role)
                '}';
    }
}
