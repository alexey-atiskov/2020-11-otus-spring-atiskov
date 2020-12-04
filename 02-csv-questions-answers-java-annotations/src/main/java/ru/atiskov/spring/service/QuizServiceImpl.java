package ru.atiskov.spring.service;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import ru.atiskov.spring.domain.Quiz;

@Service
public class QuizServiceImpl implements QuizService {

    private final String quizName;
    private final StringToQuizService stringToQuizService;
    private final QuizProcessor quizProcessor;
    @Value("${app.countOfAnswersForSuccess}")
    private int countOfAnswersForSuccess;

    public QuizServiceImpl(@Value("${app.quizName}") String quizName,
                           StringToQuizService stringToQuizService,
                           QuizProcessor quizProcessor) {
        this.quizName = quizName;
        this.stringToQuizService = stringToQuizService;
        this.quizProcessor = quizProcessor;
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
    public int askQuestions(List<Quiz> quizzes) {
        int countOfCorrectAnswers = 0;
        quizProcessor.startAskingQuestions();
        for (Quiz quiz : quizzes) {
            quizProcessor.askQuestionCommandLine(quiz);
            String next = quizProcessor.getAnswerFromUserCL();
            if (quiz.getQuestion().getCorrectAnswer().getValue().equals(next)) {
                countOfCorrectAnswers++;
            }
        }
        quizProcessor.endAskingQuestionsCL();
        return countOfCorrectAnswers;
    }

    @Override
    public boolean processQuiz() throws IOException {
        List<String> qasList = initQuizFromFile();
        List<Quiz> quizzes = readQuizzes(qasList);
        return askQuestions(quizzes) >= countOfAnswersForSuccess;
    }
}
