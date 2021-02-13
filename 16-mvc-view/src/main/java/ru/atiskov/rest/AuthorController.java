package ru.atiskov.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import ru.atiskov.models.Author;
import ru.atiskov.repositories.AuthorRepositoryJpa;

@Controller
public class AuthorController {

    private final AuthorRepositoryJpa repository;

    @Autowired
    public AuthorController(AuthorRepositoryJpa repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String listAuthors(Model model) {
        List<Author> authors = repository.findAll();
        model.addAttribute("authors", authors);
        return "list";
    }

    @GetMapping("/edit")
    public String editAuthor(@RequestParam("id") long id, Model model) {
        Author author = repository.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("author", author);
        return "edit";
    }

    @PostMapping("/edit")
    public String saveAuthor(Author author, Model model) {
        Author saved = repository.save(author);
        model.addAttribute(saved);
//        return "redirect:/edit?id=" + saved.getAuthId();
        return "redirect:/";
    }
}
