package org.example.bookmanager.backend.service;

import org.example.bookmanager.backend.exceptions.PublisherNotFoundException;
import org.example.bookmanager.backend.model.Book;
import org.example.bookmanager.backend.model.Publisher;
import org.example.bookmanager.backend.repository.IBookRepository;
import org.example.bookmanager.backend.repository.IPublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PublisherService {

    @Autowired
    private IPublisherRepository pubRepo;

    @Autowired
    private IBookRepository bookRepo;

    public List<Publisher> getAllPublishers() { return pubRepo.findAll(); }

    public Publisher getPublisherById(Long id) {
        return pubRepo.findById(id).orElseThrow(() -> new PublisherNotFoundException("Publisher not found"));
    }

    public Publisher savePublisher(Publisher publisher) { return pubRepo.save(publisher); }

    public void deletePublisher(Long id) {
        List<Book> books = bookRepo.findByPublisherId(id);
        if (!books.isEmpty()) {
            throw new IllegalStateException(
                    "Cannot delete publisher: " + books.size() + " book(s) are still using it."
            );
        }
        pubRepo.deleteById(id);
    }
}
