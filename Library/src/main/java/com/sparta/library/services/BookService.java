package com.sparta.library.services;


import com.sparta.library.dtos.BookDto;
import com.sparta.library.dtos.BookMapper;
import com.sparta.library.entities.Book;
import com.sparta.library.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService (BookRepository bookRepository,  BookMapper bookMapper) {
        if (bookRepository == null) {
            throw new NullPointerException("BookRepository cannot be null");
        }
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream().map(bookMapper::toDTO).toList();
    }

    public BookDto getBookByID(Integer id){
        return bookRepository.findById(id)
                .map(bookMapper::toDTO)
                .orElseThrow(() -> new NoSuchElementException("Book not found"));
    }

    public BookDto saveBook(Book book){
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDTO(savedBook);
    }

    public boolean deleteBookById(Integer id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public BookDto updateBook(Book book) {
        if (!bookRepository.existsById(book.getId())) {
            throw new IllegalArgumentException("Book does not exist");
        }
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDTO(savedBook);
    }
}

