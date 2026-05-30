package org.example.bookmanager.backend.controller;

import jakarta.validation.Valid;
import org.example.bookmanager.backend.model.Genre;
import org.example.bookmanager.backend.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public String listGenres(Model model) {
        model.addAttribute("genres", genreService.getAllGenres());
        return "genres/list";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("genre", new Genre());
        return "genres/form";
    }

    @PostMapping
    public String addGenre(@Valid @ModelAttribute Genre genre, BindingResult result) {
        if (result.hasErrors()) return "genres/form";
        genreService.saveGenre(genre);
        return "redirect:/genres";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("genre", genreService.getGenreById(id));
        return "genres/form";
    }

    @PostMapping("/update/{id}")
    public String updateGenre(@PathVariable Long id, @Valid @ModelAttribute Genre genre,
                              BindingResult result) {
        if (result.hasErrors()) return "genres/form";
        genre.setId(id);
        genreService.saveGenre(genre);
        return "redirect:/genres";
    }

    @GetMapping("/delete/{id}")
    public String deleteGenre(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            genreService.deleteGenre(id);
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/genres";
    }
}
