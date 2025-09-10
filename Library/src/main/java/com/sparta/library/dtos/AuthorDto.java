package com.sparta.library.dtos;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
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

    public AuthorDto(Integer id, String fullName, BookDto book) {
        this.id = id;
        this.fullName = fullName;
        this.book = book;
    }

    public Integer getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public BookDto getBook() {
        return book;
    }

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