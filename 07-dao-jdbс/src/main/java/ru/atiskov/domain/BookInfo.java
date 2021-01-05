package ru.atiskov.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class BookInfo {
    private final String name;
    private final String firstName;
    private final String secondaryName;

    private String genreName;
}
