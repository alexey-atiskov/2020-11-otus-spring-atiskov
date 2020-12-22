package ru.atiskov.spring;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.IOException;

import ru.atiskov.spring.service.QuizService;

@ShellComponent
public class QAShell {

    private final QuizService service;

    public QAShell(QuizService service) {
        this.service = service;
    }

    @ShellMethod(key = "start", value = "Start quiz service")
    public void start(@ShellOption({"username", "u"}) String userName) throws IOException {
        service.startAskingQuestions(userName);
        service.processQuiz(userName);
    }
}
