package ru.atiskov.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Book {
    // primary
    private final long id;
    // foreign
    private final long idAuthor;
    private final long idGenre;
    // other
    private final String name;
}
