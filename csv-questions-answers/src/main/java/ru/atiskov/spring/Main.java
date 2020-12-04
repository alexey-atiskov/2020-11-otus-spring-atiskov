package ru.atiskov.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import ru.atiskov.spring.domain.Quiz;
import ru.atiskov.spring.service.QuizService;

public class Main {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuizService service = context.getBean(QuizService.class);
        List<String> qasList = service.initQuizFromFile();
        List<Quiz> quizzes = service.readQuizzes(qasList);
        int countOfCorrectAnswers = service.processQuiz(quizzes);
        System.out.println("Correct number of answers is " + countOfCorrectAnswers);
    }
}
