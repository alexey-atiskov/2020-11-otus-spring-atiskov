package ru.atiskov.controllers;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ru.atiskov.service.AuthorService;

@Configuration
@Import({AuthorService.class})
public class ControllerTestConfig {

    @Bean
    AuthorService authorService() {
        return Mockito.mock(AuthorService.class);
    }
}
