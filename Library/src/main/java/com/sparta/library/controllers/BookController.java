package com.sparta.library.controllers;

import com.sparta.library.dtos.BookSummaryDto;
import com.sparta.library.entities.Book;
import com.sparta.library.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // for REST APIs; this anno combines @Controller & @ResponseBody
@RequestMapping("/books") // maps HTTP request to this endpoint
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @Operation(summary = "Get all books", description = "Retrieve a list of all books")
    @GetMapping(value = "/") // this is a post-fix to @RequestMapping above; maps HTTP GET requests to /books/
    public ResponseEntity<List<BookSummaryDto>> getAllBooks() {
        List<BookSummaryDto> books = service.getAllBooks();
        return ResponseEntity.ok(books); // returns OK status
    }

    @Operation(summary = "Get book by ID", description = "Retrieve a book from the database by its unique ID")
    // the below may be optional to set up as Spring can do it automatically with starter-data-rest dependency (which also does HATEOAS)
    @GetMapping("/{id}") // arg must match the PV parameter in line below
    public ResponseEntity<BookSummaryDto> getBookById(@PathVariable Integer id) {
        BookSummaryDto bookDto = service.getBookByID(id);
        if(bookDto != null){
            return ResponseEntity.ok(bookDto);
        }
        else{
            return ResponseEntity.notFound().build(); // returns 404 not found response
        }
    }

    @Operation(summary = "Add a new book", description = "Create a new book in the database")
    @PostMapping // no path needed here as it follows from original mapping above
    public ResponseEntity<BookSummaryDto> addBook(@RequestBody BookSummaryDto bookDTO) { // this annotation tells Spring to bind the JSON made by the HTTP request to a new Java object
        BookSummaryDto savedBook = service.saveBook(bookDTO);
        return ResponseEntity.status(201).body(savedBook); // 201 "created" code
    }

    @Operation(summary = "Delete a book", description = "Deletes a book by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        BookSummaryDto bookDto = service.getBookByID(id);
        if (bookDto != null) {
            service.deleteBookById(id); // method I created
            return ResponseEntity.noContent().build(); // 204 no content error
        } else {
            return ResponseEntity.notFound().build(); // 404 not found error
        }
    }

    @Operation(summary = "Update a book", description = "Modify an existing book's details in the database")
    @PutMapping("/{id}")
    public ResponseEntity<BookSummaryDto> updateBook(@PathVariable Integer id, @RequestBody BookSummaryDto bookDto) {
        try {
            BookSummaryDto updatedBook = service.updateBook(bookDto);
            return ResponseEntity.ok(updatedBook);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}