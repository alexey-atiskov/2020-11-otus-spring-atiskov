package ru.atiskov.junit.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import ru.atiskov.spring.config.AppProps;
import ru.atiskov.spring.domain.Quiz;
import ru.atiskov.spring.service.QuizProcessor;
import ru.atiskov.spring.service.QuizService;
import ru.atiskov.spring.service.QuizServiceImpl;
import ru.atiskov.spring.service.StringToQuizService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuizServiceImplTest {

    private QuizService quizService;

    @Mock
    private QuizProcessor quizProcessorTest;
    @Mock
    private AppProps appProps;
    @Mock
    private StringToQuizService stringToQuizService;

    @BeforeEach
    void setUp() {
        quizService = new QuizServiceImpl(appProps, stringToQuizService, quizProcessorTest);
    }

    @Test
    void sameSizeQuiz() throws IOException {
        given(stringToQuizService.getQuiz(any())).willReturn(Quiz.builder().build());
        when(appProps.getQuizName()).thenReturn("quizTest.csv");

        List<Quiz> quizzes = quizService.readQuizzes();

        assertEquals(2, quizzes.size());
    }

    @Test
    void askQuestionsTest() throws IOException {
        when(appProps.getQuizName()).thenReturn("quizTest.csv");
        given(quizProcessorTest.getAnswerFromUser()).willReturn("0").willReturn("1");
        given(quizProcessorTest.isCorrectAnswer(any(), eq("0"))).willReturn(true);
        given(quizProcessorTest.isCorrectAnswer(any(), eq("1"))).willReturn(false);

        List<Quiz> quizzes = quizService.readQuizzes();
        quizService.askQuestions(quizzes);

        verify(quizProcessorTest, times(1)).startAskingQuestions();
        verify(quizProcessorTest, times(1)).endAskingQuestions(1);
        verify(quizProcessorTest, times(2)).askQuestion(any());
    }
}
