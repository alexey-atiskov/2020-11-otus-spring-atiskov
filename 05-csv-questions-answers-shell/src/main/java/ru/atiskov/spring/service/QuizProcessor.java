package ru.atiskov.spring.service;

import ru.atiskov.spring.domain.Quiz;

public interface QuizProcessor {
    void startAskingQuestions(String userName);

    void endAskingQuestions(int countOfCorrectAnswers);

    String getAnswerFromUser();

    boolean isCorrectAnswer(Quiz quiz, String next);

    void askQuestion(Quiz quiz);
}
