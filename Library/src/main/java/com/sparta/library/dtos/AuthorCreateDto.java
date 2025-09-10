package com.sparta.library.dtos;

import java.io.Serializable;

public class AuthorCreateDto implements Serializable {
    private String fullName;
    private String email;

    public AuthorCreateDto() {}

    public AuthorCreateDto(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
