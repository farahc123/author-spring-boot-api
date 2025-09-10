package com.sparta.library.services;

import com.sparta.library.dtos.AuthorDto;
import com.sparta.library.dtos.AuthorMapper;
import com.sparta.library.entities.Author;
import com.sparta.library.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository,  AuthorMapper authorMapper) {
        if (authorRepository == null) {
            throw new NullPointerException("AuthorRepository cannot be null");
        }
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public List<AuthorDto> getAllAuthors() {
        return authorRepository.findAll().stream().map(authorMapper::toDTO).toList();
    }

    public AuthorDto getAuthorByID(Integer id){
        return authorRepository.findById(id)
                .map(authorMapper::toDTO)
                .orElseThrow(() -> new NoSuchElementException("Author not found"));
    }

    public AuthorDto saveAuthor(Author author){
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }
        Author savedAuthor = authorRepository.save(author);
        return authorMapper.toDTO(savedAuthor);
    }

    public boolean deleteAuthorById(Integer id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public AuthorDto updateAuthor(Author author) {
        if (!authorRepository.existsById(author.getId())) {
            throw new IllegalArgumentException("Author does not exist");
        }
        Author savedAuthor = authorRepository.save(author);
        return authorMapper.toDTO(savedAuthor);
    }
}
