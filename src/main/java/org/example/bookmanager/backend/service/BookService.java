package org.example.bookmanager.backend.service;

import org.example.bookmanager.backend.exceptions.BookNotFoundException;
import org.example.bookmanager.backend.model.Book;
import org.example.bookmanager.backend.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService {

    @Autowired
    private IBookRepository bookRepo;

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepo.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found"));
    }

    public Book saveBook(Book book) {
        return bookRepo.save(book);
    }

    public void deleteBook(Long id) {
        bookRepo.deleteById(id);
    }
}
