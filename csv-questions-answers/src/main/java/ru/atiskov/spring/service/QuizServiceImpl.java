package ru.atiskov.spring.service;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import ru.atiskov.spring.Main;
import ru.atiskov.spring.domain.Answer;
import ru.atiskov.spring.domain.Question;
import ru.atiskov.spring.domain.Quiz;

public class QuizServiceImpl implements QuizService {

    private final String quizName;

    public QuizServiceImpl(String quizName) {
        this.quizName = quizName;
    }

    @Override
    public List<String> initQuizFromFile() throws IOException {
        URL resource = Main.class.getClassLoader().getResource(quizName);
        return FileUtils.readLines(new File(resource.getPath()), StandardCharsets.UTF_8);
    }

    @Override
    public Set<Quiz> readQuiz(List<String> qasList) {
        Set<Quiz> quizSet = new HashSet<>();
        for (String qasString : qasList) {
            String[] qasStrings = qasString.split(",");
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
            quizSet.add(quiz);
        }
        return quizSet;
    }

    @Override
    public int processQuiz(Set<Quiz> quizzes) {
        int countOfCorrectAnswers = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number of correct answer:");
        for (Quiz quiz : quizzes) {
            System.out.println(quiz.getQuestion().getValue());
            List<Answer> answerList = quiz.getAnswerList();
            for (int i = 0; i < answerList.size(); i++) {
                Answer answer = answerList.get(i);
                System.out.println(i + ":" + answer.getValue());
            }
            String next = in.next();
            if (quiz.getQuestion().getCorrectAnswer().getValue().equals(next)) {
                countOfCorrectAnswers++;
            }
        }
        in.close();
        return countOfCorrectAnswers;
    }
}
