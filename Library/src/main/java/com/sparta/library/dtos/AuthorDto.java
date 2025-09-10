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
    private Integer id;
    @NotNull
    private String fullName;
    private List<BookDto> books;
    private String email;

    public AuthorDto(Integer id, String fullName, String email, List<BookDto> books) {
        this.id = id;
        this.fullName = fullName;
        this.books = books;
        this.email = email;
    }

  
    public Integer getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public List<BookDto> getBooks() { return books; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDto entity = (AuthorDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.fullName, entity.fullName) &&
                Objects.equals(this.email, entity.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, email);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "fullName = " + fullName + ", " +
                "email = " + email + ")";
    }
}
