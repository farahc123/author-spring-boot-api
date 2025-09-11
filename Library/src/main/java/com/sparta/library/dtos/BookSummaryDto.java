package com.sparta.library.dtos;

import java.io.Serializable;

public class BookSummaryDto implements Serializable {
    private final String title;
    private final String authorName;
    private final Integer authorId;

    public BookSummaryDto(String title, String authorName, Integer authorID) {
        this.title = title;
        this.authorName = authorName;
        this.authorId = authorID;
    }

    public String getTitle() { return title; }
    public String getAuthorName() { return authorName; }
    public Integer getAuthorId() { return authorId; }
}
