package ru.atiskov;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import lombok.AllArgsConstructor;
import ru.atiskov.domain.Author;
import ru.atiskov.domain.Book;
import ru.atiskov.domain.Genre;
import ru.atiskov.repositories.AuthorRepository;
import ru.atiskov.repositories.BookRepository;
import ru.atiskov.repositories.CommentRepository;
import ru.atiskov.repositories.GenreRepository;

@ShellComponent
@AllArgsConstructor
public class JdbcShell {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CommentRepository commentRepository;

    @ShellMethod(key = "countb", value = "Print count of all books")
    public void start() {
        System.out.println("Books count is " + bookRepository.findAll().size());
    }

    @ShellMethod(key = "addb", value = "Add book")
    public void addBookToLibrary(
            @ShellOption({"bookname", "bn"}) String bookName,
            @ShellOption({"author", "a"}) String authorStr,
            @ShellOption({"genre", "g"}) String genreStr) {
        Author author = new Author(authorStr);
        authorRepository.save(author);
        Genre genre = new Genre(genreStr);
        genreRepository.save(genre);
        Book book = new Book(List.of(author), genre, bookName);
        bookRepository.save(book);
    }

    @ShellMethod(key = "getbi", value = "Get book infos")
    public void getBookInfos() {
        System.out.println("All books infos are = " + bookRepository.findAll());
    }

    @ShellMethod(key = "geta", value = "Get author infos")
    public void geAuthorInfos() {
        System.out.println("All author infos are = " + authorRepository.findAll());
    }

    @ShellMethod(key = "getb", value = "Get book info by id")
    public void getBookInfoById(
            @ShellOption({"book id", "bi"}) String bookId) {
        System.out.println("Book info is = " + bookRepository.findById(bookId));
    }

    @ShellMethod(key = "fb", value = "Get book info by name")
    public void getBookInfoByName(
            @ShellOption({"book name", "bn"}) String bookName) {
        System.out.println("Book info is = " + bookRepository.findByName(bookName));
    }

    @ShellMethod(key = "fba", value = "Get book info by author's name")
    public void getBookInfoByAuthorName(
            @ShellOption({"author name", "an"}) String authorName) {
        System.out.println("Book info is = " + bookRepository.findByAuthors_firstName(authorName));
    }

    @ShellMethod(key = "del", value = "Delete all info")
    public void deleteAll() {
        bookRepository.deleteAll();
    }
}
