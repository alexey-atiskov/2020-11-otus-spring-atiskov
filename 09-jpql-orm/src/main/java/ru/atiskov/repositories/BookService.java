package ru.atiskov.repositories;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service
public class BookService {

    @PersistenceContext
    private EntityManager em;

    public void updateNameById(long id, String name) {
        Query query = em.createQuery("update Book b " +
                "set b.name = :name " +
                "where b.book_id = :id");
        query.setParameter("name", name);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
