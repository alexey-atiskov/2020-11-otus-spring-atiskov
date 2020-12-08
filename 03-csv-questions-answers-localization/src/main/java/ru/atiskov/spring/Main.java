package ru.atiskov.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

import ru.atiskov.spring.domain.Quiz;
import ru.atiskov.spring.service.QuizService;

@ComponentScan
@Configuration
@PropertySource("classpath:application.properties")
public class Main {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        QuizService service = context.getBean(QuizService.class);
        service.processQuiz();
    }
}
