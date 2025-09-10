package com.sparta.library.controllers;

import com.sparta.library.dtos.BookDto;
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
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> books = service.getAllBooks();
        return ResponseEntity.ok(books); // returns OK status
    }

    @Operation(summary = "Get book by ID", description = "Retrieve a book from the database by its unique ID")
    // the below may be optional to set up as Spring can do it automatically with starter-data-rest dependency (which also does HATEOAS)
    @GetMapping("/{id}") // arg must match the PV parameter in line below
    public ResponseEntity<BookDto> getBookById(@PathVariable Integer id) {
        BookDto book = service.getBookByID(id);
        if(book != null){
            return ResponseEntity.ok(book);
        }
        else{
            return ResponseEntity.notFound().build(); // returns 404 not found response
        }
    }

    @Operation(summary = "Add a new book", description = "Create a new book in the database")
    @PostMapping // no path needed here as it follows from original mapping above
    public ResponseEntity<BookDto> addBook(@RequestBody Book book) { // this annotation tells Spring to bind the JSON made by the HTTP request to a new Java object
        BookDto saveBook = service.saveBook(book);
        return ResponseEntity.status(201).body(saveBook); // 201 "created" code
    }

    @Operation(summary = "Delete a book", description = "Deletes a book by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        BookDto book = service.getBookByID(id);
        if (book != null) {
            service.deleteBookById(id); // method I created
            return ResponseEntity.noContent().build(); // 204 no content error
        } else {
            return ResponseEntity.notFound().build(); // 404 not found error
        }
    }

    @Operation(summary = "Update a book", description = "Modify an existing book's details in the database")
    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Integer id, @RequestBody Book book) {
        book.setId(id);
        try {
            BookDto updatedBook = service.updateBook(book);
            return ResponseEntity.ok(updatedBook);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}