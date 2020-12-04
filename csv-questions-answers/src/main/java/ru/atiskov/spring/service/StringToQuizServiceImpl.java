package ru.atiskov.spring.service;

import java.util.ArrayList;
import java.util.List;

import ru.atiskov.spring.domain.Answer;
import ru.atiskov.spring.domain.Question;
import ru.atiskov.spring.domain.Quiz;

public class StringToQuizServiceImpl implements StringToQuizService {
    @Override
    public Quiz getQuiz(String quizString) {
        String[] qasStrings = quizString.split(",");
        Question question = Question.builder()
                .value(qasStrings[0])
                .correctAnswer(Answer.builder().value(qasStrings[1]).build())
                .build();
        List<Answer> answerList = new ArrayList<>();
        for (int i = 2; i < qasStrings.length; i++) {
            String answer = qasStrings[i];
            answerList.add(Answer.builder().value(answer).build());
        }
        Quiz quiz = Quiz.builder()
                .question(question)
                .answerList(answerList)
                .build();
        return quiz;
    }
}
