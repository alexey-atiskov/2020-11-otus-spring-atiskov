package ru.atiskov.spring.dao;

import ru.atiskov.spring.domain.Person;

public class PersonDaoSimple implements PersonDao {

    public Person findByName(String name) {
        return new Person(name, 18);
    }
}
