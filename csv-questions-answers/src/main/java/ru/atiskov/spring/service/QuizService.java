package ru.atiskov.spring.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import ru.atiskov.spring.domain.Quiz;

public interface QuizService {
    List<String> initQuizFromFile() throws IOException;

    Set<Quiz> readQuiz(List<String> qasList);

    int processQuiz(Set<Quiz> quizzes);
}
