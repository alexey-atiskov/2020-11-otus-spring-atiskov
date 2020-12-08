package ru.atiskov.spring.service;

import java.io.IOException;
import java.util.List;

import ru.atiskov.spring.domain.Quiz;

public interface QuizService {
    List<Quiz> readQuizzes() throws IOException;

    void askQuestions(List<Quiz> quizzes);

    void processQuiz() throws IOException;
}
