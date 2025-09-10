package com.sparta.library.dtos;

import com.sparta.library.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "author.books", ignore = true) // prevent recursion
    BookDto toDTO(Book book);

    @Mapping(target = "author.books", ignore = true)
    Book toEntity(BookDto bookDto);
}
