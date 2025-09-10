package com.sparta.library.services;

import com.sparta.library.dtos.AuthorDto;
import com.sparta.library.dtos.AuthorCreateDto;
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
        if (authorMapper == null) {
            throw new NullPointerException("AuthorMapper cannot be null");
        }
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public List<AuthorDto> getAllAuthors() {
        return authorRepository.findAll().stream().map(authorMapper::toDTO).toList();
    }

    public AuthorDto getAuthorByID(Integer id){
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Author not found"));
        return authorMapper.toDTO(author);
    }

    public AuthorDto saveAuthor(AuthorCreateDto authorCreateDto){
        if (authorCreateDto == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }
        Author entity = new Author(authorCreateDto.getFullName(), authorCreateDto.getEmail());
        Author savedAuthor = authorRepository.save(entity);
        return authorMapper.toDTO(savedAuthor);
    }

    public boolean deleteAuthorById(Integer id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public AuthorDto updateAuthor(AuthorDto authorDto) {
        Integer id = authorDto.getId();
        if (!authorRepository.existsById(id)) {
            throw new NoSuchElementException("Author does not exist");
        }
        Author entity = authorMapper.toEntity(authorDto);
        Author savedAuthor = authorRepository.save(entity);
        return authorMapper.toDTO(savedAuthor);
    }
}