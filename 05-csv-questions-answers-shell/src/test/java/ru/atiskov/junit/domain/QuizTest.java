package ru.atiskov.junit.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import ru.atiskov.spring.domain.Answer;
import ru.atiskov.spring.domain.Question;
import ru.atiskov.spring.domain.Quiz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayName("Class Quiz")
class QuizTest {

    private static final String QUESTION = "question";
    private static final String CORRECT_ANSWER = "0";

    @DisplayName("Correctly created by builder")
    @Test
    void shouldHaveCorrectBuilder() {
        ArrayList<Answer> answerList = new ArrayList<>();
        answerList.add(Answer.builder().value("only0").build());
        answerList.add(Answer.builder().value("only1").build());
        Quiz question = Quiz.builder()
                .answerList(answerList)
                .question(Question.builder().value(QUESTION).correctAnswer(Answer.builder().value(CORRECT_ANSWER).build()).build())
                .build();

        assertEquals(QUESTION, question.getQuestion().getValue());
        assertNotEquals("questionBad", question.getQuestion().getValue());
        assertEquals(Answer.builder().value(CORRECT_ANSWER).build(), question.getQuestion().getCorrectAnswer());
    }
}
