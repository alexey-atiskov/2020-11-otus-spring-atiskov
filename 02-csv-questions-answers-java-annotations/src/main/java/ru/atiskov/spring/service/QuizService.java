package ru.atiskov.spring.service;

import java.io.IOException;
import java.util.List;

import ru.atiskov.spring.domain.Quiz;

public interface QuizService {
    List<String> initQuizFromFile() throws IOException;

    List<Quiz> readQuizzes(List<String> qasList);

    int askQuestions(List<Quiz> quizzes);

    boolean processQuiz() throws IOException;
}
