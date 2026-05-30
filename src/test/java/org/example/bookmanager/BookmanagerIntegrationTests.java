package org.example.bookmanager;

import jakarta.transaction.Transactional;
import org.example.bookmanager.backend.model.Address;
import org.example.bookmanager.backend.model.Book;
import org.example.bookmanager.backend.model.Genre;
import org.example.bookmanager.backend.model.Publisher;
import org.example.bookmanager.backend.service.BookService;
import org.example.bookmanager.backend.service.GenreService;
import org.example.bookmanager.backend.service.PublisherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class BookmanagerIntegrationTests {

    @Autowired
    private BookService bookService;
    @Autowired private GenreService genreService;
    @Autowired private PublisherService publisherService;

    // Book tests
    @Test
    void testSaveAndRetrieveBook() {
        Book book = new Book();
        book.setTitle("Integration Book");
        book.setAuthor("Test Author");
        bookService.saveBook(book);

        List<Book> books = bookService.getAllBooks();
        assertTrue(books.stream().anyMatch(b -> b.getTitle().equals("Integration Book")));
    }

    @Test
    void testDeleteBook() {
        Book book = new Book();
        book.setTitle("To Delete");
        book.setAuthor("Author");
        Book saved = bookService.saveBook(book);

        bookService.deleteBook(saved.getId());
        assertThrows(RuntimeException.class, () -> bookService.getBookById(saved.getId()));
    }

    @Test
    void testUpdateBook() {
        Book book = new Book();
        book.setTitle("Old Title");
        book.setAuthor("Author");
        Book saved = bookService.saveBook(book);

        saved.setTitle("New Title");
        bookService.saveBook(saved);

        assertEquals("New Title", bookService.getBookById(saved.getId()).getTitle());
    }

    // Genre tests
    @Test
    void testSaveAndRetrieveGenre() {
        Genre genre = new Genre();
        genre.setName("Horror");
        genreService.saveGenre(genre);

        assertTrue(genreService.getAllGenres()
                .stream().anyMatch(g -> g.getName().equals("Horror")));
    }

    // Publisher tests
    @Test
    void testSaveAndRetrievePublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("Test Publisher");
        Address address = new Address();
        address.setStreet("Main St");
        address.setNumber("1");
        address.setCity("Vienna");
        address.setPostalCode("1010");
        address.setNation("AT");
        publisher.setAddress(address);
        publisherService.savePublisher(publisher);

        assertTrue(publisherService.getAllPublishers()
                .stream().anyMatch(p -> p.getName().equals("Test Publisher")));
    }
}
