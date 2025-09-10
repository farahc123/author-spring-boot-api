package com.sparta.library.repositories;

import com.sparta.library.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false) // this disables automatic exposing of all endpoints (so only those explicitly created will be exposed)
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByTitleContainingIgnoreCase(String title);
}
