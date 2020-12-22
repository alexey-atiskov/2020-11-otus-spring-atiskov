package ru.atiskov.spring.service;

import ru.atiskov.spring.domain.Quiz;

public interface QuizProcessor {
    void startAskingQuestions();

    void endAskingQuestions(int countOfCorrectAnswers);

    String getAnswerFromUser();

    boolean isCorrectAnswer(Quiz quiz, String next);

    void askQuestion(Quiz quiz);
}
