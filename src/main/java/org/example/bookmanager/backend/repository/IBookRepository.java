package org.example.bookmanager.backend.repository;

import org.example.bookmanager.backend.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface IBookRepository extends JpaRepository<Book, Long> {
    List<Book> findByGenreId(Long id);
    List<Book> findByPublisherId(Long id);
}
