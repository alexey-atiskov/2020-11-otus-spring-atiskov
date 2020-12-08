package ru.atiskov.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

import lombok.Data;
import lombok.Value;

@ConfigurationProperties(prefix = "app")
@Data
public class AppProps {
    private String quizName;
    private int countOfAnswersForSuccess;
    private Locale locale;
}
