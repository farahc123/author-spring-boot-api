package com.sparta.library.dtos;

import com.sparta.library.entities.Author;
import com.sparta.library.entities.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto toDTO(Book book);

    Book toEntity(BookDto bookDto);

}