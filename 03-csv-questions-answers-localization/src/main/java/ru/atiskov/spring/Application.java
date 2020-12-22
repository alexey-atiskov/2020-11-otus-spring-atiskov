package ru.atiskov.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

import ru.atiskov.spring.config.AppProps;
import ru.atiskov.spring.service.QuizService;

@SpringBootApplication
@EnableConfigurationProperties(AppProps.class)
public class Application {

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        QuizService service = context.getBean(QuizService.class);
        service.processQuiz();
    }
}
