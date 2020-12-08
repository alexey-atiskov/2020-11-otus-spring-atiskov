package ru.atiskov.spring.service;

import ru.atiskov.spring.domain.Quiz;

public interface QuizProcessor {
    void startAskingQuestions();

    void endAskingQuestions(int countOfCorrectAnswers);

    String getAnswerFromUser();

    void askQuestion(Quiz quiz);
}
