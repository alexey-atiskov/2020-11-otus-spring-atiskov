package ru.atiskov.spring.service;

import ru.atiskov.spring.domain.Quiz;

public interface QuizProcessor {
    void startAskingQuestions();

    void endAskingQuestionsCL();

    String getAnswerFromUserCL();

    void askQuestionCommandLine(Quiz quiz);
}
