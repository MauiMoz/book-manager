package org.example.bookmanager;

import org.example.bookmanager.backend.model.Book;
import org.example.bookmanager.backend.model.Genre;
import org.example.bookmanager.backend.model.Publisher;
import org.example.bookmanager.backend.repository.IBookRepository;
import org.example.bookmanager.backend.repository.IGenreRepository;
import org.example.bookmanager.backend.repository.IPublisherRepository;
import org.example.bookmanager.backend.service.BookService;
import org.example.bookmanager.backend.service.GenreService;
import org.example.bookmanager.backend.service.PublisherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class BookmanagerApplicationTests {

    @Mock private IBookRepository bookRepo;
    @Mock private IGenreRepository genRepo;
    @Mock private IPublisherRepository pubRepo;

    @InjectMocks private BookService bookService;
    @InjectMocks private GenreService genreService;
    @InjectMocks private PublisherService publisherService;

    //Book tests
    @Test
    void testGetAllBooks() {
        when(bookRepo.findAll()).thenReturn(List.of(new Book()));
        assertEquals(1, bookService.getAllBooks().size());
    }

    @Test
    void testGetAllBooksEmpty() {
        when(bookRepo.findAll()).thenReturn(List.of());
        assertEquals(0, bookService.getAllBooks().size());
    }

    @Test
    void testGetBookById() {
        Book book = new Book();
        book.setTitle("Jurassic Park");
        when(bookRepo.findById(1L)).thenReturn(Optional.of(book));
        assertEquals("Jurassic Park", bookService.getBookById(1L).getTitle());
    }

    @Test
    void testGetBookByIdNotFound() {
        when(bookRepo.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> bookService.getBookById(99L));
    }

    @Test
    void testSaveBook() {
        Book book = new Book();
        book.setTitle("Jurassic Park");
        when(bookRepo.save(book)).thenReturn(book);
        assertEquals("Jurassic Park", bookService.saveBook(book).getTitle());
    }

    @Test
    void testSaveBookWithAuthor() {
        Book book = new Book();
        book.setTitle("Jurassic Park");
        book.setAuthor("Michael Crichton");
        when(bookRepo.save(book)).thenReturn(book);
        Book saved = bookService.saveBook(book);
        assertEquals("Jurassic Park", saved.getTitle());
        assertEquals("Michael Crichton", saved.getAuthor());
    }

    @Test
    void testDeleteBook() {
        bookService.deleteBook(1L);
        verify(bookRepo, times(1)).deleteById(1L);
    }

    // Genre tests
    @Test
    void testGetAllGenres() {
        Genre genre = new Genre();
        genre.setName("Fiction");
        when(genRepo.findAll()).thenReturn(List.of(genre));
        assertEquals(1, genreService.getAllGenres().size());
    }

    @Test
    void testGetAllGenresEmpty() {
        when(genRepo.findAll()).thenReturn(List.of());
        assertEquals(0, genreService.getAllGenres().size());
    }

    @Test
    void testGetGenreById() {
        Genre genre = new Genre();
        genre.setName("Fiction");
        when(genRepo.findById(1L)).thenReturn(Optional.of(genre));
        assertEquals("Fiction", genreService.getGenreById(1L).getName());
    }

    @Test
    void testGetGenreByIdNotFound() {
        when(genRepo.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> genreService.getGenreById(99L));
    }

    @Test
    void testSaveGenre() {
        Genre genre = new Genre();
        genre.setName("Fiction");
        when(genRepo.save(genre)).thenReturn(genre);
        assertEquals("Fiction", genreService.saveGenre(genre).getName());
    }

    @Test
    void testDeleteGenre() {
        genreService.deleteGenre(1L);
        verify(genRepo, times(1)).deleteById(1L);
    }

    // Publisher tests
    @Test
    void testGetAllPublishers() {
        Publisher publisher = new Publisher();
        publisher.setName("Penguin");
        when(pubRepo.findAll()).thenReturn(List.of(publisher));
        assertEquals(1, publisherService.getAllPublishers().size());
    }

    @Test
    void testGetAllPublishersEmpty() {
        when(pubRepo.findAll()).thenReturn(List.of());
        assertEquals(0, publisherService.getAllPublishers().size());
    }

    @Test
    void testGetPublisherById() {
        Publisher publisher = new Publisher();
        publisher.setName("Penguin");
        when(pubRepo.findById(1L)).thenReturn(Optional.of(publisher));
        assertEquals("Penguin", publisherService.getPublisherById(1L).getName());
    }

    @Test
    void testGetPublisherByIdNotFound() {
        when(pubRepo.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> publisherService.getPublisherById(99L));
    }

    @Test
    void testSavePublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("Penguin");
        when(pubRepo.save(publisher)).thenReturn(publisher);
        assertEquals("Penguin", publisherService.savePublisher(publisher).getName());
    }

    @Test
    void testDeletePublisher() {
        publisherService.deletePublisher(1L);
        verify(pubRepo, times(1)).deleteById(1L);
    }
}
