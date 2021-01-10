package ru.atiskov.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Author {
    private long authId;
    private final String firstName;
    private final String lastName;
    private final String secondaryName;
}
