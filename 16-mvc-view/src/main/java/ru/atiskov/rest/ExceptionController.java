package ru.atiskov.rest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handle(NotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("err500");
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }
}
