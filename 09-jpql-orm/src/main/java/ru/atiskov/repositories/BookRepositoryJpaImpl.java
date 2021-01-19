package ru.atiskov.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ru.atiskov.models.Book;

@Transactional // TODO on methods with readOnly true
@Repository
public class BookRepositoryJpaImpl implements BookRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Book save(Book book) {
        if (book.getBookId() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> findAll() {
//        EntityGraph<?> entityGraph = em.getEntityGraph("book-comments-entity-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b join fetch b.genre", Book.class);
//        query.setHint("javax.persistence.fetchgraph", entityGraph); uncomment only for education purposes
        return query.getResultList();
    }

    @Override
    public List<Book> findByName(String name) {
        TypedQuery<Book> query = em.createQuery("select b " +
                        "from Book b " +
                        "where b.name = :name",
                Book.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from Book b " +
                "where b.book_id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void remove(Book book) {
        em.remove(book);
    }

    @Override
    public List<Book> getBookInfos() {
        return em.createQuery(
                "select new ru.atiskov.models.Book(b.authors, b.genre, b.name) " +
                        "from Book b"
                , Book.class).getResultList();
    }
}
