package ru.atiskov.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import ru.atiskov.domain.Book;
import ru.atiskov.domain.BookInfo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {

    private static final int EXPECTED_BOOKS_COUNT = 3;

    @Autowired
    private BookDao bookDao;

    @Test
    void count() {
        int booksCount = bookDao.count();
        assertThat(booksCount).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @Test
    void insert() {
        Book expectedBook = new Book(2, 2, "title3");
        expectedBook.setBookId(4);

        bookDao.insert(expectedBook);

        Book actualBook = bookDao.getById(4);
        assertThat(actualBook).isEqualTo(expectedBook);
    }

    @Test
    void getById() {
        Book expectedBook = new Book(1, 2, "book1");
        expectedBook.setBookId(1);

        Book actualBook = bookDao.getById(1);

        assertThat(actualBook).isEqualTo(expectedBook);
    }

    @Test
    void getBookInfoById() {
        BookInfo expectedBook = new BookInfo("book1", "alexey", "jurievich");

        BookInfo actualBook = bookDao.getBookInfoById(1);

        assertThat(actualBook).isEqualTo(expectedBook);
    }

    @Test
    void getAll() {
        List<Book> all = bookDao.getAll();
        assertEquals("Size of books library", all.size(), EXPECTED_BOOKS_COUNT);
    }

    @Test
    void deleteById() {
        Book expectedBook = new Book(1, 2, "book1");
        expectedBook.setBookId(1);
        Book actualBook = bookDao.getById(1);
        assertThat(actualBook).isEqualTo(expectedBook);

        bookDao.deleteById(1);

        assertThrows(EmptyResultDataAccessException.class, () -> bookDao.getById(1));
    }
}