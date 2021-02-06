package ru.atiskov.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class BookRepositoryTest {

    private static final int EXPECTED_NUMBER_OF_BOOKS = 2;
    private static final int EXPECTED_QUERIES_COUNT = 6;

    @Autowired
    private BookRepository repositoryJpa;

    @Autowired
    private TestEntityManager em;

//    @Test
//    void shouldFindExpectedBookById() {
//        Book book = repositoryJpa.findByName("Borodino");
//        Assertions.assertNotNull(book);
//        long bookId = book.getBookId();
//        Optional<Book> bookOpt = repositoryJpa.findById(bookId);
//        Book expectedBook = em.find(Book.class, bookId);
//        assertThat(bookOpt).isPresent().get()
//                .usingRecursiveComparison().isEqualTo(expectedBook);
//    }
//
//    @Test
//    void shouldReturnCorrectBooksListWithAllInfo() {
//        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
//                .unwrap(SessionFactory.class);
//        sessionFactory.getStatistics().setStatisticsEnabled(true);
//
//        System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
//        List<Book> books = repositoryJpa.findAll();
//        assertThat(books).isNotNull().hasSize(EXPECTED_NUMBER_OF_BOOKS)
//                .allMatch(s -> !s.getName().equals(""))
//                .allMatch(s -> s.getAuthors() != null && s.getAuthors().size() > 0)
//                .allMatch(s -> s.getGenre() != null)
//                .allMatch(s -> s.getComments() != null)
//                .allMatch((Book s) -> {
//                    if (s.getBookId() == 6) {
//                        return s.getComments().size() > 0;
//                    } else {
//                        return s.getComments().size() == 0;
//                    }
//                })
//        ;
//        System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
//        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);
//    }
//
//    @Test
//    void shouldFindAllBooks() {
//        List<Book> bookInfos = repositoryJpa.getBookInfos();
//
//        assertThat(bookInfos).isNotNull().hasSize(EXPECTED_NUMBER_OF_BOOKS)
//                .allMatch(s -> !s.getName().equals(""))
//                .allMatch(s -> s.getAuthors() != null && s.getAuthors().size() > 0)
//                .allMatch(s -> s.getGenre() != null)
//                .allMatch(s -> s.getComments() != null)
//                .allMatch((Book s) -> {
//                    if (s.getBookId() == 6) {
//                        return s.getComments().size() > 0;
//                    } else {
//                        return s.getComments().size() == 0;
//                    }
//                })
//        ;
//    }
}