package ru.atiskov.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ru.atiskov.service.AuthorService;

@Configuration
@Import({AuthorService.class})
public class ControllerTestConfig {

}
