package ru.atiskov;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import lombok.AllArgsConstructor;
import ru.atiskov.dao.BookDao;
import ru.atiskov.domain.Book;

@ShellComponent
@AllArgsConstructor
public class JdbcShell {

    private final BookDao bookDao;

    @ShellMethod(key = "countb", value = "Print count of all books")
    public void start() {
        System.out.println("Books count is " + bookDao.count());
    }

    @ShellMethod(key = "addb", value = "Add book")
    public void addBookToLibrary(
            @ShellOption({"bookname", "bn"}) String bookName,
            @ShellOption({"bookid", "bi"}) Long bookId,// TODO auto-increment
            @ShellOption({"authorid", "ai"}) Long authorId,
            @ShellOption({"genreid", "gi"}) Long genreId) {
        bookDao.insert(new Book(bookId, authorId, genreId, bookName));
        //TODO check existance author id, and other id's
    }
}
