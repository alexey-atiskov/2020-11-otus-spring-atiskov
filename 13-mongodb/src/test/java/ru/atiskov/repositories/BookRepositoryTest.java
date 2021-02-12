package ru.atiskov.repositories;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import ru.atiskov.domain.Author;
import ru.atiskov.domain.Book;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class BookRepositoryTest {

    @Autowired
    private BookRepository repositoryJpa;

    @Autowired
    private AuthorRepository repository;

    @Test
    public void test(@Autowired MongoTemplate mongoTemplate) {
        // given
        DBObject objectToSave = BasicDBObjectBuilder.start()
                .add("key", "value")
                .get();

        // when
        mongoTemplate.save(objectToSave, "collection");

        // then
        assertThat(mongoTemplate.findAll(DBObject.class, "collection")).extracting("key")
                .containsOnly("value");
    }

    @Test
    void shouldFindExpectedBookById() {
        Book book = repositoryJpa.findByName("Borodino");
        Assertions.assertNotNull(book);
        String bookId = book.getBookId();
        Optional<Book> bookOpt = repositoryJpa.findById(bookId);
        assertThat(bookOpt).isNotEmpty();
    }

    @Test
    void shouldFindExpectedAuthorById() {
        Author author = repository.findByFirstName("Repin");
        Assertions.assertNotNull(author);
        String authId = author.getAuthId();
        Optional<Author> bookOpt = repository.findById(authId);
        assertThat(bookOpt).isNotEmpty();
    }
}
