package ru.atiskov;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import lombok.AllArgsConstructor;
import ru.atiskov.models.Author;
import ru.atiskov.models.Book;
import ru.atiskov.models.Genre;
import ru.atiskov.repositories.AuthorRepositoryJpa;
import ru.atiskov.repositories.BookRepositoryJpa;
import ru.atiskov.repositories.CommentRepositoryJpa;
import ru.atiskov.repositories.GenreRepositoryJpa;

@ShellComponent
@AllArgsConstructor
public class JdbcShell {

    @Autowired
    private BookRepositoryJpa bookRepositoryJpa;
    @Autowired
    private GenreRepositoryJpa genreRepository;
    @Autowired
    private AuthorRepositoryJpa authorRepository;
    @Autowired
    private CommentRepositoryJpa commentRepository;

    @ShellMethod(key = "countb", value = "Print count of all books")
    public void start() {
        System.out.println("Books count is " + bookRepositoryJpa.findAll().size());
    }

    @ShellMethod(key = "addb", value = "Add book")
    @Transactional
    public void addBookToLibrary(
            @ShellOption({"bookname", "bn"}) String bookName,
            @ShellOption({"author", "a"}) String authorStr,
            @ShellOption({"genre", "g"}) String genreStr) {
        Author author = new Author(authorStr);
        authorRepository.save(author);
        Genre genre = new Genre(genreStr);
        genreRepository.save(genre);
        Book book = new Book(List.of(author), genre, bookName);
        bookRepositoryJpa.save(book);
    }

    @ShellMethod(key = "getbi", value = "Get book infos")
    @Transactional(readOnly = true)
    public void getBookInfos() {
        System.out.println("All books infos are = " + bookRepositoryJpa.getBookInfos());
    }

    @ShellMethod(key = "getb", value = "Get book info by id")
    @Transactional(readOnly = true)
    public void getBookInfoById(
            @ShellOption({"book id", "bi"}) long bookId) {
        System.out.println("Book info is = " + bookRepositoryJpa.findById(bookId));
    }

    @ShellMethod(key = "fb", value = "Get book info by name")
    @Transactional(readOnly = true)
    public void getBookInfoByName(
            @ShellOption({"book name", "bn"}) String bookName) {
        System.out.println("Book info is = " + bookRepositoryJpa.findByName(bookName));
    }

    @ShellMethod(key = "fba", value = "Get book info by author's name")
    @Transactional(readOnly = true)
    public void getBookInfoByAuthorName(
            @ShellOption({"author name", "an"}) String authorName) {
        System.out.println("Book info is = " + bookRepositoryJpa.findByAuthors_firstName(authorName));
    }

    @ShellMethod(key = "del", value = "Delete all info")
    @Transactional
    public void deleteAll() {
        bookRepositoryJpa.deleteAll();
    }
}
