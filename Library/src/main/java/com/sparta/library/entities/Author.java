package com.sparta.library.entities;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

    @Entity // marks the class as a JPA entity, so it will be mapped to a DB table
    @Table(name = "authors") // name of this entity's corresponding DB table
    public class Author {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Integer id;

        @Column(name = "full_name", length = 40)
        private String fullName;

        @Column(name = "email", length = 40)
        private String email;

        @OneToMany(
                mappedBy = "author",
                cascade = CascadeType.ALL, // removes associated records in Books table
                orphanRemoval = true,
                fetch = FetchType.EAGER
        )
        private List<Book> books = new ArrayList<>();

        public Author(String fullName, String email) {
            this.fullName = fullName;
            this.email = email;
        }

        public Author() {
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public Integer getAuthorId() {
            return id;
        }

        public void setAuthorId(Integer id) {
            this.id = id;
        }

        public List<Book> getBooks(){
            return this.books;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
