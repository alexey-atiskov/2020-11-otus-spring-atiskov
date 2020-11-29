package ru.atiskov.spring.service;

import ru.atiskov.spring.domain.Person;
import ru.atiskov.spring.dao.PersonDao;

public class PersonServiceImpl implements PersonService {

    private final PersonDao dao;

    public PersonServiceImpl(PersonDao dao) {
        this.dao = dao;
    }

    public Person getByName(String name) {
        return dao.findByName(name);
    }
}
