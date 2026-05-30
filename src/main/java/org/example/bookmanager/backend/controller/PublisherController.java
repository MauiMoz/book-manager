package org.example.bookmanager.backend.controller;

import jakarta.validation.Valid;
import org.example.bookmanager.backend.model.Publisher;
import org.example.bookmanager.backend.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public String listPublishers(Model model) {
        model.addAttribute("publishers", publisherService.getAllPublishers());
        return "publishers/list";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "publishers/form";
    }

    @PostMapping
    public String addPublisher(@Valid @ModelAttribute Publisher publisher, BindingResult result) {
        if (result.hasErrors()) return "publishers/form";
        publisherService.savePublisher(publisher);
        return "redirect:/publishers";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("publisher", publisherService.getPublisherById(id));
        return "publishers/form";
    }

    @PostMapping("/update/{id}")
    public String updatePublisher(@PathVariable Long id, @Valid @ModelAttribute Publisher publisher,
                                  BindingResult result) {
        if (result.hasErrors()) return "publishers/form";
        publisher.setId(id);
        publisherService.savePublisher(publisher);
        return "redirect:/publishers";
    }

    @GetMapping("/delete/{id}")
    public String deletePublisher(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            publisherService.deletePublisher(id);
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/publishers";
    }
}
