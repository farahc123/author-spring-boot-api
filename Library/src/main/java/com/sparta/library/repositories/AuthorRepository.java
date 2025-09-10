package com.sparta.library.repositories;

import com.sparta.library.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false) // this disables automatic exposing of all endpoints (so only those explicitly created will be exposed)
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
