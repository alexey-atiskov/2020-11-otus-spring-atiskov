package ru.atiskov.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Author {
    private final long id;
    private final String firstname;
    private final String lastname;
    private final String secondaryname;
}
