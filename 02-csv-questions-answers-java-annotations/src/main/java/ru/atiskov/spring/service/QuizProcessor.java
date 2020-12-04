package ru.atiskov.spring.service;

import ru.atiskov.spring.domain.Quiz;

public interface QuizProcessor {
    void startAskingQuestions();

    void endAskingQuestions();

    String getAnswerFromUser();

    void askQuestion(Quiz quiz);
}
