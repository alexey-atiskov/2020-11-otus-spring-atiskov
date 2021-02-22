package ru.atiskov.rest;

class NotFoundException extends RuntimeException{

    NotFoundException(String s) {
        super(s);
    }
}