package com.sparta.library.controllers;

import com.sparta.library.dtos.AuthorDto;
import com.sparta.library.dtos.AuthorCreateDto;
import com.sparta.library.services.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // for REST APIs; this anno combines @Controller & @ResponseBody
@RequestMapping("/authors") // maps HTTP request to this endpoint
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @Operation(summary = "Get all authors", description = "Retrieve a list of all authors")
    @GetMapping(value = "/") // this is a post-fix to @RequestMapping above; maps HTTP GET requests to /authors/
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        List<AuthorDto> authors = service.getAllAuthors();
        return ResponseEntity.ok(authors); // returns OK status
    }

    @Operation(summary = "Get author by ID", description = "Retrieve an author from the database by their unique ID")
    // the below may be optional to set up as Spring can do it automatically with starter-data-rest dependency (which also does HATEOAS)
    @GetMapping("/{id}") // arg must match the PV parameter in line below
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Integer id) {
        AuthorDto author = service.getAuthorByID(id);
        if(author != null){
            return ResponseEntity.ok(author);
        }
        else{
            return ResponseEntity.notFound().build(); // returns 404 not found response
        }
    }

    @Operation(summary = "Add a new author", description = "Create a new author in the database")
    @PostMapping // no path needed here as it follows from original mapping above
    public ResponseEntity<AuthorDto> addAuthor(@RequestBody AuthorCreateDto authorCreateDto) {
        AuthorDto saveAuthor = service.saveAuthor(authorCreateDto);
        return ResponseEntity.status(201).body(saveAuthor);
    }

    @Operation(summary = "Delete an author", description = "Deletes an author by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Integer id) {
        AuthorDto author = service.getAuthorByID(id);
        if (author != null) {
            service.deleteAuthorById(id); // method I created
            return ResponseEntity.noContent().build(); // 204 no content error
        } else {
            return ResponseEntity.notFound().build(); // 404 not found error
        }
    }

    @Operation(summary = "Update an author", description = "Modify an existing author's details in the database")
    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable Integer id, @RequestBody AuthorDto authorDto) {
        authorDto.getId();
        try {
            AuthorDto updatedAuthor = service.updateAuthor(authorDto);
            return ResponseEntity.ok(updatedAuthor);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
