package ru.atiskov.spring.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

import ru.atiskov.spring.domain.Answer;
import ru.atiskov.spring.domain.Quiz;

@Service
public class QuizProcessorCL implements QuizProcessor {
    private Scanner in;

    public void startAskingQuestions() {
        in = new Scanner(System.in);
        System.out.println("Enter number of correct answer:");
    }

    public void endAskingQuestions() {
        in.close();
    }

    public String getAnswerFromUser() {
        return in.next();
    }

    public void askQuestion(Quiz quiz) {
        System.out.println(quiz.getQuestion().getValue());
        List<Answer> answerList = quiz.getAnswerList();
        for (int i = 0; i < answerList.size(); i++) {
            Answer answer = answerList.get(i);
            System.out.println(i + ":" + answer.getValue());
        }
    }
}
