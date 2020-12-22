package ru.atiskov.spring.service;

import ru.atiskov.spring.domain.Quiz;

public interface StringToQuizConverter {
    Quiz getQuiz(String quizString);
}
