package ru.atiskov.junit.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import ru.atiskov.spring.domain.Quiz;
import ru.atiskov.spring.service.QuizService;
import ru.atiskov.spring.service.QuizServiceImpl;
import ru.atiskov.spring.service.StringToQuizService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class QuizServiceImplTest {

    private QuizService quizService;

    @Mock
    private StringToQuizService stringToQuizService;

    @BeforeEach
    void setUp() {
        quizService = new QuizServiceImpl("quizName.txt", stringToQuizService);
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
}
