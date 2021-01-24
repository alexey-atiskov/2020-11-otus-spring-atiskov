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
import ru.atiskov.repositories.BookRepositoryJpa;

@ShellComponent
@AllArgsConstructor
public class JdbcShell {

    @Autowired
    private BookRepositoryJpa bookRepositoryJpa;

    @ShellMethod(key = "countb", value = "Print count of all books")
    public void start() {
        System.out.println("Books count is " + bookRepositoryJpa.findAll().size());
    }

    @ShellMethod(key = "addb", value = "Add book")
    public void addBookToLibrary(
            @ShellOption({"bookname", "bn"}) String bookName,
            @ShellOption({"author", "a"}) String author,
            @ShellOption({"genre", "g"}) String genre) {
        bookRepositoryJpa.save(new Book(List.of(new Author(author)), new Genre(genre), bookName));
    }

    @ShellMethod(key = "getbi", value = "Get book infos")
    public void getBookInfos() {
        System.out.println("All books infos are = " + bookRepositoryJpa.getBookInfos());
    }

    @ShellMethod(key = "getb", value = "Get book info by id")
    @Transactional(readOnly = true)
    public void getBookInfoById(
            @ShellOption({"book id", "bi"}) long bookId) {
        System.out.println("Book info is = " + bookRepositoryJpa.findById(bookId));
    }
}
