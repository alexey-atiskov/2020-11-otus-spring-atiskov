package ru.atiskov.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.transaction.Transactional;

import ru.atiskov.models.Author;
import ru.atiskov.models.Book;
import ru.atiskov.models.Genre;
import ru.atiskov.repositories.BookRepositoryJpa;
import ru.atiskov.repositories.GenreRepositoryJpa;

@Controller
public class BookController {
    private final BookRepositoryJpa bookRepositoryJpa;
    private final GenreRepositoryJpa genreRepositoryJpa;

    @Autowired
    public BookController(BookRepositoryJpa bookRepositoryJpa, GenreRepositoryJpa genreRepositoryJpa) {
        this.bookRepositoryJpa = bookRepositoryJpa;
        this.genreRepositoryJpa = genreRepositoryJpa;
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        List<Book> books = bookRepositoryJpa.findAll();
        model.addAttribute("books", books);
        return "listBooks";
    }

    @GetMapping("/editbook")
    public String editBook(@RequestParam("id") long id, Model model) {
        Book book = bookRepositoryJpa.findById(id).orElseThrow(() -> new NotFoundException("Cannot find by id " + id));
        model.addAttribute("book", book);
        List<Genre> genres = genreRepositoryJpa.findAll();
        model.addAttribute("genres", genres);
        return "editbook";
    }

    @PostMapping("/editbook")
    public String saveBook(Author author, Long bookId, Long genId, Model model) {
        Book book = getBook(author, bookId, genId);
        Book saved = getBook(book);
        model.addAttribute(saved);
        return "redirect:/books";
    }

    @Transactional
    private Book getBook(Book book) {
        Book saved = bookRepositoryJpa.save(book);
        return saved;
    }

    @Transactional
    private Book getBook(Author author, Long bookId, Long genId) {
        Book book = bookRepositoryJpa.findById(bookId).orElseThrow(() -> new NotFoundException("Cannot find by id " + bookId));
        book.setAuthors(author);
        Genre genre = genreRepositoryJpa.findById(genId).orElseThrow(() -> new NotFoundException("Cannot find by id " + genId));
        book.setGenre(genre);
        return book;
    }
}
