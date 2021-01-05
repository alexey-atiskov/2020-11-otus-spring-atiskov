package ru.atiskov.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Book {
    // primary
    private long bookId;
    // foreign
    private final long idAuthor;
    private final long idGenre;
    // other
    private final String name;

    private String author;
    private String genre;
}
