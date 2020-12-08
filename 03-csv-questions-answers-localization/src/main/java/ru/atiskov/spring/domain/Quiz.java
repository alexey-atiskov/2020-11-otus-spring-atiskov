package ru.atiskov.spring.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Quiz {
    private final Question question;
    private final List<Answer> answerList;

    public boolean isCorrectAnswer(String answer) {
        return question.getCorrectAnswer().getValue().equals(answer);
    }
}
