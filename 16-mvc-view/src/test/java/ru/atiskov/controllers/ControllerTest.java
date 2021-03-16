package ru.atiskov.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import ru.atiskov.rest.AuthorController;
import ru.atiskov.service.AuthorService;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = AuthorController.class)
public class ControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthorService authorService;

    @Test
    public void test() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk());
    }
}