package ru.atiskov.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import ru.atiskov.models.Author;
import ru.atiskov.service.AuthorService;

@Controller
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/")
    public String listAuthors(Model model) {
        List<Author> authors = authorService.getAll();
        model.addAttribute("authors", authors);
        return "list";
    }

    @GetMapping("/edit")
    public String editAuthor(@RequestParam("id") long id, Model model) {
        Author author = authorService.getById(id).orElseThrow(() -> new NotFoundException("Cannot find by id " + id));
        model.addAttribute("author", author);
        return "edit";
    }

    @PostMapping("/edit")
    public String saveAuthor(Author author, Model model) {
        Author saved = authorService.save(author);
        model.addAttribute(saved);
//        return "redirect:/edit?id=" + saved.getAuthId();
        return "redirect:/";
    }
}
