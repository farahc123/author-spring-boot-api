package com.sparta.library.dtos;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.sparta.library.entities.Book}
 */
public class BookDto implements Serializable {
    @NotNull
    private final Integer id;
    @NotNull
    private final String title;
    private final AuthorDto author;

    public BookDto(Integer id, String title, AuthorDto author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public Integer getBookId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto entity = (BookDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.title, entity.title) &&
                Objects.equals(this.author, entity.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "title = " + title + ", " +
                "author = " + author + ")";
    }
}