package ru.atiskov.junit.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
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
        quizService = new QuizServiceImpl("quizTest.csv", 1, stringToQuizService, quizProcessorTest);
    }

    @Test
    @Disabled
    void sameSizeQuiz() throws IOException {
        given(stringToQuizService.getQuiz(any())).willReturn(Quiz.builder().build());
//        given(stringToQuizService.initQuizFromFile(any())).willReturn(Quiz.builder().build());
//        List<String> qasList = initQuizFromFile();
//        ArrayList<String> qasList = new ArrayList<>();
//        qasList.add("0,1,2");
//        qasList.add("1,2,3");
        List<Quiz> quizzes = quizService.readQuizzes();

//        assertEquals(qasList.size(), quizzes.size());
    }

    @Test
    @Disabled
    void askQuestionsTest() throws IOException {
        List<Quiz> quizzes = quizService.readQuizzes();

        given(quizProcessorTest.getAnswerFromUser()).willReturn("0");

        quizService.askQuestions(quizzes);// TODO: how to pass implementation of getQuiz for stringToQuizService

        verify(quizProcessorTest, times(1)).startAskingQuestions();
        verify(quizProcessorTest, times(1)).endAskingQuestions();
        verify(quizProcessorTest, times(2)).askQuestion(any());
//        countOfCorrectAnswers == 1// TODO: how to check
    }
}
