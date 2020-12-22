package ru.atiskov.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import ru.atiskov.domain.Book;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {

    public static final int EXPECTED_BOOKS_COUNT = 3;
    @Autowired
    private BookDao bookDao;

    @Test
    void count() {
        int booksCount = bookDao.count();
        assertThat(booksCount).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @Test
    void insert() {
        Book expectedBook = new Book(3, 2, 2, "title3");

        bookDao.insert(expectedBook);

        Book actualBook = bookDao.getById(3);
        assertThat(actualBook).isEqualTo(expectedBook);
    }
}