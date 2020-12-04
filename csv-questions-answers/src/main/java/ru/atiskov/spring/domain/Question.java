package ru.atiskov.spring.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Question {
    private final String value;
    private final Answer correctAnswer;
}
