package ru.atiskov.models;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "books")
@NamedEntityGraph(name = "book-comments-entity-graph",
        attributeNodes = {@NamedAttributeNode("comments")})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long bookId;

//    @Fetch(FetchMode.SUBSELECT)
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 2)
    @ManyToMany(targetEntity = Author.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "book_authors", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "auth_id"))
    private List<Author> authors;

    @OneToOne(targetEntity = Genre.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_genre")
    private Genre genre;

    @OneToMany(targetEntity = Comment.class, fetch = FetchType.LAZY)
    @JoinTable(name = "comments_info", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    private List<Comment> comments;

    @Column(name = "name", nullable = false)
    private String name;

    // used in getBookInfoById
    public Book(List<Author> authors, Genre genre, String name) {
        this.authors = authors;
        this.genre = genre;
        this.name = name;
    }
}
