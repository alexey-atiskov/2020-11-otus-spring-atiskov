package ru.atiskov.junit.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

import ru.atiskov.spring.domain.Answer;
import ru.atiskov.spring.domain.Question;
import ru.atiskov.spring.domain.Quiz;
import ru.atiskov.spring.service.QuizProcessor;
import ru.atiskov.spring.service.QuizService;
import ru.atiskov.spring.service.QuizServiceImpl;
import ru.atiskov.spring.service.StringToQuizService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@PropertySource("classpath:application.properties")
class QuizServiceImplTest {

    private QuizService quizService;

    @Mock
    private QuizProcessor quizProcessorTest;
    @Mock
    private StringToQuizService stringToQuizService;

    @Value("${app.countOfAnswersForSuccess}")
    private int countOfAnswersForSuccess;
    @Value("${app.quizName}")
    private String quizName;// TODO: currently null

    @BeforeEach
    void setUp() {
        quizService = new QuizServiceImpl(quizName, stringToQuizService, quizProcessorTest);
    }

    @Test
    void sameSizeQuiz() {
        given(stringToQuizService.getQuiz(any())).willReturn(Quiz.builder().build());

        ArrayList<String> qasList = new ArrayList<>();
        qasList.add("0,1,2");
        qasList.add("1,2,3");
        List<Quiz> quizzes = quizService.readQuizzes(qasList);

        assertEquals(qasList.size(), quizzes.size());
    }

    @Test
    void askQuestionsTest() {
        given(quizProcessorTest.getAnswerFromUser()).willReturn("0");

        quizService.askQuestions(new ArrayList<>());// TODO: how to read from quizTest.csv

        verify(quizProcessorTest, times(1)).startAskingQuestions();
        verify(quizProcessorTest, times(1)).endAskingQuestions();
//        verify(quizProcessorTest, times(2)).askQuestion();// TODO: how to check
//        countOfCorrectAnswers == 1// TODO: how to check
    }
}
