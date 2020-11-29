package ru.atiskov.spring.service;

import ru.atiskov.spring.domain.Person;

public interface PersonService {

    Person getByName(String name);
}
