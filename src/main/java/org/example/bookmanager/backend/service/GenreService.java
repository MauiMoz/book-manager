package org.example.bookmanager.backend.service;

import org.example.bookmanager.backend.exceptions.GenreNotFoundException;
import org.example.bookmanager.backend.model.Book;
import org.example.bookmanager.backend.model.Genre;
import org.example.bookmanager.backend.repository.IBookRepository;
import org.example.bookmanager.backend.repository.IGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GenreService {

    @Autowired
    private IGenreRepository genRepo;

    @Autowired
    private IBookRepository bookRepo;

    public List<Genre> getAllGenres() {
        return genRepo.findAll();
    }

    public Genre getGenreById(Long id) {
        return genRepo.findById(id)
                .orElseThrow(() -> new GenreNotFoundException("Genre not found"));
    }

    public Genre saveGenre(Genre genre) {
        return genRepo.save(genre);
    }

    public void deleteGenre(Long id) {
        List<Book> books = bookRepo.findByGenreId(id);
        if (!books.isEmpty()) {
            throw new IllegalStateException(
                    "Cannot delete genre: " + books.size() + " book(s) are still using it."
            );
        }
        genRepo.deleteById(id);
    }
}
