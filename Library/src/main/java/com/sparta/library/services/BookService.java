package com.sparta.library.services;

import com.sparta.library.dtos.BookSummaryDto;
import com.sparta.library.entities.Author;
import com.sparta.library.entities.Book;
import com.sparta.library.repositories.AuthorRepository;
import com.sparta.library.repositories.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        if (bookRepository == null) {
            throw new NullPointerException("BookRepository cannot be null");
        }
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<BookSummaryDto> getAllBooks() {
        return bookRepository.findAll().stream().map(this::toSummaryDto).toList();
    }

    public BookSummaryDto getBookByID(Integer id) {
        return bookRepository.findById(id)
                .map(this::toSummaryDto)
                .orElseThrow(() -> new NoSuchElementException("Book not found"));
    }

    // controller passes DTO, service maps & persists entity, then maps back to DTO to return
    // note that the id will be required in swagger ui but will be overriden by the generated id in the database
    public BookSummaryDto saveBook(BookSummaryDto bookDTO) {
        if (bookDTO == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        Author author = authorRepository.findById(bookDTO.getAuthorId())
                .orElseThrow(() -> new NoSuchElementException("Author not found"));
        Book entity = new Book(bookDTO.getTitle(), author);
        Book savedBook = bookRepository.save(entity);
        return toSummaryDto(savedBook);
    }

    public boolean deleteBookById(Integer id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public BookSummaryDto updateBook(BookSummaryDto bookDto) {
        // Find the book by title (or add id to BookSummaryDto for better matching)
        List<Book> books = bookRepository.findAll();
        Book book = books.stream().filter(b -> b.getTitle().equals(bookDto.getTitle())).findFirst()
                .orElseThrow(() -> new NoSuchElementException("Book does not exist"));
        book.setTitle(bookDto.getTitle());
        Book savedBook = bookRepository.save(book);
        return toSummaryDto(savedBook);
    }

    private BookSummaryDto toSummaryDto(Book book) {
        String authorName = (book.getAuthor() != null) ? book.getAuthor().getFullName() : null;
        Integer authorId = (book.getAuthor() != null) ? book.getAuthor().getId() : null;
        return new BookSummaryDto(book.getId(), book.getTitle(), authorName, authorId);
    }
}