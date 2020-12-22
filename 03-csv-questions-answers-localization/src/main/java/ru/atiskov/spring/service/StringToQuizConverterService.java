package ru.atiskov.spring.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import ru.atiskov.spring.domain.Answer;
import ru.atiskov.spring.domain.Question;
import ru.atiskov.spring.domain.Quiz;

@Service
public class StringToQuizConverterService implements StringToQuizConverter {
    private static final int START_CELL_INDEX_FOR_ANSWERS = 2;

    @Override
    public Quiz getQuiz(String quizString) {
        String[] qasStrings = quizString.split(",");
        Question question = Question.builder()
                .value(qasStrings[0])
                .correctAnswer(Answer.builder().value(qasStrings[1]).build())
                .build();
        List<Answer> answerList = new ArrayList<>();
        for (int i = START_CELL_INDEX_FOR_ANSWERS; i < qasStrings.length; i++) {
            String answer = qasStrings[i];
            answerList.add(Answer.builder().value(answer).build());
        }
        return Quiz.builder()
                .question(question)
                .answerList(answerList)
                .build();
    }
}
