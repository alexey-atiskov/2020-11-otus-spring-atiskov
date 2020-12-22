package ru.atiskov.spring.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

import ru.atiskov.spring.config.AppProps;
import ru.atiskov.spring.domain.Answer;
import ru.atiskov.spring.domain.Quiz;

@Service
public class QuizProcessorCL implements QuizProcessor {

    private final MessageSource messageSource;
    private final AppProps props;

    private final Scanner in;

    public QuizProcessorCL(MessageSource messageSource, AppProps props) {
        this.messageSource = messageSource;
        this.props = props;
        in = new Scanner(System.in);
    }

    public void startAskingQuestions(String userName) {
        System.out.println(getLocalizedMessage("greeting.message", userName));
    }

    public void endAskingQuestions(int countOfCorrectAnswers) {
        System.out.println(countOfCorrectAnswers >= props.getCountOfAnswersForSuccess()
                ? getLocalizedMessage("result.message.ok")
                : getLocalizedMessage("result.message.fail"));
    }

    private String getLocalizedMessage(String code) {
        return messageSource.getMessage(code, null, props.getLocale());
    }

    private String getLocalizedMessage(String code, String param) {
        return messageSource.getMessage(code, new String[]{param}, props.getLocale());
    }

    public String getAnswerFromUser() {
        return in.next();
    }

    @Override
    public boolean isCorrectAnswer(Quiz quiz, String next) {
        return quiz.isCorrectAnswer(next);
    }

    public void askQuestion(Quiz quiz) {
        System.out.println(getLocalizedMessage(quiz.getQuestion().getValue()));
        List<Answer> answerList = quiz.getAnswerList();
        for (int i = 0; i < answerList.size(); i++) {
            Answer answer = answerList.get(i);
            System.out.println(i + ":" + answer.getValue());
        }
    }
}
