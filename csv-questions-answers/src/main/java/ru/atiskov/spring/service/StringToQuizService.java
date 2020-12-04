package ru.atiskov.spring.service;

import ru.atiskov.spring.domain.Quiz;

public interface StringToQuizService {
    Quiz getQuiz(String quizString);
}
