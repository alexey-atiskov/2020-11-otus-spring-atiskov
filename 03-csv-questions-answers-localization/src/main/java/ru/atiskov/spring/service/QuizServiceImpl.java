package ru.atiskov.spring.service;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import ru.atiskov.spring.config.AppProps;
import ru.atiskov.spring.domain.Quiz;

@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final StringToQuizConverter stringToQuizConverter;
    private final QuizProcessor quizProcessor;
    private final AppProps props;

    private List<String> initQuizFromFile() throws IOException {
        URL resource = QuizServiceImpl.class.getClassLoader().getResource(props.getQuizName());
        return FileUtils.readLines(new File(resource.getPath()), StandardCharsets.UTF_8);
    }

    @Override
    public List<Quiz> readQuizzes() throws IOException {
        List<String> qasList = initQuizFromFile();
        List<Quiz> quizList = new ArrayList<>();
        qasList.forEach(s -> quizList.add(stringToQuizConverter.getQuiz(s)));
        return quizList;
    }

    @Override
    public void askQuestions(List<Quiz> quizzes) {
        int countOfCorrectAnswers = 0;
        quizProcessor.startAskingQuestions();
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
    public void processQuiz() throws IOException {
        List<Quiz> quizzes = readQuizzes();
        askQuestions(quizzes);
    }
}
