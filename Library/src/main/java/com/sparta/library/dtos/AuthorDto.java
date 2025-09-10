package com.sparta.library.dtos;

import com.sparta.library.entities.Book;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * DTO for {@link com.sparta.library.entities.Author}
 */
public class AuthorDto implements Serializable {
    @NotNull
    private final Integer id;
    @NotNull
    private final String fullName;
    private final BookDto book;

    public AuthorDto(Integer id, String fullName, BookDto book, List<BookDto> books) {
        this.id = id;
        this.fullName = fullName;
        this.book = book;
        this.books = books;
    }

    // Standard getters matching field names
    public Integer getId() { return id; }
    public String getFullName() { return fullName; }
//    public BookDto getBooks() { return book; }
    private final List<BookDto> books;

    public List<BookDto> getBooks() { return books; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDto entity = (AuthorDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.fullName, entity.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "fullName = " + fullName + ")";
    }
}
