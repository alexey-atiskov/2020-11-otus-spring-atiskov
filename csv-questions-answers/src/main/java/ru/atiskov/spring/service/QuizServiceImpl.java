package ru.atiskov.spring.service;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ru.atiskov.spring.Main;
import ru.atiskov.spring.domain.Answer;
import ru.atiskov.spring.domain.Quiz;

public class QuizServiceImpl implements QuizService {

    private final String quizName;
    private final StringToQuizService stringToQuizService;

    public QuizServiceImpl(String quizName, StringToQuizService stringToQuizService) {
        this.quizName = quizName;
        this.stringToQuizService = stringToQuizService;
    }

    @Override
    public List<String> initQuizFromFile() throws IOException {
        URL resource = QuizServiceImpl.class.getClassLoader().getResource(quizName);
        return FileUtils.readLines(new File(resource.getPath()), StandardCharsets.UTF_8);
    }

    @Override
    public List<Quiz> readQuizzes(List<String> qasList) {
        List<Quiz> quizSet = new ArrayList<>();
        qasList.forEach(s -> quizSet.add(stringToQuizService.getQuiz(s)));
        return quizSet;
    }

    @Override
    public int processQuiz(List<Quiz> quizzes) {
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
