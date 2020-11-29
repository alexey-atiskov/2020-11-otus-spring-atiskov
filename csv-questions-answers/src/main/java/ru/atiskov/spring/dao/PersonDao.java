package ru.atiskov.spring.dao;

import ru.atiskov.spring.domain.Person;

public interface PersonDao {

    Person findByName(String name);
}
