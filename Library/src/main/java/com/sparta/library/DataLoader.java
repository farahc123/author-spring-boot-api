package com.sparta.library;

import com.sparta.library.entities.Author;
import com.sparta.library.entities.Book;
import com.sparta.library.repositories.AuthorRepository;
import com.sparta.library.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration // tells spring this class contains beans
@ComponentScan(basePackages = "com.sparta.library.repositories") // scans this package for repositories
public class DataLoader {

    @Bean
    @Transactional
    public CommandLineRunner loadData(AuthorRepository authorRepository, BookRepository bookRepository) { // special type of interface that executes code after the app context (incl. beans) has been fully loaded
        return args -> {
            if (authorRepository.count() == 0 && bookRepository.count() == 0) {
                Author author1 = new Author("JRR Tolkien", "jr@mordor.com");
                Author author2 = new Author("Douglas Adams", "da@galaxy.com");
                Author author3 = new Author("Richard Powers", "rp@overstory.com");
                authorRepository.save(author1);
                authorRepository.save(author2);
                authorRepository.save(author3);

                Book book1 = new Book("LOTR: Fellowship of the Ring", author1);
                Book book2 = new Book("LOTR: Two Towers", author1);
                Book book3 = new Book("Hitchhiker's Guide to the Galaxy", author2);
                Book book4 = new Book("The Overstory", author3);
                bookRepository.save(book1);
                bookRepository.save(book2);
                bookRepository.save(book3);

                System.out.println("Seed data added");
            } else {
                System.out.println("Seed skipped");
            }
        };
    }
}
