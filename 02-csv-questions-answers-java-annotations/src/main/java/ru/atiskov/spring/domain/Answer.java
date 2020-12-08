package ru.atiskov.spring.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Answer {
    private final String value;
}
