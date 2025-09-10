package com.sparta.library.dtos;

import com.sparta.library.entities.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

// mapstruct will generate implementations of these at runtimes
@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(target = "books", ignore = true) // prevent recursion
    AuthorDto toDTO(Author author);

    @Mapping(target = "books", ignore = true)
    Author toEntity(AuthorDto authorDto);
}
