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
        if (bookMapper == null) {
            throw new NullPointerException("BookMapper cannot be null");
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

    // controller passes DTO, service maps & persists entity, then maps back to DTO to return
    public BookDto saveBook(BookDto bookDTO){
        if (bookDTO == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        Book entity = bookMapper.toEntity(bookDTO);
        Book savedBook = bookRepository.save(entity);
        return bookMapper.toDTO(savedBook);
    }

    public boolean deleteBookById(Integer id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public BookDto updateBook(BookDto bookDto) {
        Integer id = bookDto.getId();
        if (!bookRepository.existsById(id)) {
            throw new NoSuchElementException("Book does not exist");
        }
        Book entity = bookMapper.toEntity(bookDto);
        Book savedBook = bookRepository.save(entity);
        return bookMapper.toDTO(savedBook);
    }
}