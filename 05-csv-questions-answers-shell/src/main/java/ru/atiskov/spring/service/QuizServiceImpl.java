package ru.atiskov.spring.service;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import ru.atiskov.spring.config.AppProps;
import ru.atiskov.spring.domain.Quiz;

@Service
public class QuizServiceImpl implements QuizService {

    private final StringToQuizService stringToQuizService;
    private final QuizProcessor quizProcessor;

    private final AppProps props;

    private String userName;

    public QuizServiceImpl(AppProps props,
                           StringToQuizService stringToQuizService,
                           QuizProcessor quizProcessor) {
        this.props = props;
        this.stringToQuizService = stringToQuizService;
        this.quizProcessor = quizProcessor;
    }

    private List<String> initQuizFromFile() throws IOException {
        URL resource = QuizServiceImpl.class.getClassLoader().getResource(props.getQuizName());
        return FileUtils.readLines(new File(resource.getPath()), StandardCharsets.UTF_8);
    }

    @Override
    public List<Quiz> readQuizzes() throws IOException {
        List<String> qasList = initQuizFromFile();
        List<Quiz> quizSet = new ArrayList<>();
        qasList.forEach(s -> quizSet.add(stringToQuizService.getQuiz(s)));
        return quizSet;
    }

    @Override
    public void askQuestions(List<Quiz> quizzes) {
        int countOfCorrectAnswers = 0;
        for (Quiz quiz : quizzes) {
            quizProcessor.askQuestion(quiz);
            String next = quizProcessor.getAnswerFromUser();
            if (quizProcessor.isCorrectAnswer(quiz, next)) {
                countOfCorrectAnswers++;
            }
        }
        quizProcessor.endAskingQuestions(countOfCorrectAnswers);
    }

    @Override
    public void processQuiz(String userName) throws IOException {
        List<Quiz> quizzes = readQuizzes();
        askQuestions(quizzes);
    }

    public void startAskingQuestions(String userName) {
        quizProcessor.startAskingQuestions(userName);
    }
}
